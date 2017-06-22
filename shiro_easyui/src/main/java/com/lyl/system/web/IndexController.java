package com.lyl.system.web;

import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyl.security.UserRealm.ShiroUser;
import com.lyl.system.entity.SysUser;
import com.lyl.system.service.ResourceService;
import com.lyl.system.vo.MenuVO;

@Controller
public class IndexController {

	@Autowired
	private ResourceService	resourceService;

	private static Logger	logger	= Logger.getLogger(IndexController.class);

	@RequestMapping(value = { "/home", "" })
	public String showHomePage() {
		return "index";
	}

	@RequestMapping(value = "/index/init")
	@ResponseBody
	public Set<MenuVO> init() {

		//获得认证对象里面的实体
		//Object principal = SecurityUtils.getSubject().getPrincipal();
		//String userName = principal.toString();
		//logger.debug("从上下文获得用户名====" + principal.toString());
		Object principal = SecurityUtils.getSubject().getPrincipal();
		ShiroUser shiroUser = (ShiroUser) principal;
		SysUser user = new SysUser();
		user.setId(shiroUser.getId());
		Set<MenuVO> menus = resourceService.findMenusByUser(user);
		return menus;

	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

}
