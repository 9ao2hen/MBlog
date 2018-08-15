package com.mervyn.blog.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mervyn.blog.bean.BlogReq;
import com.mervyn.blog.model.Blog;
import com.mervyn.blog.service.BlogService;
import com.mervyn.blog.util.ErrorCode;
import com.mervyn.blog.util.MsgResponse;
import com.mervyn.blog.util.PageInfo;

@Controller
public class BlogController {

	@Autowired
	BlogService blogService ;
	

	//保存blog
	@ResponseBody
	@RequestMapping(value="saveBlog", method=RequestMethod.POST)
	public MsgResponse<String> hello(@RequestBody BlogReq blogReq) {
		MsgResponse<String> msgResponse = new MsgResponse<String>();
		msgResponse.setCode(ErrorCode.E1200.getCode());
		msgResponse.setMsg(ErrorCode.E1200.getInfo());
		
		try {
			blogService.saveBlog(blogReq);
		} catch (Exception e) {
			msgResponse.setCode(ErrorCode.E1500.getCode());
			msgResponse.setMsg(ErrorCode.E1500.getInfo());
			msgResponse.setErrorDesc(e.getMessage());
		}
		return msgResponse;
	}
	
	/**
	 * 博客列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="queryBlogs")
	public MsgResponse<PageInfo<Blog>> getBlogList(BlogReq blogReq){
		MsgResponse<PageInfo<Blog>> msgResponse = new MsgResponse<PageInfo<Blog>>();
		msgResponse.setCode(ErrorCode.E1200.getCode());
		msgResponse.setMsg(ErrorCode.E1200.getInfo());
		try {
			
			PageInfo<Blog> result  = blogService.queryBlogList(blogReq);
			msgResponse.setResult(result);
			
		} catch (Exception e) {
			msgResponse.setCode(ErrorCode.E1500.getCode());
			msgResponse.setMsg(ErrorCode.E1500.getInfo());
			msgResponse.setErrorDesc(e.getMessage());
		}
		return msgResponse;
	}
	
	
	/**
	 * 查看博客
	 * @param blogId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getBlog", method=RequestMethod.GET)
	public MsgResponse<Blog> getBlog(BlogReq blogReq) {	
		MsgResponse<Blog> msgResponse = new MsgResponse<Blog>();
		msgResponse.setCode(ErrorCode.E1200.getCode());
		msgResponse.setMsg(ErrorCode.E1200.getInfo());
		
		try {
			Blog result  = blogService.getBlog(blogReq);
			msgResponse.setResult(result);
		} catch (Exception e) {
			msgResponse.setCode(ErrorCode.E1500.getCode());
			msgResponse.setMsg(ErrorCode.E1500.getInfo());
			msgResponse.setErrorDesc(e.getMessage());
		}
		return msgResponse;
	}
	
}
