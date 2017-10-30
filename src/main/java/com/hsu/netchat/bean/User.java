package com.hsu.netchat.bean;

import java.io.Serializable;
import java.util.Arrays;

import javax.validation.constraints.Pattern;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer userId;

	@Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})",
			message="用户名必须是2-5位中文或者6-16位英文和数字的组合")
    private String username;
	@Pattern(regexp="(^(\\w){6,16}$)",
			message="输入密码不合法！")
    private String password;

    private Integer sex;

    private Integer age;

    private String email;

    private String phone;

    private String autograph;

    private String summary;

    private byte[] avator;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph == null ? null : autograph.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public byte[] getAvator() {
        return avator;
    }

    public void setAvator(byte[] avator) {
        this.avator = avator;
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", sex=" + sex
				+ ", age=" + age + ", email=" + email + ", phone=" + phone + ", autograph=" + autograph + ", summary="
				+ summary + ", avator=" + Arrays.toString(avator) + "]";
	}
    
}