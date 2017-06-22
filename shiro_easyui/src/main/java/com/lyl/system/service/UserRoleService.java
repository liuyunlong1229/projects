package com.lyl.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyl.base.dao.IBaseDao;
import com.lyl.system.entity.SysRole;
import com.lyl.system.entity.SysUser;
import com.lyl.system.entity.UserRole;
import com.lyl.system.vo.ComboboxVo;

@Service
public class UserRoleService {

	@Autowired
	private IBaseDao	baseDao;

	public void saveUserRole(UserRole ur) {
		baseDao.save(ur);

	}
	
	public List<SysRole> findRolesByUser(SysUser user){
		Map<String,Object> args=new HashMap<String,Object>();
		
		StringBuffer hql=new StringBuffer("select ur.role from UserRole ur where 1=1 ");
		if(user.getId() !=null){
			hql.append(" and ur.user.id=:uid");
			args.put("uid", user.getId());
		}
		if(StringUtils.isNotBlank(user.getLoginName())){
			hql.append(" and ur.user.loginName=:loginName");
			args.put("loginName", user.getLoginName());
		}
		
		List<SysRole> roleList=baseDao.findByHQL(hql.toString(), args);
		return roleList;
		
	}
	
	

	public List<ComboboxVo> findRoleComboboxVos(Long  userId) {
		
		List<SysRole> allRoles=baseDao.findAll(SysRole.class);
		List<ComboboxVo> roleComboboxVos=new ArrayList<ComboboxVo>();
		ComboboxVo cv=null;
		
		List<SysRole> hasRoles=null;
		if(userId !=-1){
				hasRoles=findRolesByUserId(userId);
		}
		if(allRoles !=null && allRoles.size()>0){
			for (SysRole sysRole : allRoles) {
				cv=new ComboboxVo(sysRole.getId().toString(),sysRole.getName());
				if(hasRoles !=null && hasRoles.size()>0){
					for (SysRole role : hasRoles) {
						if(role.getId()==sysRole.getId()){
							cv.setSelected(true);
						}
					}
				}
				roleComboboxVos.add(cv);
			}
		}
		
		return roleComboboxVos;
	}
	
	public List<SysRole> findRolesByUserId(Long  userId){
		SysUser user=new SysUser();
		user.setId(userId);
		List<SysRole> roleList=findRolesByUser(user);
		return roleList;
		
	}
	

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
