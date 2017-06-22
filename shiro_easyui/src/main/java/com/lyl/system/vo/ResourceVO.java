package com.lyl.system.vo;

public class ResourceVO {

	private String	id;

	private String	name;

	private String	label;

	private String	resourceType;

	private String	resourcePath;

	private String	icon;

	private String	_parentId;		//treegrid中识别父节点的必须字段，不能改名字

	private String	idPath;

	private boolean	isLeaf;		//是否是叶子节点

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

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
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

	public String getIdPath() {
		return idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ResourceVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceVO(String id, String name, String label, String resourceType, String resourcePath, String icon,
			String _parentId, String idPath) {
		super();
		this.id = id;
		this.name = name;
		this.label = label;
		this.resourceType = resourceType;
		this.resourcePath = resourcePath;
		this.icon = icon;
		this._parentId = _parentId;
		this.idPath = idPath;
	}

	public boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

}
