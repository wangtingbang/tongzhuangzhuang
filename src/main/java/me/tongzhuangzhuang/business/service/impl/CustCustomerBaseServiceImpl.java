package me.tongzhuangzhuang.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.tongzhuangzhuang.business.biz.CustCustomerBaseBusiness;
import me.tongzhuangzhuang.business.dto.CustCustomerBase;
import me.tongzhuangzhuang.business.entity.TCustCustomerBase;
import me.tongzhuangzhuang.business.service.CustCustomerBaseService;
import me.tongzhuangzhuang.support.mybatis.Example;

/**
 * 
 * @generated by Code Generator v0.1
 * @Tue May 31 16:05:12 CST 2016
 */
@Service //("ustCustomerBaseService")
public class CustCustomerBaseServiceImpl implements CustCustomerBaseService {

  @Autowired
  private CustCustomerBaseBusiness custCustomerBaseBusiness;

  private static Logger logger = LoggerFactory.getLogger(CustCustomerBaseServiceImpl.class);

  public List<CustCustomerBase> listCustomers(){
    Example example = Example.newExample(TCustCustomerBase.class);
    return custCustomerBaseBusiness.list(example);
  }
}
