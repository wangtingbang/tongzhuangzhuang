package me.tongzhuangzhuang.support.mvc;

import com.alibaba.fastjson.JSONException;
import me.tongzhuangzhuang.support.exception.BusinessExceptionDic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangtingbang on 16/1/12.
 */

public class CommonExceptionHandler implements HandlerExceptionResolver {
  private static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

  @Override
  public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp,
      Object handler, Exception ex) {
    ModelAndView mav = null;
    String xRequestedWith = req.getHeader("X-Requested-With");
    if (!StringUtils.isEmpty(xRequestedWith)) {
      mav = new ModelAndView("exception/ajaxexception");
    } else {
      mav = new ModelAndView("exception/exception");
    }
    logger.error(ex.getLocalizedMessage());
    if (ex instanceof JSONException) {
      ex = BusinessExceptionDic.EX_GNR_ILLEGAL_PARAM;
    }
    mav.addObject("ex", ex);
    return mav;
  }
}
