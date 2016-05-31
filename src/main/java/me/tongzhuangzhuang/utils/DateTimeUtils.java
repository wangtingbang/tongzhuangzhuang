package me.tongzhuangzhuang.utils;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 
 * @author wangtingbang
 *
 */
public class DateTimeUtils {

  public class Pattern {
    public final static String DEFAULT_FORMATE_DATE = "yyyy-MM-dd";
    public final static String DEFAULT_FORMATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public final static String DEFAULT_FORMATE_TIME_MI = "yyyy-MM-dd HH:mm:ss.SSS";
    
    public final static String CMBCHINA_FORMATE_DATE = "yyyy/MM/dd";
    public final static String CMBCHINA_FORMATE_TIME = "yyyy/MM/dd HH:mm:ss";
    public final static String CMBCHINA_FORMATE_TIME_MI = "yyyy/MM/dd HH:mm:ss.SSS";
  }

  public static String fromDate(Date date, String pattern) {
    return new DateTime(date).toString(pattern);
  }

  public static String fromDate(Date date) {
    return fromDate(date, Pattern.DEFAULT_FORMATE_DATE);
  }

  public static String fromDateTime(Date date) {
    return fromDate(date, Pattern.DEFAULT_FORMATE_TIME);
  }

  public static Date toDate(String date, String pattern) {
    DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
    DateTime dateTime = DateTime.parse(date, formatter);
    return dateTime.toDate();
  }

  public static Date toDate(String date) {
    return toDate(date, Pattern.DEFAULT_FORMATE_DATE);
  }

  public static Date toDateTime(String date) {
    return toDate(date, Pattern.DEFAULT_FORMATE_TIME);
  }

  public static Date round(Date date) {
    return toDate(fromDate(date));
  }

  public static Date now() {
    return DateTime.now().toDate();
  }

  public static Date today() {
    return round(now());
  }

  public static String getCurrentTime(String pattern) {
    return fromDate(now(), pattern);
  }

  public static String getCurrentTime() {
    return getCurrentTime(Pattern.DEFAULT_FORMATE_TIME);
  }

  public static String getCurrentDate(String pattern) {
    return fromDate(today(), pattern);
  }

  public static String getCurrentDate() {
    return getCurrentDate(Pattern.DEFAULT_FORMATE_DATE);
  }

  public static Date plusDays(Date date, int days) {
    return new DateTime(date).plusDays(days).toDate();
  }

  public static Date plusWeeks(Date date, int weeks) {
    return new DateTime(date).plusWeeks(weeks).toDate();
  }

  public static Date plusMonths(Date date, int months) {
    return new DateTime(date).plusMonths(months).toDate();
  }

  public static Date plusYears(Date date, int years) {
    return new DateTime(date).plusYears(years).toDate();
  }

  public static Date plusHours(Date date, int hours) {
    return new DateTime(date).plusHours(hours).toDate();
  }

  public static Date plusMinutes(Date date, int minutes) {
    return new DateTime(date).plusMinutes(minutes).toDate();
  }

  public static Date plusSeconds(Date date, int seconds) {
    return new DateTime(date).plusSeconds(seconds).toDate();
  }

  public static Date mondayOfWeek(Date date) {
    return new DateTime(date).dayOfWeek().withMinimumValue().toDate();
  }

  public static Date sundayOfWeek(Date date) {
    return new DateTime(date).dayOfWeek().withMaximumValue().toDate();
  }

  public static Date firstDayOfMonth(Date date) {
    return new DateTime(date).dayOfMonth().withMinimumValue().toDate();
  }

  public static Date lastDayOfMonth(Date date) {
    return new DateTime(date).dayOfMonth().withMaximumValue().toDate();
  }

  public static Date firstDayOfYear(Date date) {
    return new DateTime(date).dayOfYear().withMinimumValue().toDate();
  }

  public static Date lastDayOfYear(Date date) {
    return new DateTime(date).dayOfYear().withMaximumValue().toDate();
  }

  /**
   * date 相差天数，忽略date的时分秒
   * 
   * @param start
   * @param end
   * @return
   */
  public static int daysBetween(Date start, Date end) {
    return Days.daysBetween(new DateTime(round(start)), new DateTime(round(end))).getDays();
  }

  /**
   * date 相差月数，忽略date的时分秒
   * 
   * @param start
   * @param end
   * @return
   */
  public static int monthsBetween(Date start, Date end) {
    return Months.monthsBetween(new DateTime(round(start)), new DateTime(round(end))).getMonths();
  }

  /**
   * date 相差年数，忽略date的时分秒
   * 
   * @param start
   * @param end
   * @return
   */
  public static int yearsBetween(Date start, Date end) {
    return Years.yearsBetween(new DateTime(round(start)), new DateTime(round(end))).getYears();
  }

  /**
   * date 相差秒钟数
   * 
   * @param start
   * @param end
   * @return
   */
  public static int secondsBetween(Date start, Date end) {
    return Seconds.secondsBetween(new DateTime(start), new DateTime(end)).getSeconds();
  }

  /**
   * date 相差分钟数
   * 
   * @param start
   * @param end
   * @return
   */
  public static int minutesBetween(Date start, Date end) {
    return Minutes.minutesBetween(new DateTime(start), new DateTime(end)).getMinutes();
  }

  /**
   * date 相差小时数
   * 
   * @param start
   * @param end
   * @return
   */
  public static int hoursBetween(Date start, Date end) {
    return Hours.hoursBetween(new DateTime(start), new DateTime(end)).getHours();
  }

}
