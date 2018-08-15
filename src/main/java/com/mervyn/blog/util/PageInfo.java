package com.mervyn.blog.util;

import java.util.List;

public class PageInfo<T> {

	private long pageNum;	//当前页
	private long pageSize;	//每页数量
	private long totalNum;	//总数
	private long totalPage;	//总页码
	
	private List<T> list;

	
	
	public PageInfo() {
		pageSize = 5;
		pageNum = 1;
	}

	public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}



	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(long totalNum) {
		this.totalNum = totalNum;
	}

	public long getTotalPage() {
		totalPage = (long) Math.ceil(totalNum / pageSize);
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	
}
