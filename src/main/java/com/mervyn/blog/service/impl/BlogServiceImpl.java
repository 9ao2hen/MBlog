package com.mervyn.blog.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mervyn.blog.bean.BlogReq;
import com.mervyn.blog.model.Blog;
import com.mervyn.blog.repository.BlogReposiroty;
import com.mervyn.blog.service.BlogService;
import com.mervyn.blog.util.ObjectType;
import com.mervyn.blog.util.PageInfo;


@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogReposiroty blogReposiroty;
	
	/**
	 * @param @save :{userId, title, content,[tag, description]}
	 * 
	 * @param @update:{userId ,title, content,[tag,description ]}
	 */
	@Override
	public Blog saveBlog(BlogReq blogReq) {
		Blog blog = new Blog();
		if(ObjectType.isEmpty(blogReq.getBlogId())) {		//new insert
			BeanUtils.copyProperties(blogReq, blog);
		/*	if(blog.getTitle() == null) {
				blog.setTitle(blog.getContent().substring(0, 20)+"...");
			}
			if(blog.getDescription() == null) {
				blog.setDescription(blog.getContent().substring(0, 140));
			}*/
			blog.setCreateTime(new Date());
			blog.setUpdateTime(new Date());
			blog.setBlogId(UUID.randomUUID().toString());
			blogReposiroty.saveBlog(blog);
		}else {				//edit
			Update update = new Update();
			update.set("title", blogReq.getTitle());
			update.set("description", blogReq.getDescription());
			update.set("tag", blogReq.getTag());
			update.set("content", blogReq.getContent());
			blogReposiroty.updateBlog(blogReq, update);
		}
		return null;
	}
	
	/**
	 * 根据blogID获取blog对象
	 */
	@Override
	public Blog getBlog(BlogReq blogReq) {
		if(!ObjectType.isEmpty(blogReq.getBlogId())) {
			return blogReposiroty.getBlog(blogReq.getBlogId());
		}else {
			return null;
		}
	}
	
	/**
	 * 查询列表
	 * @return
	 */
	@Override
	public PageInfo<Blog> queryBlogList(BlogReq blogReq){
		PageInfo<Blog> pageInfo = new PageInfo<Blog>();
		BeanUtils.copyProperties(blogReq, pageInfo);
		
		return blogReposiroty.getBlogList(blogReq, pageInfo);
	}
	
	
}
