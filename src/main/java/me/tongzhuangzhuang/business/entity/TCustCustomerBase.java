package me.tongzhuangzhuang.business.entity;

import me.tongzhuangzhuang.support.dao.BaseEntity;

/**
 * 
 * @generated by Code Generator v0.1
 * @date Tue May 31 16:05:12 CST 2016
 */
public class TCustCustomerBase extends BaseEntity<String> {

  /**
   * @field id
   */
  private String id;

  /**
   * @field loginName
   */
  private String loginName;

  /**
   * @field password
   */
  private String password;

  /**
   * @field userName
   */
  private String userName;

  /**
   * @field phone
   */
  private String phone;

  /**
   * @field email
   */
  private String email;

  /**
   * @field deleteFlag
   */
  private Byte deleteFlag;

  /**
   * @return id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return loginName
   */
  public String getLoginName() {
    return loginName;
  }

  /**
   * @param loginName
   */
  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  /**
   * @return password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return phone
   */
  public String getPhone() {
    return phone;
  }

  /**
   * @param phone
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * @return email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return deleteFlag
   */
  public Byte getDeleteFlag() {
    return deleteFlag;
  }

  /**
   * @param deleteFlag
   */
  public void setDeleteFlag(Byte deleteFlag) {
    this.deleteFlag = deleteFlag;
  }
}
