package com.hsu.netchat.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsu.netchat.bean.User;
import com.hsu.netchat.service.UserService;

public class UserRealm extends AuthorizingRealm {

	// 设置realm的名称
	@Override
	public void setName(String name) {
		super.setName("userRealm");
	}

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 从token中获取用户名
		String username = (String) token.getPrincipal();
		// 根据用户名查询数据库获取所有用户信息
		User user = userService.getUserByUserName(username);

		if(user == null){
			return null;
		}
		// 从数据库查询到密码
		String password = user.getPassword();
		// 将user设置simpleAuthenticationInfo
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				user, password, this.getName());
		
		return simpleAuthenticationInfo;
	}

}
