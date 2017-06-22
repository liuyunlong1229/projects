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

import com.lyl.system.entity.SysRole;

import com.lyl.system.service.ResourceService;
import com.lyl.system.service.RoleResourceService;
import com.lyl.system.service.RoleService;
import com.lyl.system.vo.TreeVo;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

	private static Logger	logger	= Logger.getLogger(RoleController.class);

	@Autowired
	RoleService				roleService;

	@Autowired
	ResourceService			resourceService;

	@Autowired
	RoleResourceService		roleResourceService;

	
	
	
	@RequestMapping(value = "/home")
	public String showPage() {
		return "/role/roleList";

	}
	
	
	@RequestMapping(value = { "/list", "" })
	@ResponseBody
	public ModelMap list(SysRole role, Integer rows,Integer page)  {
		PageBean pageBean = new PageBean(rows, page);
		List<SysRole> roleList = roleService.findRolesWithPage(role, pageBean);
		ModelMap modelMap = new ModelMap();
		modelMap.put("rows", roleList);// 数据表格数据传递
		modelMap.put("total", pageBean.getTotalCount());// 统计条数传递
		return modelMap;

	}
	
	@RequestMapping(value = "/findResourceTree", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeVo> findResourceTree(String  roleId){
		List<TreeVo> resourceTree=roleResourceService.findResourceTreeByRid(roleId);
		return resourceTree;
		
	}
	
	

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "/role/roleAdd";
	}
	

	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(SysRole role) {
		roleService.saveRole(role);
		return true;
	}


	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam("id") Long rid, Model model) {
		logger.debug("修改的角色编号为==" + rid);
		SysRole role = roleService.findRoleById(rid);
		model.addAttribute("role", role);
		return "/role/roleUpdate";
	}

	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public boolean update(SysRole role) {
		roleService.updateRole(role);
		return true;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@RequestParam("ids")String ids) {
		logger.debug("删除的角色编号为==" + ids);
		roleService.deleteRoles(ids);
		return "success";
	}

}
