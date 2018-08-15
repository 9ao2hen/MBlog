package com.mervyn.blog.service;

import com.mervyn.blog.bean.BlogReq;
import com.mervyn.blog.model.Blog;
import com.mervyn.blog.util.PageInfo;

public interface BlogService {

	/**
	 * 保存blog
	 * @param blogReq
	 * @return
	 */
	public Blog saveBlog(BlogReq blogReq);

	Blog getBlog(BlogReq blogReq);

	PageInfo<Blog> queryBlogList(BlogReq blogReq);

}
