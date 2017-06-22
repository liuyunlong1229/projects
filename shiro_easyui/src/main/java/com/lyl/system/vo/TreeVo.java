package com.lyl.system.vo;

import java.util.ArrayList;
import java.util.List;

public class TreeVo {
	
	private Long id;
	
	
	private String text;
	
	private String iconCls;
	
	
	private String  state="open" ;
	
	boolean checked;
	
	List<TreeVo> children=new ArrayList<TreeVo>();
	
	List attributes;



	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}




	


	public TreeVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public List getAttributes() {
		return attributes;
	}


	public void setAttributes(List attributes) {
		this.attributes = attributes;
	}


	@Override
	public String toString() {
		return "TreeVo [id=" + id + ", text=" + text + ", iconCls=" + iconCls
				+ ", state=" + state + ", checked=" + checked + ", children="
				+ children + ", attributes=" + attributes + "]";
	}


	public List<TreeVo> getChildren() {
		return children;
	}


	public void setChildren(List<TreeVo> children) {
		this.children = children;
	}


	public TreeVo(Long id, String text, String state, boolean checked,
			List<TreeVo> children, List attributes) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.checked = checked;
		this.children = children;
		this.attributes = attributes;
	}


	public String getIconCls() {
		return iconCls;
	}


	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}




	

	
	
}
