package com.hsu.netchat.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hsu.netchat.bean.Anno;
import com.hsu.netchat.service.AnnoService;

public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Autowired
	private AnnoService annoService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();  
		List<Anno> annoMsg = null;
		
		//在用户登录的过程中就进行拦截，获得公告信息，并保存在session域中
		if(requestURI.indexOf("login")>0){
			annoMsg = annoService.getAnnoMsg();
			request.getSession().setAttribute("anno", annoMsg.get(0).getAnno());
		}
		 
		 /*if(requestURI.indexOf("index")>0){
			System.out.println("----------登陆拦截----------");
			User user = (User) session.getAttribute("userInfo");
			
			if(user == null){
				request.getRequestDispatcher("/forward.jsp").forward(request, response);
				return false;
			}else{
				
				return true;
			}
		} */
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
