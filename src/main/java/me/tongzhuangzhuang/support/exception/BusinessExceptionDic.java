package me.tongzhuangzhuang.support.exception;

/**
 * Created by wangtingbang on 16-2-16.
 */
public class BusinessExceptionDic {

  /**
   * 通用/基本异常
   */
  public static final BusinessException EX_GNR_UNKNOWN_ERROR = new BusinessException("999999", "未知错误");
  public static final BusinessException EX_GNR_ILLEGAL_PARAM = new BusinessException("001001", "参数非法");
  public static final BusinessException EX_GNR_NULL_PARAM = new BusinessException("001002", "参数空");
  public static final BusinessException EX_GNR_DATA_NULL = new BusinessException("001003", "数据空");
  public static final BusinessException EX_GNR_DATA_DUPLICATE = new BusinessException("001004", "数据冗余");
  public static final BusinessException EX_GNR_DATE_NULL = new BusinessException("001005", "时间空");
  public static final BusinessException EX_GNR_NOT_NUMBER = new BusinessException("001006", "数据不是有效数字");
  public static final BusinessException EX_GNR_NOT_DATE = new BusinessException("001007", "数据不是有效日期");
  public static final BusinessException EX_GNR_UNSUPPORTED_FORMAT = new BusinessException("001008", "数据格式不支持");
  public static final BusinessException EX_GNR_NULL_POINTER = new BusinessException("001009", "空指针");
  public static final BusinessException EX_GNR_REMOTE_TIMEOUT = new BusinessException("001010", "远程服务器连接超时");
  public static final BusinessException EX_GRN_DATABASE_TIMEOUT = new BusinessException("001011", "数据库连接超时");
  public static final BusinessException EX_GRN_DATABASE_WRITE = new BusinessException("001012", "数据无法保存");
  public static final BusinessException EX_GRN_DATABASE_READ = new BusinessException("001013", "数据无法查询");
  public static final BusinessException EX_GNR_DATA_ERROR = new BusinessException("001014", "数据错误");
  public static final BusinessException EX_GNR_ILLEGAL_REQUEST = new BusinessException("001015", "请求无效");


  /**
   * 用户
   */
  public static final BusinessException EX_USR_LOGIN_ERROR = new BusinessException("101000", "登录失败");
  public static final BusinessException EX_NULL_LOGIN_NAME = new BusinessException("101001", "登录名不能为空");
  public static final BusinessException EX_INVALID_LOGIN = new BusinessException("101002", "登录信息无效");
  public static final BusinessException EX_USR_UNAUTHORIZED = new BusinessException("101008","未授权的操作");
  public static final BusinessException EX_USR_UN_LOGIN = new BusinessException("101009","用户未登录");
  public static final BusinessException EX_USR_SESSION_TIMEOUT = new BusinessException("101010","登录已过时，请重新登录");
  public static final BusinessException EX_USR_UPDATE_PASSWORD = new BusinessException("101011","密码修改失败");
  public static final BusinessException EX_USR_UPDATE_LOGIN_NAME = new BusinessException("101012","登录名修改失败");
  public static final BusinessException EX_USR_LOGIN_NAME_DUPLICATE = new BusinessException("101013","登录名已存在");
  public static final BusinessException EX_USR_NOT_EXISTED = new BusinessException("101014","用户不存在");
  public static final BusinessException EX_USR_PSWD_ERROR = new BusinessException("101015","密码不正确");
  public static final BusinessException EX_USR_UNPOWERED = new BusinessException("101016","当前用户无权限");
  public static final BusinessException EX_USR_INVALID_USERINFO = new BusinessException("101017","用户信息无效");
  public static final BusinessException EX_USR_INVALID_USERROLE= new BusinessException("101018","用户信息无效,无效的用户角色");
  public static final BusinessException EX_USR_INVALID_USERAP_NULL = new BusinessException("101019","用户信息无效,经办人员审批人不可为空");


  /**
   * 活动 异常
   */
  public static final BusinessException EX_ACT_GROUP_NOT_EXIST = new BusinessException("201001", "活动组不存在");
  public static final BusinessException EX_ACT_GROUP_EXISTED = new BusinessException("201002", "活动组已存在");
  public static final BusinessException EX_ACTIVITY_NOT_EXIST = new BusinessException("201003", "活动不存在");
  public static final BusinessException EX_ACTIVITY_EXISTED = new BusinessException("201004", "活动已存在");
  public static final BusinessException EX_ACTIVITY_INVALID_OP = new BusinessException("201005", "活动操作不允许");
  public static final BusinessException EX_ACTIVITY_AREA_NULL = new BusinessException("201006", "活动地区为空");
  public static final BusinessException EX_ACTIVITY_GIFT_NULL = new BusinessException("201007", "活动礼品为空");
  public static final BusinessException EX_ACTIVITY_TYPE_ERR = new BusinessException("201008", "活动类型错误");
  public static final BusinessException EX_ACTIVITY_AREA_NO = new BusinessException("201009", "本区域无活动");
  public static final BusinessException EX_ACTIVITY_NULL_USER = new BusinessException("201010", "用户名下无活动");
  public static final BusinessException EX_ACTIVITY_INVALID = new BusinessException("201011", "活动不完整");
  public static final BusinessException EX_ACT_GROUP_INVALID = new BusinessException("201012", "活动组不完整");
  public static final BusinessException EX_ACTIVITY_INVALID_INFO = new BusinessException("201013", "活动信息不完整");

