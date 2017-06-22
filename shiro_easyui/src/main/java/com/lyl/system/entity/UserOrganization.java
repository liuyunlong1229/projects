package com.lyl.system.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lyl.base.entity.BaseEntity;

/**
 * 用户和组织关联表
 * @author Liu YunLong
 *
 */
@Entity
@Table(name = "T_SYS_USER_ORGANIZATION")
public class UserOrganization extends BaseEntity {

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orgId")
	private Organization	organization;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uId")
	private SysUser			SysUser;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public SysUser getSysUser() {
		return SysUser;
	}

	public void setSysUser(SysUser sysUser) {
		SysUser = sysUser;
	}

}
