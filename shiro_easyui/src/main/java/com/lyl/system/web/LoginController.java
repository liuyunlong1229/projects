package com.lyl.system.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyl.security.PasswordHelper;
import com.lyl.system.entity.SysUser;
import com.lyl.system.service.UserService;

@Controller
public class LoginController {

	Logger			logger	= Logger.getLogger(LoginController.class);

	@Autowired
	UserService		userService;

	@Autowired
	PasswordHelper	passwordHelper;

	@RequestMapping(value = "/login")
	public String showLoginForm(HttpServletRequest req, Model model) {

		SysUser user = new SysUser();

		user.setPassword("admin");
		passwordHelper.encryptPassword(user);
		logger.debug("password==admin\tsalt==" + user.getSalt() + "\t password==" + user.getPassword());

		user.setPassword("zs");
		passwordHelper.encryptPassword(user);
		logger.debug("password==zs\tsalt==" + user.getSalt() + "\t password==" + user.getPassword());

		user = new SysUser();
		user.setPassword("ls");
		passwordHelper.encryptPassword(user);
		logger.debug("password==ls\tsalt==" + user.getSalt() + "\t password==" + user.getPassword());

		user = new SysUser();
		user.setPassword("ww");
		passwordHelper.encryptPassword(user);
		logger.debug("password==ww\tsalt==" + user.getSalt() + "\t password==" + user.getPassword());

		user = new SysUser();
		user.setPassword("ml");
		passwordHelper.encryptPassword(user);
		logger.debug("password==ml\tsalt==" + user.getSalt() + "\t password==" + user.getPassword());

		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (exceptionClassName != null) {
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				error = "用户名/密码错误";

			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				error = "用户名/密码错误";

			} else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
				error = "尝试的次数过多，已经锁定，请一分钟后再试";

			} else {
				error = "其他错误：" + exceptionClassName;
			}
		}
		model.addAttribute("error", error);

		return "login";

	}

}
