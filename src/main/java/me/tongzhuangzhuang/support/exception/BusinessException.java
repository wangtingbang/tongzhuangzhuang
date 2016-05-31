package me.tongzhuangzhuang.support.exception;

import me.tongzhuangzhuang.support.CommonReturnCode;

public class BusinessException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String code;

  public BusinessException() {}

  public BusinessException(String code) {
    super();
    this.code = code;
  }

  public BusinessException(CommonReturnCode apiCode) {
    this(apiCode.getCode(), apiCode.getMessage());
  }

  public BusinessException(String code, String message) {
    super(message);
    this.code = code;
  }

  public BusinessException(CommonReturnCode apiCode, String message) {
    this(apiCode.getCode(), message);
  }

  public BusinessException(String code, Throwable cause) {
    super(cause);
    this.code = code;
  }

  public BusinessException(String code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}