  public static final BusinessException EX_ACTIVITY_END = new BusinessException("201100", "活动已结束");
  public static final BusinessException EX_ACTIVITY_NOT_START = new BusinessException("201101", "活动还未开始");
  public static final BusinessException EX_ACTIVITY_USER_MAX_EXCEED = new BusinessException("201102", "超过活动可参与次数");
  public static final BusinessException EX_ACTIVITY_EXCLUSIVE_HIST = new BusinessException("201103", "已参与过同类型活动");
  public static final BusinessException EX_ACTIVITY_THANKS_PARTAKE = new BusinessException("201104", "很遗憾没抽到奖哦,谢谢参与!");
  public static final BusinessException EX_ACTIVITY_PARTAKE_FAILED = new BusinessException("201105", "领取活动礼品失败");
  public static final BusinessException EX_ACTIVITY_PARTAKE_QUA = new BusinessException("201106", "不足参与活动的资格");
  public static final BusinessException EX_ACTIVITY_PARTAKE_DUPLICATE = new BusinessException("201107", "商品已经领取过");
  public static final BusinessException EX_ACTIVITY_PARTAKE_PHONE_NULL = new BusinessException("201108", "手机号为空，请填写手机号");
  public static final BusinessException EX_ACTIVITY_CUSTLIST_NULL = new BusinessException("201200", "无客户活动参与记录");

  /**
   * 商品
   */
  public static final BusinessException EX_PRD_INUSE = new BusinessException("210100", "商品已被使用");
  public static final BusinessException EX_PRD_INDEX_QUERY = new BusinessException("210101", "商品目录查询失败");
  public static final BusinessException EX_PRD_INDEX_SAVE = new BusinessException("210102", "商品目录获取失败");
  public static final BusinessException EX_PRD_NOT_EXIST = new BusinessException("210103", "商品信息不存在");
  public static final BusinessException EX_PRD_STOCK_OUT= new BusinessException("210104", "商品库存不足");
  public static final BusinessException EX_PRD_REPAY_FAIL = new BusinessException("210105", "商品补发失败");
  public static final BusinessException EX_PRD_USED = new BusinessException("210106", "商品已被占用");
  public static final BusinessException EX_PRD_CHECKED_STATUS = new BusinessException("210107", "商品状态异常,请检查商品系统");
  public static final BusinessException EX_PRD_CHECKING_STATUS = new BusinessException("210108", "商品状态同步异常,请检查商品系统");
  public static final BusinessException EX_PRD_LOCK_STATUS = new BusinessException("210109", "商品锁定异常,请检查商品系统");

  /**
   * 商品 OMS
   */
  public static final BusinessException EX_EXT_PRD_REQUEST_TIMEOUT = new BusinessException("301001", "商品服务器连接超时");
  public static final BusinessException EX_EXT_PRD_REQUEST_ERROR = new BusinessException("301002", "商品服务器请求错误");
  public static final BusinessException EX_EXT_PRD_RESPONSE_NULL = new BusinessException("301003", "商品服务器响应为空");
  public static final BusinessException EX_EXT_PRD_RESPONSE_ERROR = new BusinessException("301004", "商品信息响应错误");

  /**
   * 商品 饭票
   */
  public static final BusinessException EX_EXT_PRD_GET_CODE_FAIL = new BusinessException("302000", "商品领取失败");

  /**
   * 客户
   */
  public static final BusinessException EX_CUST_NOT_LOGIN = new BusinessException("401000", "未登录");
  public static final BusinessException EX_CUST_LOGIN_ERR = new BusinessException("401001", "登录失败");
  public static final BusinessException EX_CUST_INVALID_LOGIN = new BusinessException("401002", "登录无效，客户信息不完全");
  public static final BusinessException EX_CUST_NOT_EXIST = new BusinessException("401003", "客户不存在，未登录过平台");

  /**
   * 资格平台
   */
  public static final BusinessException EX_QUA_RESPONSE_NULL = new BusinessException("501001", "资格平台数据返回为空");
  public static final BusinessException EX_QUA_RESPONSE_NONE = new BusinessException("501002", "没有可使用的资格，资格平台无数据");
  public static final BusinessException EX_QUA_NO_USEFULL = new BusinessException("501003", "没有可使用的资格");

}
