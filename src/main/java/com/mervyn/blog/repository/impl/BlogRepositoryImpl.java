package com.mervyn.blog.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.mervyn.blog.bean.BlogReq;
import com.mervyn.blog.model.Blog;
import com.mervyn.blog.repository.BlogReposiroty;
import com.mervyn.blog.util.ObjectType;
import com.mervyn.blog.util.PageInfo;

@Component
public class BlogRepositoryImpl implements BlogReposiroty{

	
	
	
	@Autowired
	MongoTemplate mongo;
	
	//save
	@Override
	public void saveBlog(Blog blog) {
		mongo.insert(blog);
	}
	
	//update
	@Override
	public void updateBlog(BlogReq blog, Update update) {
		mongo.updateFirst(new Query(Criteria.where("blogId").is(blog.getBlogId())), update, Blog.class);
	}
	
	//getBlog
	@Override
	public Blog getBlog(String blogId) {
		return mongo.findOne(new Query(Criteria.where("blogId").is(blogId)), Blog.class);
	}
	
	//get list
	@Override
	public PageInfo<Blog> getBlogList(BlogReq blogReq, PageInfo<Blog> pageInfo){
		
		Query query = new Query();
		if(!ObjectType.isEmpty(blogReq.getTitle())) {
			query = new Query(Criteria.where("title").regex(blogReq.getSearchWord()));
		}
		long totalNum = mongo.count(query.limit(blogReq.getPageSize()).skip(blogReq.getStartRow()), Blog.class);
		pageInfo.setTotalNum(totalNum);
		List<Blog> result = mongo.find(query.limit(blogReq.getPageSize()).skip(blogReq.getStartRow()), Blog.class);
		pageInfo.setList(result);
		return pageInfo;
	}
	
}
