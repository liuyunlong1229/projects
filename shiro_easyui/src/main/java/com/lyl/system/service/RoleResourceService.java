package com.lyl.system.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyl.base.dao.IBaseDao;
import com.lyl.system.entity.SysResource;
import com.lyl.system.entity.SysRole;
import com.lyl.system.vo.TreeVo;

@Service
public class RoleResourceService {

	@Autowired
	private IBaseDao	baseDao;

	@SuppressWarnings("unchecked")
	public List<SysRole> findRolesByResource(SysResource resource) {
		String hql = "select rr.role from RoleResource rr where rr.resource.id=:rsid";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("rsid", resource.getId());
		List<SysRole> roleList = baseDao.findByHQL(hql, args);
		return roleList;

	}

	@SuppressWarnings("unchecked")
	public List<SysResource> findResourcesByRole(SysRole role) {
		String hql = "select rr.resource from RoleResource rr where rr.role.id=:rid";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("rid", role.getId());
		List<SysResource> resourceList = baseDao.findByHQL(hql, args);
		return resourceList;

	}

	@SuppressWarnings("unchecked")
	public List<TreeVo> findResourceTreeByRid(String roleId) {
		List<TreeVo> resourceTree=new ArrayList<TreeVo>();
		List roleResourcesIds=new ArrayList();
		TreeVo tv=null;
		
		if(StringUtils.isNotBlank(roleId)){
			roleResourcesIds=baseDao.findBySQL("SELECT  rs.rsid FROM t_sys_role_resource rs WHERE rs.rid='"+roleId+"'", null);
		}
		
		List<SysResource> rootResources=baseDao.findByHQL("from SysResource rs  where rs.parent is null ", null);
		
		for (SysResource rs : rootResources) {
			
			tv=construct(rs,roleResourcesIds);
			resourceTree.add(tv);
		}
		return resourceTree;
		
		
	}
	
	
	public TreeVo construct(SysResource rs,List roleResourceIds){
		
		TreeVo tv=bulidTreeVo( rs,roleResourceIds);
		TreeVo sub=null;
		if(rs.getSubList() !=null && rs.getSubList().size()>0){
			for(SysResource res:rs.getSubList()){
				sub=construct(res,roleResourceIds);
				tv.getChildren().add(sub);
			
			}
			
		}
		
		return tv;
		
	}
	
	
	public TreeVo bulidTreeVo(SysResource rs,List roleResourceIds){
		
		TreeVo tv=new TreeVo();
		tv.setId(rs.getId());
		tv.setText(rs.getName());
		tv.setChecked(roleResourceIds.contains(BigInteger.valueOf(rs.getId())));
		return tv;
		
	}
	


}
