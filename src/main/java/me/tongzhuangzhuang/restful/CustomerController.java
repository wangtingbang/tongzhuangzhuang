package me.tongzhuangzhuang.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.tongzhuangzhuang.business.dto.CustCustomerBase;
import me.tongzhuangzhuang.business.service.CustCustomerBaseService;

@Controller
@RequestMapping(value = "customer")
public class CustomerController {

  @Autowired
  CustCustomerBaseService custCustomerBaseService;
  
  @RequestMapping(value="list", method=RequestMethod.GET)
  @ResponseBody
  public List<CustCustomerBase> list(){
    return custCustomerBaseService.listCustomers();
  }
}
