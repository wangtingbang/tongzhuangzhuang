package me.tongzhuangzhuang.support.database;

import java.io.*;
import java.util.*;

public class PropertiesHelper {

  public PropertiesHelper(Properties p) {
    systemPropertiesMode = 0;
    setProperties(p);
  }

  public PropertiesHelper(Properties p, int systemPropertiesMode) {
    this.systemPropertiesMode = 0;
    setProperties(p);
    if (systemPropertiesMode != 0 && systemPropertiesMode != 1 && systemPropertiesMode != 2) {
      throw new IllegalArgumentException((new StringBuilder("error systemPropertiesMode mode:"))
          .append(systemPropertiesMode).toString());
    } else {
      this.systemPropertiesMode = systemPropertiesMode;
      return;
    }
  }

  public Properties getProperties() {
    return p;
  }

  public void setProperties(Properties props) {
    if (props == null) {
      throw new IllegalArgumentException("properties must be not null");
    } else {
      p = props;
      return;
    }
  }

  public String getRequiredString(String key) {
    String value = getProperty(key);
    if (isBlankString(value))
      throw new IllegalStateException((new StringBuilder("required property is blank by key="))
          .append(key).toString());
    else
      return value;
  }

  public String getNullIfBlank(String key) {
    String value = getProperty(key);
    if (isBlankString(value))
      return null;
    else
      return value;
  }

  public String getNullIfEmpty(String key) {
    String value = getProperty(key);
    if (value == null || "".equals(value))
      return null;
    else
      return value;
  }

  public String getAndTryFromSystem(String key) {
    String value = getProperty(key);
    if (isBlankString(value))
      value = getSystemProperty(key);
    return value;
  }

  private String getSystemProperty(String key) {
    String value = System.getProperty(key);
    if (isBlankString(value))
      value = System.getenv(key);
    return value;
  }

  public Integer getInteger(String key) {
    String v = getProperty(key);
    if (v == null)
      return null;
    else
      return Integer.valueOf(Integer.parseInt(v));
  }

  public int getInt(String key, int defaultValue) {
    if (getProperty(key) == null)
      return defaultValue;
    else
      return Integer.parseInt(getRequiredString(key));
  }

  public int getRequiredInt(String key) {
    return Integer.parseInt(getRequiredString(key));
  }

  public Long getLong(String key) {
    if (getProperty(key) == null)
      return null;
    else
      return Long.valueOf(Long.parseLong(getRequiredString(key)));
  }

  public long getLong(String key, long defaultValue) {
    if (getProperty(key) == null)
      return defaultValue;
    else
      return Long.parseLong(getRequiredString(key));
  }

  public Long getRequiredLong(String key) {
    return Long.valueOf(Long.parseLong(getRequiredString(key)));
  }

  public Boolean getBoolean(String key) {
    if (getProperty(key) == null)
      return null;
    else
      return Boolean.valueOf(Boolean.parseBoolean(getRequiredString(key)));
  }

  public boolean getBoolean(String key, boolean defaultValue) {
    if (getProperty(key) == null)
      return defaultValue;
    else
      return Boolean.parseBoolean(getRequiredString(key));
  }

  public boolean getRequiredBoolean(String key) {
    return Boolean.parseBoolean(getRequiredString(key));
  }

  public Float getFloat(String key) {
    if (getProperty(key) == null)
      return null;
    else
      return Float.valueOf(Float.parseFloat(getRequiredString(key)));
  }

  public float getFloat(String key, float defaultValue) {
    if (getProperty(key) == null)
      return defaultValue;
    else
      return Float.parseFloat(getRequiredString(key));
  }

  public Float getRequiredFloat(String key) {
    return Float.valueOf(Float.parseFloat(getRequiredString(key)));
  }

  public Double getDouble(String key) {
    if (getProperty(key) == null)
      return null;
    else
      return Double.valueOf(Double.parseDouble(getRequiredString(key)));
  }

  public double getDouble(String key, double defaultValue) {
    if (getProperty(key) == null)
      return defaultValue;
    else
      return Double.parseDouble(getRequiredString(key));
  }

  public Double getRequiredDouble(String key) {
    return Double.valueOf(Double.parseDouble(getRequiredString(key)));
  }

  public Object setProperty(String key, int value) {
    return setProperty(key, String.valueOf(value));
  }

  public Object setProperty(String key, long value) {
    return setProperty(key, String.valueOf(value));
  }

  public Object setProperty(String key, float value) {
    return setProperty(key, String.valueOf(value));
  }

