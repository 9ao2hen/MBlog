package com.mervyn.blog.repository;

import org.springframework.data.mongodb.core.query.Update;

import com.mervyn.blog.bean.BlogReq;
import com.mervyn.blog.model.Blog;
import com.mervyn.blog.util.PageInfo;

public interface BlogReposiroty {

	void saveBlog(Blog blog);

	Blog getBlog(String blogId);

	void updateBlog(BlogReq blog, Update update);

	PageInfo<Blog> getBlogList(BlogReq blogReq, PageInfo<Blog> pageInfo);

}
