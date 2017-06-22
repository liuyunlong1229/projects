package com.lyl.base.utils.taglib;



import java.util.Collection;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.lyl.base.utils.SpringUtils;
import com.lyl.system.entity.SysResource;
import com.lyl.system.entity.SysRole;
import com.lyl.system.service.ResourceService;
import com.lyl.system.service.RoleService;
import com.lyl.system.service.UserRoleService;


public class Functions {


    public static boolean in(Iterable iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }



    public static String roleName(Long roleId) {
    	 SysRole role = getRoleService().findRoleById(roleId);
        if(role == null) {
            return "";
        }
        return role.getDescription();
    }
    
    public static String roleNames(Long userId ){
    	List<SysRole> roleList=getUserRoleService().findRolesByUserId(userId);
    	
    	String roleNames="";
    	for(int i=0;i<roleList.size();i++){
    		if(i==0){
    			roleNames=roleNames+roleList.get(0).getName();
    		}else{
    			roleNames=","+roleNames+roleList.get(i).getName();
    		}
    	}
    	return roleNames;
    }

    public static String roleNames(Collection<Long> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long roleId : roleIds) {
            SysRole role = getRoleService().findRoleById(roleId);
            if(role == null) {
                return "";
            }
            s.append(role.getDescription());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }
    public static String resourceName(Long resourceId) {
    	 SysResource resource = getResourceService().findResourceById(resourceId);
        if(resource == null) {
            return "";
        }
        return resource.getName();
    }
    public static String resourceNames(Collection<Long> resourceIds) {
        if(CollectionUtils.isEmpty(resourceIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long resourceId : resourceIds) {
            SysResource resource = getResourceService().findResourceById(resourceId);
            if(resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }

    private static RoleService roleService;
    private static UserRoleService userRoleService;
    private static ResourceService resourceService;


    public static RoleService getRoleService() {
        if(roleService == null) {
            roleService = SpringUtils.getBean(RoleService.class);
        }
        return roleService;
    }

    public static ResourceService getResourceService() {
        if(resourceService == null) {
            resourceService = SpringUtils.getBean(ResourceService.class);
        }
        return resourceService;
    }
    
    public static UserRoleService getUserRoleService() {
        if(userRoleService == null) {
        	userRoleService = SpringUtils.getBean(UserRoleService.class);
        }
        return userRoleService;
    }
}

