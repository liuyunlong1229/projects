package com.lyl.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.lyl.base.dao.IBaseDao;
import com.lyl.system.entity.SysResource;
import com.lyl.system.entity.SysUser;
import com.lyl.system.vo.MenuVO;
import com.lyl.system.vo.ResourceVO;

@Service
public class ResourceService {

	@Autowired
	private IBaseDao	baseDao;

	public SysResource findResourceById(Long Id) {
		return baseDao.getById(SysResource.class, Id);
	}

	public List<ResourceVO> findAllResourceVos() {

		List<SysResource> allResource = baseDao.findAll(SysResource.class);
		List<ResourceVO> allResourceVos = new ArrayList<ResourceVO>();
		ResourceVO rv = null;
		for (SysResource rs : allResource) {
			rv = new ResourceVO();
			rv.setId(rs.getId().toString());
			rv.setName(rs.getName());
			rv.setLabel(rs.getLabel());
			rv.setIcon(rs.getIcon());
			rv.setIdPath(rs.getIdPath());
			rv.set_parentId(rs.getParentId().toString());
			rv.setResourcePath(rs.getResourcePath());
			rv.setResourceType(rs.getResourceType().getText());
			rv.setIsLeaf(rs.isLeaf());
			allResourceVos.add(rv);
		}
		//
		//		Collections.sort(allResourceVos, new Comparator<ResourceVO>() {
		//			@Override
		//			public int compare(ResourceVO o1, ResourceVO o2) {
		//				return o1.getIdPath().compareTo(o2.getIdPath());
		//			}
		//		});

		return allResourceVos;

	}

	public void createResource(SysResource resource) {

		if (resource.getParent() != null && resource.getParent().getId() != null) {
			SysResource parent = baseDao.getById(SysResource.class, resource.getParent().getId());
			resource.setParent(parent);
		}
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		Long id = keyHolder.getKey().longValue();
		final String sql = "insert into sys_resource(name, type, url, permission, parent_id, parent_ids, available) values(?,?,?,?,?,?,?)";

	}

	public void saveResource(SysResource resource) {

		if (resource.getParent() == null || resource.getParent().getId() == null || resource.getParent().getId() == 0) {
			resource.setResourceType(SysResource.ResourceType.module);
			resource.setParent(null);
		} else {
			SysResource parent = baseDao.getById(SysResource.class, resource.getParent().getId());
			resource.setParent(parent);
			resource.setResourceType(parent.getChildType());
		}

		baseDao.save(resource);

	}

	public void updateResource(SysResource resource) {
		if (resource.getParent() == null || resource.getParent().getId() == null || resource.getParent().getId() == 0) {
			resource.setResourceType(SysResource.ResourceType.module);
			resource.setParent(null);
		} else {
			SysResource parent = baseDao.getById(SysResource.class, resource.getParent().getId());
			resource.setParent(parent);
			resource.setResourceType(parent.getChildType());
		}
		baseDao.update(resource);

	}

	@SuppressWarnings("unchecked")
	public Set<MenuVO> findMenusByUser(SysUser user) {

		//查询用户所有角色
		List<Object> roleIds = baseDao.findBySQL("select ur.rid from t_sys_user_role ur where ur.uid='" + user.getId()
				+ "'", null);

		Set<MenuVO> resources = new HashSet<MenuVO>();
		MenuVO parent = null;
		MenuVO child = null;

		for (Object obj : roleIds) {
			String roleId = obj.toString();

			//查询角所有模块
			String hql = "select rr.resource from RoleResource rr where rr.resource.parent.id is null and rr.resource.resourceType='module' and rr.role.id="
					+ roleId;
			List<SysResource> modules = baseDao.findByHQL(hql, null);
			for (SysResource module : modules) {
				parent = coverResource2MenuVo(module);

				//查询角色当前模块下的菜单
				hql = "SELECT rr.resource FROM RoleResource rr WHERE rr.resource.parent.id =" + module.getId()
						+ " AND rr.resource.resourceType='menu' AND rr.role.id=" + roleId;
				List<SysResource> menus = baseDao.findByHQL(hql, null);

				for (SysResource sub : menus) {
					child = coverResource2MenuVo(sub);
					parent.getSubList().add(child);
				}

				resources.add(parent);
			}

		}

		return resources;
	}

	private MenuVO coverResource2MenuVo(SysResource resource) {
		MenuVO menu = new MenuVO();
		menu.setId(resource.getId().toString());
		menu.setName(resource.getName());
		menu.setResourcePath(resource.getResourcePath());
		menu.setIcon(resource.getIcon());
		return menu;

	}

	public List<SysResource> findAll() {

		//final String sql = "select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource order by concat(parent_ids, id) asc";
		//	        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Resource.class));
		return baseDao.findAll(SysResource.class);
	}

	public List<SysResource> findByName(String resName) {
		String hql = "from SysResource rs where rs.name like:rsName ";
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("rsName", "%" + resName + "%");
		List<SysResource> resouceList = baseDao.findByHQL(hql, args);
		return resouceList;

	}

	public void deleteResource(SysResource resource) {

		resource = baseDao.getById(SysResource.class, resource.getId());
		List<Long> idList = new ArrayList<Long>();
		getAllIds(resource, idList);
		for (Long id : idList) {
			baseDao.execSQL("delete from  T_SYS_ROLE_RESOURCE where rsid= " + id); //角色资源表
			baseDao.execSQL("delete from  T_SYS_ORG_RESOURCE where rsid= " + id); //组织资源表
			baseDao.execSQL("delete from  T_SYS_RESOURCE where id= " + id); //资源表
		}
	}

	public void getAllIds(SysResource resource, List<Long> idList) {
		if (!CollectionUtils.isEmpty(resource.getSubList())) {
			for (SysResource rs : resource.getSubList()) {
				getAllIds(rs, idList);
			}
		}
		idList.add(resource.getId());

	}

	public IBaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public static void main(String[] args) {

	}

}
