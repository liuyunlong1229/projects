package com.lyl.system.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyl.base.utils.PageBean;
import com.lyl.system.entity.SysUser;
import com.lyl.system.service.RoleService;
import com.lyl.system.service.UserRoleService;
import com.lyl.system.service.UserService;
import com.lyl.system.vo.ComboboxVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static Logger	logger	= Logger.getLogger(UserController.class);

	@Autowired
	UserService				userService;

	@Autowired
	RoleService				roleService;

	@Autowired
	UserRoleService			userRoleService;

	@RequestMapping(value = "/home")
	public String showPage() {
		return "/user/userList";

	}

	@RequestMapping(value = "/list")
	@ResponseBody
	public ModelMap list(SysUser user, Integer rows, Integer page) {
		PageBean pageBean = new PageBean(rows, page);
		List<SysUser> userList = userService.findAllUserWithPage(user, pageBean);
		ModelMap modelMap = new ModelMap();
		modelMap.put("rows", userList);// 数据表格数据传递
		modelMap.put("total", pageBean.getTotalCount());// 统计条数传递
		return modelMap;

	}

	@RequestMapping(value = "/findComboboxVos")
	@ResponseBody
	public List<ComboboxVo> findComboboxVos(@RequestParam("uid") Long userId) {
		return userRoleService.findRoleComboboxVos(userId);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		SysUser user = new SysUser();
		model.addAttribute("user", user);
		return "/user/userAdd";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(SysUser user) {
		userService.saveUser(user);
		return true;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam("id") Long uid, Model model) {
		logger.debug("修改的用户id==" + uid);
		SysUser user = userService.findUserById(uid);
		model.addAttribute("user", user);
		return "/user/userUpdate";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(SysUser user) {
		userService.updateUser(user);
		return true;

	}

	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	@ResponseBody
	public String resetPwd(@RequestParam("ids") String uids) {
		userService.resetPwdWithBatch(uids);
		return "success";

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam("ids") String ids) {
		logger.debug("删除的用户 ids==" + ids);
		userService.deleteUser(ids);
		return "success";
	}
}
