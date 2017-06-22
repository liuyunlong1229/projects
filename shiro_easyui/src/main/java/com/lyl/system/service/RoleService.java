package com.lyl.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyl.base.dao.IBaseDao;
import com.lyl.base.utils.PageBean;
import com.lyl.system.entity.RoleResource;
import com.lyl.system.entity.SysResource;
import com.lyl.system.entity.SysRole;

@Service
@Transactional
public class RoleService {

	@Autowired
	private IBaseDao	baseDao;
	
	
	@SuppressWarnings("unchecked")
	public List<SysRole> findRolesWithPage(SysRole role,PageBean page) {
		StringBuffer hql=new StringBuffer(" from SysRole role where 1=1");
		Map<String,Object> args=new HashMap<String,Object>();
		if(StringUtils.isNotBlank(role.getName())){
			hql.append(" and role.name like :name");
			args.put("name", "'%"+role.getName()+"%'");
		}
		if(StringUtils.isNotBlank(role.getLabel())){
			hql.append(" and role.label =:label");
			args.put("label",role.getLabel());
		}
		return baseDao.findPageByHQL(hql.toString(), args, page);
	}
	

	public List<SysRole> findAll() {
		return baseDao.findAll(SysRole.class);
	}
	
	
	public SysRole findRoleById(Long id) {
		return baseDao.getById(SysRole.class, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<SysRole> findByName(String roleName) {
		String hql = "from SysRole sr where sr.name like:roleName ";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("roleName", "%" + roleName + "%");
		List<SysRole> resouceList = baseDao.findByHQL(hql, args);
		return resouceList;

	}
	

	
	

	public void saveRole(SysRole role) {
		if (StringUtils.isNotBlank(role.getResourceIds())) {
			String [] rsids=role.getResourceIds().split(",");
			for (int i = 0; i < rsids.length; i++) {
				SysResource resource = baseDao.getById(SysResource.class, Long.parseLong(rsids[i]));
				RoleResource rr = new RoleResource();
				rr.setRole(role);
				rr.setResource(resource);
				baseDao.save(rr);
			}
		}
		baseDao.save(role);
	}

	
	
	



	public void updateRole(SysRole role) {

		String sql = "delete from T_SYS_ROLE_RESOURCE where rid= " + role.getId();
		baseDao.execSQL(sql);

		if (StringUtils.isNotBlank(role.getResourceIds())) {
			String [] rsids=role.getResourceIds().split(",");
			for (int i = 0; i < rsids.length; i++) {
				SysResource resource = baseDao.getById(SysResource.class, Long.parseLong(rsids[i]));
				RoleResource rr = new RoleResource();
				rr.setRole(role);
				rr.setResource(resource);
				baseDao.save(rr);
			}
		}
		baseDao.update(role);

	}
	
	public void deleteRoles(String ids) {
		String sql = "delete from T_SYS_ROLE_RESOURCE where rid in ("+ids+")";
		baseDao.execSQL(sql);
		sql = "delete from T_SYS_USER_ROLE where rid in ("+ids+")";
		baseDao.execSQL(sql);
		sql = "delete from T_SYS_ROLE where id in ("+ids+")";
		baseDao.execSQL(sql);
	}
		

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	

}
