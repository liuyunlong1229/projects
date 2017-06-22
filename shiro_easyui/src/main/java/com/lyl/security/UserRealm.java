package com.lyl.security;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.lyl.system.entity.SysResource;
import com.lyl.system.entity.SysRole;
import com.lyl.system.entity.SysUser;
import com.lyl.system.service.RoleResourceService;
import com.lyl.system.service.UserRoleService;
import com.lyl.system.service.UserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService			userService;

	@Autowired
	private UserRoleService		userRoleService;

	@Autowired
	private RoleResourceService	roleResourceService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser user = (ShiroUser) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		SysUser sysUser = new SysUser();
		sysUser.setId(user.getId());
		List<SysRole> roleList = userRoleService.findRolesByUser(sysUser);

		Set<String> roleLabels = new HashSet<String>();
		Set<String> resourceLabels = new HashSet<String>();
		if (roleList != null && roleList.size() > 0) {
			for (SysRole role : roleList) {
				roleLabels.add(role.getLabel());
				List<SysResource> resourceList = roleResourceService.findResourcesByRole(role);
				if (resourceList != null && resourceList.size() > 0) {
					for (SysResource resource : resourceList) {
						resourceLabels.add(resource.getLabel());
					}
				}

			}
		}

		authorizationInfo.setRoles(roleLabels);
		authorizationInfo.setStringPermissions(resourceLabels);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal();

		SysUser user = userService.findByLoginName(username);

		if (user == null) {
			throw new UnknownAccountException();//没找到帐号
		}

		if (Boolean.FALSE.equals(user.isEnableFlag())) {
			throw new LockedAccountException(); //帐号锁定
		}

		ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginName(), user.getRealName());

		//交给AuthenticatingRealm使用RetryLimitHashedCredentialsMatcher进行密码匹配
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(shiroUser, //用户名
				user.getPassword(), //密码
				ByteSource.Util.bytes(user.getSalt()), getName() //realm name
		);
		return authenticationInfo;
	}

	public static class ShiroUser implements Serializable {
		private static final long	serialVersionUID	= -1373760761780840081L;
		public Long					id;
		public String				loginName;
		public String				realName;

		public ShiroUser(Long id, String loginName, String realName) {
			this.id = id;
			this.loginName = loginName;
			this.realName = realName;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		public String getRealName() {
			return realName;
		}

		public void setRealName(String realName) {
			this.realName = realName;
		}
	}

}
