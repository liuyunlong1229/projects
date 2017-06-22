package com.lyl.system.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lyl.base.entity.BaseEntity;

/**
 * 角色和资源关联表
 * @author Liu YunLong
 *
 */
@Entity
@Table(name = "T_SYS_ROLE_RESOURCE")
public class RoleResource extends BaseEntity {

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rid")
	private SysRole		role;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rsid")
	private SysResource	resource;

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public SysResource getResource() {
		return resource;
	}

	public void setResource(SysResource resource) {
		this.resource = resource;
	}
}
