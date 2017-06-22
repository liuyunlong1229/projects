package com.lyl.base.utils;

/**
  分页对象
*/
public class PageBean {
	
	
	public final static int	PAGE_SIZE	= 1;

	/**
	 * 每页记录数
	 */
	private Integer				pageSize;

	/**
	 * 总记录数
	 */
	private Integer			totalCount;
	
	/**
	 * 当前的页码
	 */

	private Integer			currPageIndex;


	
	public Integer getStartIndex(){
		
		return ((getCurrPageIndex() - 1) * getPageSize());
	}
	
	public int getPageSize() {
		if(pageSize==null){
			return PAGE_SIZE;
		}
		return pageSize;
	}
	
	

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}



	public Integer getCurrPageIndex() {
		if(currPageIndex==null){
			currPageIndex=1;
		}
		return currPageIndex;
	}



	public void setCurrPageIndex(Integer currPageIndex) {
		this.currPageIndex = currPageIndex;
	}



	public Integer getTotalCount() {
		return totalCount;
	}

	public PageBean(int pageSize, Integer currPageIndex) {
		super();
		this.pageSize = pageSize;
		this.currPageIndex = currPageIndex;
	}

	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}


}
