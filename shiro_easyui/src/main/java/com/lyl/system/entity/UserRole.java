package com.lyl.system.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.lyl.base.entity.BaseEntity;

/**
 * 用户和角色关联表
 * @author Liu YunLong
 *
 */
@Entity
@Table(name = "T_SYS_USER_ROLE")
public class UserRole extends BaseEntity {

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "uid")
	private SysUser	user;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rid")
	private SysRole	role;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}
}
