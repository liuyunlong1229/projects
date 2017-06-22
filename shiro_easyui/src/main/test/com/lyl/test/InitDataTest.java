package com.lyl.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.lyl.system.entity.RoleResource;
import com.lyl.system.entity.SysResource;
import com.lyl.system.entity.SysResource.ResourceType;
import com.lyl.system.entity.SysRole;
import com.lyl.system.entity.SysUser;
import com.lyl.system.entity.UserRole;
import com.lyl.system.service.ResourceService;
import com.lyl.system.service.RoleService;
import com.lyl.system.service.UserService;

public class InitDataTest extends BaseSpringTestCase {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	public void testAddUser() {

		SysUser user1 = new SysUser();
		user1.setLoginName("tom");
		user1.setPassword("tom");
		user1.setRealName("汤姆");
		user1.setCreateDttm(new Date());
		baseDao.save(user1);

		SysUser user2 = new SysUser();
		user2.setLoginName("kat");
		user2.setPassword("kat");
		user2.setRealName("凯特");
		user2.setCreateDttm(new Date());
		baseDao.save(user2);

	}

	public void testAddRole() {

		SysRole role1 = new SysRole();
		role1.setName("管理员");
		role1.setLabel("admin");
		baseDao.save(role1);

		SysRole role2 = new SysRole();
		role2.setName("用户");
		role2.setLabel("user");
		baseDao.save(role2);

	}

	public void testAddResource() {

		SysResource ssr = new SysResource();
		ssr.setName("系统管理");
		ssr.setResourceType(ResourceType.menu);
		baseDao.save(ssr);

		// *********************************************************************************************//
		SysResource ssr1 = new SysResource();
		ssr1.setName("用户管理");
		ssr1.setResourcePath("/user/list.do");
		ssr1.setResourceType(ResourceType.menu);
		ssr1.setParent(ssr);
		baseDao.save(ssr1);

		SysResource ssr12 = new SysResource();
		ssr12.setName("用户新增");
		ssr12.setResourceType(ResourceType.button);
		ssr12.setResourcePath("/user/add.do");
		ssr12.setParent(ssr1);
		baseDao.save(ssr12);

		SysResource ssr13 = new SysResource();
		ssr13.setName("用户修改");
		ssr13.setResourceType(ResourceType.button);
		ssr13.setResourcePath("/user/update.do");
		ssr13.setParent(ssr1);
		baseDao.save(ssr13);

		SysResource ssr14 = new SysResource();
		ssr14.setName("用户删除");
		ssr14.setResourceType(ResourceType.button);
		ssr14.setResourcePath("/user/delete.do");
		ssr14.setParent(ssr1);
		baseDao.save(ssr14);

		// *********************************************************************************************//

		SysResource ssr2 = new SysResource();
		ssr2.setName("角色管理");
		ssr2.setResourceType(ResourceType.menu);
		ssr2.setResourcePath("/role/list.do");
		ssr2.setParent(ssr);
		baseDao.save(ssr2);

		SysResource ssr22 = new SysResource();
		ssr22.setName("角色新增");
		ssr22.setResourceType(ResourceType.button);
		ssr22.setResourcePath("/role/add.do");
		ssr22.setParent(ssr2);
		baseDao.save(ssr22);

		SysResource ssr23 = new SysResource();
		ssr23.setName("角色修改");
		ssr23.setResourceType(ResourceType.button);
		ssr23.setResourcePath("/role/update.do");
		ssr23.setParent(ssr2);
		baseDao.save(ssr23);

		SysResource ssr24 = new SysResource();
		ssr24.setName("角色删除");
		ssr24.setResourceType(ResourceType.button);
		ssr24.setResourcePath("/role/delete.do");
		ssr24.setParent(ssr2);
		baseDao.save(ssr24);

		// *********************************************************************************************//

	}

	public void testSetRelation() {

		SysUser user = userService.findByLoginName("tom");
		SysRole role = roleService.findByName("管理员").get(0);
		UserRole ur = new UserRole();
		ur.setUser(user);
		ur.setRole(role);
		baseDao.save(ur);

		SysResource root = resourceService.findByName("系统管理").get(0);
		RoleResource rrs = new RoleResource();
		rrs.setRole(role);
		rrs.setResource(root);
		baseDao.save(rrs);

		// 取得所有的用户资源
		List<SysResource> srr1 = resourceService.findByName("用户");

		for (SysResource sysResource : srr1) {
			rrs = new RoleResource();
			rrs.setRole(role);
			rrs.setResource(sysResource);
			baseDao.save(rrs);
		}

		// 取得所有的角色资源
		List<SysResource> srr2 = resourceService.findByName("角色");

		for (SysResource sysResource : srr2) {
			rrs = new RoleResource();
			rrs.setRole(role);
			rrs.setResource(sysResource);
			baseDao.save(rrs);
		}

		user = userService.findByLoginName("kat");
		role = roleService.findByName("用户").get(0);
		ur = new UserRole();
		ur.setUser(user);
		ur.setRole(role);
		baseDao.save(ur);

		rrs = new RoleResource();
		rrs.setRole(role);
		rrs.setResource(root);
		baseDao.save(rrs);

		List<SysResource> srr3 = resourceService.findByName("用户管理");

		for (SysResource sysResource : srr3) {
			rrs = new RoleResource();
			rrs.setRole(role);
			rrs.setResource(sysResource);
			baseDao.save(rrs);
		}

		List<SysResource> srr4 = resourceService.findByName("角色管理");

		for (SysResource sysResource : srr4) {
			rrs = new RoleResource();
			rrs.setRole(role);
			rrs.setResource(sysResource);
			baseDao.save(rrs);
		}

	}

	@Test
	@Rollback(false)
	public void init() {
		testAddUser();
		testAddRole();
		testAddResource();
		testSetRelation();

	}
}
