package com.lyl.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyl.base.dao.IBaseDao;
import com.lyl.base.utils.PageBean;
import com.lyl.security.PasswordHelper;
import com.lyl.system.entity.SysRole;
import com.lyl.system.entity.SysUser;
import com.lyl.system.entity.UserRole;

@Service
@Transactional
public class UserService {

	@Autowired
	private IBaseDao	baseDao;
	
	@Autowired
	PasswordHelper passwordHelper;

	public SysUser findUserById(Long Id) {
		return baseDao.getById(SysUser.class, Id);
	}

	@SuppressWarnings("unchecked")
	public SysUser findByLoginName(String loginName) {
		String hql = "from SysUser where loginName =:loginName";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("loginName", loginName);
		List<SysUser> userList = baseDao.findByHQL(hql, args);
		SysUser user = null;
		if (userList != null && userList.size() > 0) {
			user = userList.get(0);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<SysUser> findAllUserWithPage(SysUser user,PageBean page) {
		StringBuffer hql = new StringBuffer("from SysUser user where 1=1 ");
		Map<String,Object>  args=new HashMap<String,Object>();
		if(StringUtils.isNotBlank(user.getLoginName())){
			hql.append(" and user.loginName like '%"+user.getLoginName()+"%'");
		}
		if(StringUtils.isNotBlank(user.getEmail())){
			hql.append(" and user.email=:email");
			args.put("email", user.getEmail());
			
		}
		List<SysUser> userList =baseDao.findPageByHQL(hql.toString(), args, page);
		
		return userList;
	}

	@Transactional
	public void saveUser(SysUser user) {
		passwordHelper.encryptPassword(user);
		String [] roleIdArr=user.getRolesIdArr();
		if (roleIdArr !=null && roleIdArr.length>0) {
			for (int i = 0; i < roleIdArr.length; i++) {
				SysRole role = baseDao.getById(SysRole.class,Long.parseLong(roleIdArr[i]));
				UserRole ur = new UserRole();
				ur.setUser(user);
				ur.setRole(role);
				baseDao.save(ur);
			}

		}
		baseDao.save(user);

	}

	public void updateUser(SysUser user) {

		String sql = "delete from T_SYS_USER_ROLE where uid= " + user.getId();
		baseDao.execSQL(sql);
		String [] roleIdArr=user.getRolesIdArr();
		for (int i = 0; i < roleIdArr.length; i++) {
			SysRole role = baseDao.getById(SysRole.class, Long.parseLong(roleIdArr[i]));
			UserRole ur = new UserRole();
			ur.setUser(user);
			ur.setRole(role);
			baseDao.save(ur);
		}
		baseDao.update(user);

	}
	
	
	@SuppressWarnings("unchecked")
	public void resetPwdWithBatch(String uids) {
		Map<String,Object>  args=new HashMap<String,Object>();
		String [] idArr=uids.split(",");
		List<Long> ids=new ArrayList<Long>();
		for (String id : idArr) {
			ids.add(Long.parseLong(id));
		}
		args.put("uids", ids);
		List<SysUser> userList=baseDao.findByHQL("from SysUser u where u.id in(:uids)", args);
		for (SysUser sysUser : userList) {
			sysUser.setPassword("000000");
			passwordHelper.encryptPassword(sysUser);
			baseDao.update(sysUser);
		}
		
		
	}

	public void deleteUser(String uid) {
		String sql = "delete from T_SYS_USER_ROLE where uid "+uid;
		baseDao.execSQL(sql);
		sql = "delete from T_SYS_USER where id = "+uid;
		baseDao.execSQL(sql);
	}

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}


	

}
