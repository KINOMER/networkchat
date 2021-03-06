package com.hsu.netchat.controller;

import com.hsu.netchat.bean.Msg;
import com.hsu.netchat.bean.User;
import com.hsu.netchat.service.AnnoService;
import com.hsu.netchat.service.UserService;
import com.hsu.netchat.utils.MD5Util;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AnnoService annoService;

    /**
     * 转发到用户登录页面[考虑到用户第一次登录界面，注销退出页面(没有session值)，或者直接×掉页面(session未过期)两种情况]
     * 用户在没有登录网站的情况下不会存有session值，直接转发到登录页面
     * 用户在登陆以后并不是通过注销方式退出网站(本地存有session值)，再登录时应该直接重定向到主页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request){
        User userInfo = (User) request.getSession().getAttribute("userInfo");
        String anno = (String) request.getSession().getAttribute("anno");
        
        if(anno == null){
            request.getSession().setAttribute("anno", annoService.getAnnoMsg().get(0));
        }
        
        if(userInfo != null){
            return "redirect:/index";
        }

        return "login";
    }

    @RequestMapping("/regist")
    public String regist(){
        return "regist";
    }

    @RequestMapping("/index")
    public String forwardIndexPage(){
        return "index";
    }

    @RequestMapping(value = "/checkUsername/{username}",method=RequestMethod.POST)
    @ResponseBody
    public Msg checkUsername( @PathVariable("username") String username) {
        username = username.split("-")[1];
        // 后端校验用户名
        String usernameRegx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        
        try {
            username = new String(username.getBytes("iso8859-1"),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        if(!username.matches(usernameRegx)) {
            return Msg.fail("×：用户名必须是2-5位中文或者6-16位英文和数字的组合");
        }

        User user = userService.getUserByUserName(username);
        if(user != null){
            return Msg.fail("×：用户名已存在");
        }

        return Msg.success("√：用户名可用");
    }

    @RequestMapping(value = "/regist",method=RequestMethod.POST)
    @ResponseBody
    public Msg regist(@Valid User user,BindingResult result) {
        // 防止表单重复提交
        User thisUser = user;
        if(thisUser != null){
            thisUser = userService.getUserByUserName(user.getUsername());
            if(thisUser != null){
                return Msg.fail("×：用户名已存在").add("errors", "");
            }
        }

        // 后台验证数据错误，反馈给前台
        if(result.hasErrors()){
            Map<String, Object> map = new HashMap<String, Object>();
            List <FieldError> errors = result.getFieldErrors();

            for(FieldError error : errors){
                map.put(error.getField(), error.getDefaultMessage());
            }
            
            return Msg.fail("验证信息错误!").add("errors", map);
        }else{
            // 注册时将用户密码加密
            user.setPassword(MD5Util.md5(user.getPassword()));
            userService.insertUser(user);
            
            return Msg.success("用户注册成功，请登录！");
        }
    }

    @RequestMapping(value="/userInfo", method=RequestMethod.PUT)
    @ResponseBody
    public Msg updateUserInfo(User user, HttpServletRequest request) {
        userService.updateUserInfo(user);
        // 由于不同步修改用户的头像信息，所谓在提交修改信息时，session域中的头像为空，
        // 再从数据库中查询完整的包含头像信息的用户信息
        user = userService.getUserByUserName((String) request.getSession().getAttribute("username"));
        request.getSession().setAttribute("userInfo", user);
        
        return Msg.success("修改信息成功！");
    }

    @RequestMapping(value="/uploadPic", method=RequestMethod.POST)
    @ResponseBody
    public Msg uploadUserAvator(HttpServletRequest request, HttpServletResponse response) {
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

        return Msg.success("上传成功！");
    }
    
    @RequestMapping("/readUserAvator")
    public void readUserAvator(HttpServletRequest request, HttpServletResponse response) {
        try{
            response.setContentType("image/jpeg");
            response.setCharacterEncoding("utf-8");

            User user = (User) request.getSession().getAttribute("userInfo");
            OutputStream outputStream = response.getOutputStream();
            if(user.getAvator()!=null){
                user = userService.getUserByUserName(user.getUsername());
                InputStream in = new ByteArrayInputStream(user.getAvator());
                int len = 0;
                byte[] buf = new byte[1024];

                while((len = in.read(buf,0,1024)) != -1) {
                    outputStream.write(buf, 0, len);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

