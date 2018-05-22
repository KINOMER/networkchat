package com.hsu.netchat.filter;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsu.netchat.bean.User;
import com.hsu.netchat.service.UserService;

public class UserFormAuthenticationFilter extends FormAuthenticationFilter {

	@Autowired
	private UserService userService;
	
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String valistr1 = (String) httpServletRequest.getSession().getAttribute("valistr");

		if(valistr1 != null && valistr1 != ""){
			String valistr2 = httpServletRequest.getParameter("valistr");
			if(valistr2 != null && valistr2 !=""){
				valistr2 = valistr2.toLowerCase();

				//判断验证码，在验证码不正确时，直接返回json数据提示用户
				if(! valistr1.equals(valistr2)){
					//验证码不正确时，将错误信息通过shiroLoginFailure放在request域中
					response.setCharacterEncoding("UTF-8");
					PrintWriter out = response.getWriter();
					out.println("{'msg':'×：验证码错误'}");

					out.flush();
					out.close();
					//验证码错误不对用户名密码进行验证
					return true;
				}
			}

		}
		return super.onAccessDenied(request, response, mappedValue);
	}

	/**
	 * 登陆成功时
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		
		PrintWriter out = response.getWriter();
		//根据用户名获取数据库中用户的信息
		User user = userService.getUserByUserName(token.getPrincipal().toString());
		String rememberName = request.getParameter("rememberName");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		try{
			//如果用户选择了记住用户名，
			Cookie cookie = null;
			if("OK".equals(rememberName)){
				cookie = new Cookie("remUsername",URLEncoder.encode(user.getUsername(),"utf-8"));
				cookie.setMaxAge(3600*24*30);
				cookie.setPath(httpServletRequest.getContextPath());
				httpServletResponse.addCookie(cookie);
			}else{
				//如果用户没有勾选记住用户名的选项则清空Cookie
				Cookie [] cookies = httpServletRequest.getCookies();
				for(Cookie cookieTemp : cookies){
					cookieTemp.setMaxAge(0);
					cookieTemp.setPath("/");
					httpServletResponse.addCookie(cookieTemp);
				}
			}
			
			if(user != null){
				((HttpServletRequest)request).getSession().setAttribute("username",user.getUsername());
				((HttpServletRequest)request).getSession().setAttribute("password",user.getPassword());
				((HttpServletRequest)request).getSession().setAttribute("userInfo",user);
				out.println("{'msg':'登陆成功！'}");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 登录失败时
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		if (!"XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request)
				.getHeader("X-Requested-With"))) {
			// 不是ajax请求
			setFailureAttribute(request, e);
			return true;
		}
		
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			String message = e.getClass().getSimpleName();
			
			if ("IncorrectCredentialsException".equals(message)) {
				out.println("{'msg':'×：用户名或密码不正确'}");
			} else if ("UnknownAccountException".equals(message)) {
				out.println("{'msg':'×：用户名不存在'}");
			} else if("valistrError".equals(message)){
				out.println("{'msg':'×：验证码错误'}");
			} else {
				out.println("{'msg':'×：未知错误'}");
			}
			out.flush();
			out.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
}