  public Object setProperty(String key, double value) {
    return setProperty(key, String.valueOf(value));
  }

  public Object setProperty(String key, boolean value) {
    return setProperty(key, String.valueOf(value));
  }

  public String[] getStringArray(String key) {
    String v = getProperty(key);
    if (v == null)
      return new String[0];
    else
      return tokenizeToStringArray(v, ", \t\n\r\f");
  }

  public int[] getIntArray(String key) {
    return toIntArray(getStringArray(key));
  }

  public Properties getStartsWithProperties(String prefix) {
    if (prefix == null)
      throw new IllegalArgumentException("'prefix' must be not null");
    Properties props = getProperties();
    Properties result = new Properties();
    for (Iterator iterator = props.entrySet().iterator(); iterator.hasNext();) {
      Map.Entry entry = (Map.Entry) iterator.next();
      String key = (String) entry.getKey();
      if (key != null && key.startsWith(prefix))
        result.put(key.substring(prefix.length()), entry.getValue());
    }

    return result;
  }

  public String getProperty(String key, String defaultValue) {
    String value = getProperty(key);
    if (isBlankString(value))
      return defaultValue;
    else
      return value;
  }

  public String getProperty(String key) {
    String propVal = null;
    if (systemPropertiesMode == 2)
      propVal = getSystemProperty(key);
    if (propVal == null)
      propVal = p.getProperty(key);
    if (propVal == null && systemPropertiesMode == 1)
      propVal = getSystemProperty(key);
    return propVal;
  }

  public Object setProperty(String key, String value) {
    return p.setProperty(key, value);
  }

  public void clear() {
    p.clear();
  }

  public Set entrySet() {
    return p.entrySet();
  }

  public Enumeration propertyNames() {
    return p.propertyNames();
  }

  public boolean contains(Object value) {
    return p.contains(value);
  }

  public boolean containsKey(Object key) {
    return p.containsKey(key);
  }

  public boolean containsValue(Object value) {
    return p.containsValue(value);
  }

  public Enumeration elements() {
    return p.elements();
  }

  public Object get(Object key) {
    return p.get(key);
  }

  public boolean isEmpty() {
    return p.isEmpty();
  }

  public Enumeration keys() {
    return p.keys();
  }

  public Set keySet() {
    return p.keySet();
  }

  public void list(PrintStream out) {
    p.list(out);
  }

  public void list(PrintWriter out) {
    p.list(out);
  }

  public void load(InputStream inStream) throws IOException {
    p.load(inStream);
  }

  public void loadFromXML(InputStream in) throws IOException, InvalidPropertiesFormatException {
    p.loadFromXML(in);
  }

  public Object put(Object key, Object value) {
    return p.put(key, value);
  }

  public void putAll(Map t) {
    p.putAll(t);
  }

  public Object remove(Object key) {
    return p.remove(key);
  }

  /**
   * @deprecated Method save is deprecated
   */

  public void save(OutputStream out, String comments) {
    p.save(out, comments);
  }

  public int size() {
    return p.size();
  }

  public void store(OutputStream out, String comments) throws IOException {
    p.store(out, comments);
  }

  public void storeToXML(OutputStream os, String comment, String encoding) throws IOException {
    p.storeToXML(os, comment, encoding);
  }

  public void storeToXML(OutputStream os, String comment) throws IOException {
    p.storeToXML(os, comment);
  }

  public Collection values() {
    return p.values();
  }

  public String toString() {
    return p.toString();
  }

  private static boolean isBlankString(String value) {
    return value == null || "".equals(value.trim());
  }

  private static String[] tokenizeToStringArray(String str, String seperators) {
    StringTokenizer tokenlizer = new StringTokenizer(str, seperators);
    List result = new ArrayList();
    Object s;
    for (; tokenlizer.hasMoreElements(); result.add(s))
      s = tokenlizer.nextElement();

    return (String[]) result.toArray(new String[result.size()]);
  }

  private static int[] toIntArray(String array[]) {
    int result[] = new int[array.length];
    for (int i = 0; i < array.length; i++)
      result[i] = Integer.parseInt(array[i]);

    return result;
  }

  public static final int SYSTEM_PROPERTIES_MODE_NEVER = 0;
  public static final int SYSTEM_PROPERTIES_MODE_FALLBACK = 1;
  public static final int SYSTEM_PROPERTIES_MODE_OVERRIDE = 2;
  private int systemPropertiesMode;
  private Properties p;
}
