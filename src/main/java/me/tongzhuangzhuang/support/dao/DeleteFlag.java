package me.tongzhuangzhuang.support.dao;

public class DeleteFlag {
  public static final byte DELETED = 1;
  public static final byte ALIVE = 0;

  public static byte deleted() {
    return DELETED;
  }

  public static byte alive() {
    return ALIVE;
  }

}
