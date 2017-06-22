package com.lyl.system.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.lyl.base.entity.BaseEntity;

/**
 * 资源表
 * 
 * @author Liu YunLong
 * 
 */

@Entity
@Table(name = "T_SYS_RESOURCE", uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "label" }) })
public class SysResource extends BaseEntity implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private String				name;

	private String				label;

	//EnumType.ORDINAL 将值保持到数据库，默认的模式 ;EnumType.STRING将名称保存到数据库
	@Enumerated(EnumType.STRING)
	private ResourceType		resourceType;

	private String				resourcePath;

	private String				icon;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pid")
	private SysResource			parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private Set<SysResource>	subList				= new HashSet<SysResource>();

	@Transient
	private boolean				isLeaf				= true;

	public String getIdPath() {
		String ids = this.getId().toString();
		SysResource resource = this.getParent();
		while (resource != null && resource.getId() != null) {
			ids = resource.getId().toString() + "/" + ids;
			resource = resource.getParent();
		}
		return ids;

	}

	public ResourceType getChildType() {
		if (resourceType == ResourceType.root) {
			return ResourceType.module;
		} else if (resourceType == ResourceType.module) {
			return ResourceType.menu;
		} else if (resourceType == ResourceType.menu) {
			return ResourceType.button;
		} else {
			return null;
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public SysResource getParent() {
		return parent;
	}

	public void setParent(SysResource parent) {
		this.parent = parent;
	}

	public Set<SysResource> getSubList() {
		return subList;
	}

	public void setSubList(Set<SysResource> subList) {
		this.subList = subList;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public enum ResourceType {
		root("根节点"), module("模块"), menu("菜单"), button("按钮");
		String	text;

		ResourceType(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}

	}

	public SysResource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getParentId() {
		if (this.getParent() != null) {
			return this.getParent().getId();
		}
		return 0L;

	}

	@Override
	public String toString() {
		return "SysResource [id=" + getId() + ",name=" + name + ", label=" + label + ", resourceType=" + resourceType
				+ ", resourcePath=" + resourcePath + ", icon=" + icon + ", _parentId=" + getParentId() + ", isLeaf="
				+ isLeaf + "]";
	}

	public boolean isLeaf() {
		if (this.getSubList() == null || this.getSubList().size() == 0) {
			return true;
		}
		return false;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

}
