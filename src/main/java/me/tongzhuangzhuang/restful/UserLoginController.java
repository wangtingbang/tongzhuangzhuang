package me.tongzhuangzhuang.restful;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.tongzhuangzhuang.utils.KeyGenerator;



/**
 * Created by wangtingbang on 16/3/22.
 */
@Controller
@RequestMapping(value = "customer")
public class UserLoginController {

  private static final Logger log = LoggerFactory.getLogger(UserLoginController.class);

  /**
   * Before 在核心业务执行前执行，不能阻止核心业务的调用。
   * 
   * @param joinPoint
   */
  @RequestMapping(value="login", method=RequestMethod.GET)
  @ResponseBody
  public String login(HttpServletRequest request) throws Exception{
    
    HttpSession session = request.getSession();
    String token = (String) session.getAttribute("token");
    String id = session.getId();
    
    log.info("get from request, token:{}, id:{}", token, id);
    
    if(StringUtils.isBlank(token)){
      String newToken = KeyGenerator.uuid();
      request.getSession().setAttribute("token", newToken);
      token = id+"-"+newToken;
      log.info("set new token:{}, id:{}", token, id);  
    }
    return token;
  }
}
