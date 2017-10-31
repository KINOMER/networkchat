package com.hsu.netchat.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hsu.netchat.bean.Msg;
import com.hsu.netchat.bean.User;
import com.hsu.netchat.service.UserService;
import com.hsu.netchat.utils.MD5Util;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	/**
	 * 跳转到登录页面
	 */
	@RequestMapping("/login1")
	public String forwardIndexpage(){
		return "login1";
	} 

	/**
	 * 跳转到注册页面
	 */
	@RequestMapping("regist")
	public String regist(){
		return "regist";
	}
	/**
	 * 跳转到主页面
	 */
	@RequestMapping("/index")
	public String forwardIndexPage(){
		return "index";
	}
	/**
	 * 用户注册校验用户名 ， 采用占位符的形式接受用户名参数
	 * 为什么会出现乱码？
	 */
	@RequestMapping(value = "/checkUsername/{username}",method=RequestMethod.POST)
	@ResponseBody
	public Msg checkUsername( @PathVariable("username") String username){

		username = username.split("-")[1];
		try {
			username = new String(username.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//System.out.println(username);

		//后端校验用户名
		String usernameRegx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!username.matches(usernameRegx)){
			return Msg.fail("用户名必须是2-5位中文或者6-16位英文和数字的组合!");
		}

		User user = userService.getUserByUserName(username);
		if(user != null){	
			return Msg.fail("用户名已存在！"); 
		}
		return Msg.success("用户名可用！");
	}

	/**
	 * 用户注册
	 */
	@RequestMapping(value = "/regist",method=RequestMethod.POST)
	@ResponseBody
	public Msg regist(@Valid User user,BindingResult result){
		
		//防止表单重复提交
		User thisUser = user;
		if(thisUser != null){
			thisUser = userService.getUserByUserName(user.getUsername());
			if(thisUser != null){
				return Msg.fail("用户名已存在!").add("errors", "");
			}
		}

		//后台验证数据错误，反馈给前台
		if(result.hasErrors()){
			Map<String ,Object> map = new HashMap<String ,Object>();
			List <FieldError> errors = result.getFieldErrors();
			for(FieldError error : errors){
				map.put(error.getField(), error.getDefaultMessage());
			}
			return Msg.fail("验证信息错误!").add("errors", map);
		}else{
			//注册时将用户密码加密
			user.setPassword(MD5Util.md5(user.getPassword()));
			userService.insertUser(user);
			return Msg.success("用户注册成功，请登录！");
		}
	}

	/**
	 * 用户登录到主页面
	 */
	@RequestMapping(value = "/login") 
	public String login(User user,HttpServletRequest request){
		
		/*	user = (userService.getUserByUserName(user.getUsername()));
		request.getSession().setAttribute("userInfo", user);*/
		//将用户名和密码放在session中，在进行用户信息修改时使session域中的用户信息完整
		/*request.getSession().setAttribute("username", user.getUsername());
		request.getSession().setAttribute("password", user.getPassword());*/
		//System.out.println(request.getSession().getAttribute("userInfo"));

		//如果登陆失败从request中获取认证异常信息，shiroLoginFailure就是shiro异常类的全限定名
		//System.out.println(user.getUsername()+"......");

		return "login";
	}

	/**
	 * 如果该浏览器已经有用户登录，跳转到错误页面进行提示
	 */
	@RequestMapping("/forwardErrorPage")
	public String errorPage(){
		return "errorPage";
	}
	/**
	 * 转发到用户信息修改页面
	 */
	@RequestMapping("/userInfoSet")
	public String userInfo(){
		return "infoSetting";
	}

	/**
	 * 查看个人信息
	 */  
	@RequestMapping("userInfo")
	public String listUserInfo(){
		return "userInfo";
	}
	/**
	 * 修改用户信息,返回json数据进行提示
	 */
	@RequestMapping(value="/userInfo",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateUserInfo(User user,HttpServletRequest request){
		userService.updateUserInfo(user);
		user.setUsername((String) request.getSession().getAttribute("username"));
		user.setPassword((String) request.getSession().getAttribute("password"));
		//System.out.println(user);
		//由于不同步修改用户的头像信息，所谓在提交修改信息时，session域中的头像为空，
		//再从数据库中查询完整的包含头像信息的用户信息
		user = userService.getUserByUserName(user.getUsername());
		request.getSession().setAttribute("userInfo", user);
		return Msg.success("修改信息成功！");
	}

	/**
	 * 修该用户头像
	 */
	
	@RequestMapping(value="uploadPic",method=RequestMethod.POST)
	@ResponseBody
	public Msg uploadUserAvator(HttpServletRequest request, HttpServletResponse response){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request; 
		MultipartFile imgFile = multipartRequest.getFile("avator");
		try {
			byte[] avator = imgFile.getBytes();
			User user = (User) request.getSession().getAttribute("userInfo");

			user.setAvator(avator);
			userService.uploadAdator(user);
			request.getSession().setAttribute("userInfo", user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("上传成功");
		return Msg.success("上传成功！");
	}
	/**
	 * 向浏览器输出用户头像
	 */
	@RequestMapping("/readUserAvator")
	public void readUserAvator(HttpServletRequest request,HttpServletResponse response){
		User user = (User) request.getSession().getAttribute("userInfo");
		try{
			response.setContentType("image/jpeg");
			response.setCharacterEncoding("utf-8");  

			OutputStream outputStream = response.getOutputStream();  
			if(user.getAvator()!=null){
				user = userService.getUserByUserName(user.getUsername());
				InputStream in = new ByteArrayInputStream(user.getAvator());  

				int len = 0;  
				byte[] buf = new byte[1024];  
				while((len = in.read(buf,0,1024)) != -1){  
					outputStream.write(buf, 0, len);
				}  
			}
		} catch (IOException e) {  
			e.printStackTrace();  
		}
	}
	
}

