package me.tongzhuangzhuang.support.mybatis;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * @param <T>
 */
public class Example<T> implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 5328950191693093949L;
  
  private Map<String, Object> fieldsMap;
  private boolean distinct;
  private List<String> order;
  private boolean desc;
  private Criteria criteria;
  private List<Criteria> params = new LinkedList<Criteria>();

  public Example(Class<T> clz) {
    Field[] fields = clz.getDeclaredFields();
    this.fieldsMap = new HashMap<String, Object>();
    for (int i = 0; i < fields.length; i++) {
      this.fieldsMap.put(propertyToColumn(fields[i].getName()), new Object());
    }
    this.criteria = new Criteria();
    this.params.add(criteria);
  }

  public Example<T> or() {
    criteria = new Criteria();
    params.add(criteria);
    return this;
  }

  private void param(String key, Object value, Object value2, String condition) {
    if ("between".equals(condition) || "notbetween".equals(condition)) {
      if (value == null || value2 == null) {
        throw new RuntimeException("Between values for " + key + " cannot be null");
      }
    }
    if (!("isnull".equals(condition) || "notnull".equals(condition))) {
      if (value == null) {
        return;
      }
      if (value instanceof Collection) {
        if (((Collection) value).size() == 0) {
          return;
        }
      }
    }
    Criterion criterion = new Criterion(key, value, value2, condition);
    if(fieldsMap.containsKey(criterion.getColunm())){
      this.criteria.getCriteria().put(criterion.getColunm()+"$"+condition, criterion);
    }
  }

  private Example<T> param(Object obj) {
    if (null == obj)
      return this;
    try {
      Class<?> clz = obj.getClass();
      Field[] fields = clz.getDeclaredFields();
      for (int i = 0; i < fields.length; i++) {
        Field field = fields[i];
        if (Modifier.isStatic(field.getModifiers())) {
          continue;
        }
        String prefix = "get";
        if ("class java.lang.Boolean".equals(field.getGenericType().toString())
            || "boolean".equals(field.getGenericType().toString())) {
          prefix = "is";
        }
        String key = field.getName();
        Object value = null;
        Method method = null;
        try {
          method = clz.getMethod(prefix + getMethodName(key));
        } catch (NoSuchMethodException e) {
        }
        if (null != method) {
          value = method.invoke(obj);
        }
        if (null != value) {
          this.param(key, value);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this;
  }

  private static String getMethodName(String fildeName) throws Exception {
    byte[] items = fildeName.getBytes();
    items[0] = (byte) ((char) items[0] - 'a' + 'A');
    return new String(items);
  }

  public Example<T> param(String key, Object value) {
    param(key, value, null, "iseq");
    return this;
  }

  public Example<T> paramNotEqualTo(String key, Object value) {
    param(key, value, null, "noteq");
    return this;
  }

  public Example<T> paramLikeTo(String key, Object value) {
    param(key, value, null, "like");
    return this;
  }

  public Example<T> paramNotLikeTo(String key, Object value) {
    param(key, value, null, "notlike");
    return this;
  }

  public Example<T> paramLessThan(String key, Object value) {
    param(key, value, null, "islt");
    return this;
  }

  public Example<T> paramGreaterThan(String key, Object value) {
    param(key, value, null, "isgt");
    return this;
  }

  public Example<T> paramLessThanOrEqualTo(String key, Object value) {
    param(key, value, null, "isle");
    return this;
  }

  public Example<T> paramGreaterThanOrEqualTo(String key, Object value) {
    param(key, value, null, "isge");
    return this;
  }

  public Example<T> paramBetween(String key, Object value, Object value2) {
    param(key, value, value2, "between");
    return this;
  }

  public Example<T> paramNotBetween(String key, Object value, Object value2) {
    param(key, value, value2, "notbetween");
    return this;
  }

  public Example<T> paramIn(String key, List value) {
    param(key, value, null, "isin");
    return this;
  }

  public Example<T> paramIn(String key, Object... values) {
    return paramIn(key, Arrays.asList(values));
  }

  public Example<T> paramNotIn(String key, List value) {
    param(key, value, null, "notin");
    return this;
  }

  public Example<T> paramNotIn(String key, Object... values) {
    return paramNotIn(key, Arrays.asList(values));
  }

  public Example<T> paramIsNull(String key) {
    param(key, null, null, "isnull");
    return this;
  }

  public Example<T> paramNotNull(String key) {
    param(key, null, null, "notnull");
    return this;
  }

  public static <T> Example<T> newExample(Class<T> clz) {
    return new Example<T>(clz);
  }

  public Example<T> distinct() {
    this.distinct = true;
    return this;
  }

  private static String propertyToColumn(String property) {
    if (property == null || property.isEmpty()) {
      return "";
    }
    if (property.contains("_"))
      return property;
    StringBuilder column = new StringBuilder();
    column.append(property.substring(0, 1).toLowerCase());
    for (int i = 1; i < property.length(); i++) {
      String s = property.substring(i, i + 1);
      if (!Character.isDigit(s.charAt(0)) && s.equals(s.toUpperCase())) {
        column.append("_");
      }
      column.append(s.toLowerCase());
    }
    return column.toString();
  }

  /**
   * 这个方法有问题， 不要使用
   * @param columns
   * @return
   */
  @Deprecated
  public Example<T> orderBy(String... columns) {
    if(columns==null) return this;
    for (int i = 0; i < columns.length; i++) {
      columns[i] = propertyToColumn(columns[i]);
      if(!fieldsMap.containsKey(columns[i])){
        throw new RuntimeException("no column [" +columns[i]+"] in table");
      }
    }
    if (this.order == null) {
      this.order = Arrays.asList(columns);
    } else {
      this.order.addAll(Arrays.asList(columns));
    }
    return this;
  }

  public Example<T> orderBy(String column) {
    if(column==null || "".equals(column)) return this;
     String columnStr = propertyToColumn(column);
      if(!fieldsMap.containsKey(columnStr)){
        throw new RuntimeException("no column [" +columnStr+"] in table");
      }
      this.order = Arrays.asList(columnStr);
    return this;
  }
  
  public Example<T> asc() {
    this.desc = false;
    return this;
  }

  public Example<T> desc() {
    this.desc = true;
    return this;
  }


  public static class Criteria implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Map<String, Criterion> criteria;

    public Criteria() {
      this.criteria = new HashMap<String, Criterion>();
    }

    public Map<String, Criterion> getCriteria() {
      return criteria;
    }

    public void setCriteria(Map<String, Criterion> criteria) {
      this.criteria = criteria;
    }

  }

  public static class Criterion implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String colunm;
    private Object value;
    private Object secondValue;
    private boolean iseq;
    private boolean noteq;
    private boolean like;
    private boolean notlike;
    private boolean islt;
    private boolean isgt;
    private boolean isle;
    private boolean isge;
    private boolean between;
    private boolean notbetween;
    private boolean isin;
    private boolean notin;
    private boolean isnull;
    private boolean notnull;
    
    public Criterion() {}

    public Criterion(String colunm, Object value, Object secondValue, String condition) {
      this.colunm = propertyToColumn(colunm);
      this.value = value;
      this.secondValue = secondValue;
      switch (condition) {
        case "iseq":
          this.iseq = true;
          break;
        case "noteq":
          this.noteq = true;
          break;
        case "like":
          this.like = true;
          break;
        case "notlike":
          this.notlike = true;
          break;
        case "islt":
          this.islt = true;
          break;
        case "isgt":
          this.isgt = true;
          break;
        case "isle":
          this.isle = true;
          break;
        case "isge":
          this.isge = true;
        case "between":
          this.between = true;
        case "notbetween":
          this.notbetween = true;
        case "isin":
          this.isin = true;
        case "notin":
          this.notin = true;
          break;
        case "isnull":
          this.isnull = true;
          break;
        case "notnull":
          this.notnull = true;
          break;
        default:
          break;
      }
    }

    public String getColunm() {
      return colunm;
    }

    public void setColunm(String colunm) {
      this.colunm = colunm;
    }

    public Object getValue() {
      return value;
    }

    public void setValue(Object value) {
      this.value = value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public void setSecondValue(Object secondValue) {
      this.secondValue = secondValue;
    }

    public boolean isIseq() {
      return iseq;
    }

    public void setIseq(boolean iseq) {
      this.iseq = iseq;
    }

    public boolean isNoteq() {
      return noteq;
    }

    public void setNoteq(boolean noteq) {
      this.noteq = noteq;
    }

    public boolean isLike() {
      return like;
    }

    public void setLike(boolean like) {
      this.like = like;
    }

    public boolean isNotlike() {
      return notlike;
    }

    public void setNotlike(boolean notlike) {
      this.notlike = notlike;
    }

    public boolean isIslt() {
      return islt;
    }

    public void setIslt(boolean islt) {
      this.islt = islt;
    }

    public boolean isIsgt() {
      return isgt;
    }

    public void setIsgt(boolean isgt) {
      this.isgt = isgt;
    }

    public boolean isIsle() {
      return isle;
    }

    public void setIsle(boolean isle) {
      this.isle = isle;
    }

    public boolean isIsge() {
      return isge;
    }

    public void setIsge(boolean isge) {
      this.isge = isge;
    }

    public boolean isBetween() {
      return between;
    }

    public void setBetween(boolean between) {
      this.between = between;
    }

    public boolean isNotbetween() {
      return notbetween;
    }

    public void setNotbetween(boolean notbetween) {
      this.notbetween = notbetween;
    }

    public boolean isIsin() {
      return isin;
    }

    public void setIsin(boolean isin) {
      this.isin = isin;
    }

    public boolean isNotin() {
      return notin;
    }

    public void setNotin(boolean notin) {
      this.notin = notin;
    }

    public boolean isIsnull() {
      return isnull;
    }

    public void setIsnull(boolean isnull) {
      this.isnull = isnull;
    }

    public boolean isNotnull() {
      return notnull;
    }

    public void setNotnull(boolean notnull) {
      this.notnull = notnull;
    }

  }

  public boolean isDistinct() {
    return distinct;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public List<String> getOrder() {
    return order;
  }

  public void setOrder(List<String> order) {
    this.order = order;
  }

  public boolean isDesc() {
    return desc;
  }

  public void setDesc(boolean desc) {
    this.desc = desc;
  }

  public List<Criteria> getParams() {
    return params;
  }

  public void setParams(List<Criteria> params) {
    this.params = params;
  }

}
