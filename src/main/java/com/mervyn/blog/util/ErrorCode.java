package com.mervyn.blog.util;

public enum ErrorCode {

	E1200(1200,"成功"),
	E1404(1404,"找不到页面"),
	E1405(1405,"没有数据"),
	E1500(1500,"服务器内部错误");
	
	
	
	private Integer code;
	private String info;
	
	

	ErrorCode(Integer code, String info) {
		this.code = code;
		this.info = info;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	
	
}
