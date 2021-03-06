package me.tongzhuangzhuang.restful;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.tongzhuangzhuang.utils.DateTimeUtils;
import me.tongzhuangzhuang.utils.KeyGenerator;

/**
 * Created by wangtingbang on 16/3/22.
 */
@Controller
@RequestMapping(value = "user")
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
    
    if(!StringUtils.isBlank(id)){
      return id;
    }
    
    log.info("get from request, token:{}, id:{}", token, id);
    
    if(StringUtils.isBlank(token)){
      String newToken = KeyGenerator.uuid();
      token = id+"-"+newToken;
      request.getSession().setAttribute("token", token);
      
      log.info("set new token:{}, id:{}", token, id);  
    }
    return token;
  }
  
  @RequestMapping(value="cookie", method=RequestMethod.GET)
  @ResponseBody
  public String cookie(@CookieValue(value = "token", defaultValue="") String cookieValue, HttpServletRequest request, HttpServletResponse response){
    String key = null;

    if(StringUtils.isBlank(cookieValue)){
      log.info("token is null");
      String uuid = KeyGenerator.uuid();
      String date = DateTimeUtils.fromDate(DateTimeUtils.now(), "yyyy-MM-dd_HH:mm:ss");
      key = date+">>"+uuid;
      Cookie cookie = new Cookie("token", key);
      cookie.setMaxAge(5);
      response.addCookie(cookie);
    }else{
      key = cookieValue;
      Cookie[] cookies = request.getCookies();
      for(Cookie cookie:cookies){
        if("token".equals(cookie.getName())){
          cookie.setMaxAge(5);
          response.addCookie(cookie);
          break;
        }
      }
    }
    System.out.println(key);
    return key;
  }
}
