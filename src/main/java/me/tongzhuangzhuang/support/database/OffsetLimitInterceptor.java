package me.tongzhuangzhuang.support.database;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
    Object.class, RowBounds.class, ResultHandler.class})})
public class OffsetLimitInterceptor implements Interceptor {
  private static final Logger logger = LoggerFactory.getLogger(OffsetLimitInterceptor.class);
  public static class BoundSqlSqlSource implements SqlSource {

    public BoundSql getBoundSql(Object parameterObject) {
      return boundSql;
    }

    BoundSql boundSql;

    public BoundSqlSqlSource(BoundSql boundSql) {
      this.boundSql = boundSql;
    }
  }

  public OffsetLimitInterceptor() {}

  public Object intercept(Invocation invocation) throws Throwable {
    processIntercept(invocation.getArgs());
    return invocation.proceed();
  }

  void processIntercept(Object queryArgs[]) {

    MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
    Object parameter = queryArgs[PARAMETER_INDEX];
    RowBounds rowBounds = (RowBounds) queryArgs[ROWBOUNDS_INDEX];
    int offset = rowBounds.getOffset();
    int limit = rowBounds.getLimit();
    if (dialect.supportsLimit() && (offset != 0 || limit != 0x7fffffff)) {
      BoundSql boundSql = ms.getBoundSql(parameter);
      String sql = boundSql.getSql().trim();
      if (dialect.supportsLimitOffset()) {
        sql = dialect.getLimitString(sql, offset, limit);
        offset = 0;
      } else {
        sql = dialect.getLimitString(sql, 0, limit);
      }
      limit = 0x7fffffff;
      queryArgs[ROWBOUNDS_INDEX] = new RowBounds(offset, limit);
      BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);
      MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
      queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
    }
  }

  private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
    BoundSql newBoundSql =
        new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),
            boundSql.getParameterObject());
    for (Iterator iterator = boundSql.getParameterMappings().iterator(); iterator.hasNext();) {
      ParameterMapping mapping = (ParameterMapping) iterator.next();
      String prop = mapping.getProperty();
      if (boundSql.hasAdditionalParameter(prop))
        newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
    }

    return newBoundSql;
  }

  private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
    MappedStatement.Builder builder =
        new MappedStatement.Builder(ms.getConfiguration(), ms.getId(),
            newSqlSource, ms.getSqlCommandType());
    builder.resource(ms.getResource());
    builder.fetchSize(ms.getFetchSize());
    builder.statementType(ms.getStatementType());
    builder.keyGenerator(ms.getKeyGenerator());
    String[] s = ms.getKeyProperties();
    if (s == null) {
      builder.keyProperty(null);
    } else {
      builder.keyProperty(s[0]);
    }
    builder.timeout(ms.getTimeout());
    builder.parameterMap(ms.getParameterMap());
    builder.resultMaps(ms.getResultMaps());
    builder.resultSetType(ms.getResultSetType());
    builder.cache(ms.getCache());
    builder.flushCacheRequired(ms.isFlushCacheRequired());
    builder.useCache(ms.isUseCache());
    return builder.build();
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  public void setProperties(Properties properties) {
    String dialectClass = (new PropertiesHelper(properties)).getRequiredString("dialectClass");
    try {
      dialect = (Dialect) Class.forName(dialectClass).newInstance();
    } catch (Exception e) {
      throw new RuntimeException((new StringBuilder(
          "cannot create dialect instance by dialectClass:")).append(dialectClass).toString(), e);
    }
    logger.info((new StringBuilder(String.valueOf(OffsetLimitInterceptor.class
        .getSimpleName()))).append(".dialect=").append(dialectClass).toString());
  }

  static int MAPPED_STATEMENT_INDEX = 0;
  static int PARAMETER_INDEX = 1;
  static int ROWBOUNDS_INDEX = 2;
  static int RESULT_HANDLER_INDEX = 3;
  Dialect dialect;

}
