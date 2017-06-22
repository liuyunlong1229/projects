package com.lyl.system.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.StringUtils;

import com.lyl.base.entity.BaseEntity;

/**
 * 用户表
 * @author Liu YunLong
 *
 */
@Entity
@Table(name = "T_SYS_USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "loginName" }) })
public class SysUser extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	private String		loginName;

	private String		password;

	private String		salt;

	private String		realName;

	private String		sex;

	private String		telephone;

	private String		email;

	private String		address;

	@Transient
	private String	    roleIds	;
	

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	public String getSexStr(){
		if("1".equals(this.getSex())){
			return "男";
		}else if("0".equals(getSex())){
			return "女";
		}else{
			return "";
		}
	}
	

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleIds() {
		return roleIds;
	}
	
	public String [] getRolesIdArr(){
		if(StringUtils.isNotBlank(roleIds)){
			return roleIds.split(",");
		}
		return new String []{};
		
	}

}
