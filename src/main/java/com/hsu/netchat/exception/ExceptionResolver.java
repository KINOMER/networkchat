package com.hsu.netchat.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
@Component
public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		//ex.getCause();
		String errorMsg = "异常信息："+ex.getMessage();
		 ModelAndView modelAndView = new ModelAndView("errorPage");
		 modelAndView.addObject("errorMsg", errorMsg);
		return modelAndView;
	}

}
