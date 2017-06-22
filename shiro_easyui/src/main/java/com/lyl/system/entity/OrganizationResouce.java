package com.lyl.system.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lyl.base.entity.BaseEntity;

/**
 * 组织和资源关联表
 * @author Liu YunLong
 *
 */
@Entity
@Table(name = "T_SYS_ORG_RESOURCE")
public class OrganizationResouce extends BaseEntity {

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orgid")
	private Organization	organization;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rsid")
	private SysResource		resource;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public SysResource getResource() {
		return resource;
	}

	public void setResource(SysResource resource) {
		this.resource = resource;
	}
}
