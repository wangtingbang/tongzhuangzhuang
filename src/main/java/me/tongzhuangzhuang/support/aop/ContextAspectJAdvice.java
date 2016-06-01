package me.tongzhuangzhuang.support.aop;

import com.google.common.collect.Lists;
import me.tongzhuangzhuang.support.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;


/**
 * Created by wangtingbang on 16/3/22.
 */
@Aspect
public class ContextAspectJAdvice {

  private static final Logger log = LoggerFactory.getLogger(ContextAspectJAdvice.class);


  private List<String> urlWhiteList = Lists.newArrayList("listUsersByDept","addMessage");

  /**
   * Pointcut 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数 该方法就是一个标识，不进行调用
   */
  @Pointcut("execution(* *.*(..)) && @annotation(me.tongzhuangzhuang.support.aop.ContextResolver)")
  private void aspectjMethod() {}


  /**
   * Before 在核心业务执行前执行，不能阻止核心业务的调用。
   * 
   * @param joinPoint
   */
  @Before("aspectjMethod()")
  public void beforeAdvice(JoinPoint joinPoint) throws BusinessException,Exception{
    /*
    try {
      Object[] args = joinPoint.getArgs();
      Method method = joinPoint.getTarget().getClass().getSuperclass().getDeclaredMethod("initContext", AuthUser.class);

      boolean inWhiteList = false;
      for (Object arg : args) {
        if (arg instanceof HttpServletRequest) {
          HttpServletRequest request = (HttpServletRequest) arg;
          String token = (String) request.getSession().getAttribute("token");
          if(log.isDebugEnabled()) {
            log.debug("token: {}", token);
          }
          if (StringUtils.isEmpty(token)) {
            String uri = request.getRequestURI();
            for(String tmp:urlWhiteList){
              if(uri.indexOf(tmp) >0){
                inWhiteList = true;
                break;
              }
            }
            if(inWhiteList){
              break;
            }
            log.error("错误, token为空, {}", BusinessExceptionDic.EX_USR_UN_LOGIN);
            throw BusinessExceptionDic.EX_USR_UN_LOGIN;
          }
          AuthUser user = authorityService.getUserByToken(token);
          if(user == null){
            log.error("错误: {}", BusinessExceptionDic.EX_USR_UN_LOGIN);
            throw BusinessExceptionDic.EX_USR_UN_LOGIN;
          }
          method.invoke(joinPoint.getTarget(), user);
        }
      }
    } catch (BusinessException e) {
      log.error("错误: {}", e);
      throw e;
    } catch (Exception e) {
      log.error("错误: {}", e);
      throw e;
    }//*/
  }

  private boolean authorizedRequest(Byte role, String url){

    return false;
  }
}
