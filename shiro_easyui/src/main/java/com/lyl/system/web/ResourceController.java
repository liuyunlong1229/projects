package com.lyl.system.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lyl.system.entity.SysResource;
import com.lyl.system.service.ResourceService;
import com.lyl.system.service.RoleResourceService;
import com.lyl.system.service.RoleService;
import com.lyl.system.vo.ResourceVO;

@Controller
@RequestMapping(value = "/resource")
public class ResourceController {

	private static Logger	logger	= Logger.getLogger(ResourceController.class);

	@Autowired
	RoleService				roleService;

	@Autowired
	ResourceService			resourceService;

	@Autowired
	RoleResourceService		roleResourceService;

	@ModelAttribute("types")
	public SysResource.ResourceType[] resourceTypes1() {
		//return SysResource.ResourceType.values();
		SysResource.ResourceType resTypes[] = new SysResource.ResourceType[2];
		resTypes[0] = SysResource.ResourceType.button;
		resTypes[1] = SysResource.ResourceType.menu;
		return resTypes;
	}

	@ModelAttribute("allTypes")
	public SysResource.ResourceType[] allResourceTypes() {
		return SysResource.ResourceType.values();

	}

	@RequestMapping(value = "/home")
	public String showPage() {
		return "/resource/resourceList";

	}

	@RequestMapping(value = { "/list.do", "" })
	@ResponseBody
	public ModelMap list() {

		List<ResourceVO> allResourceVos = resourceService.findAllResourceVos();
		ResourceVO root = new ResourceVO("0", "系统资源", "", SysResource.ResourceType.root.getText(), "", "", "", "");
		allResourceVos.add(0, root);
		ModelMap modelMap = new ModelMap();
		modelMap.put("rows", allResourceVos);// 数据表格数据传递
		modelMap.put("total", allResourceVos.size());// 统计条数传递
		Map<String, String> footer = new HashMap<String, String>();
		footer.put("name", "总记录数");
		footer.put("resourceType", allResourceVos.size() + "条");
		footer.put("iconCls", "icon-sum");
		List list = new ArrayList();
		list.add(footer);
		modelMap.put("footer", list);
		return modelMap;

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Long rsid, Model model) {
		SysResource parent = null;
		SysResource resource = new SysResource();
		if (rsid == 0) {
			parent = new SysResource();
			parent.setId(0L);
			parent.setName("系统资源");
			parent.setResourceType(SysResource.ResourceType.root);
		} else {
			parent = resourceService.findResourceById(rsid);
		}

		resource.setResourceType(parent.getChildType());
		resource.setParent(parent);

		model.addAttribute("resource", resource);
		return "/resource/resourceAdd";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public boolean add(SysResource resource) {
		resourceService.saveResource(resource);
		return true;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Long rsid, Model model) {
		logger.debug("修改的资源编号为==" + rsid);
		SysResource resource = resourceService.findResourceById(rsid);
		model.addAttribute("resource", resource);
		return "/resource/resourceUpdate";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(SysResource resource) {
		resourceService.updateResource(resource);
		return "redirect:/resource";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(Long rsid) {
		logger.debug("删除的资源编号为==" + rsid);
		SysResource resource = new SysResource();
		resource.setId(rsid);
		resourceService.deleteResource(resource);
		return "success";
	}

}
