package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Blog;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addBlog(Blog blog) {

		try
		{
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception occoured"+e);
			return false;
		}
		
	}

	@Override
	public boolean deleteBlog(Blog blog) {
		try
		{
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception occoured"+e);
			return false;
		}
	}

	@Override
	public boolean updateBlog(Blog blog) {
		try
		{
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception occoured"+e);
			return false;
		}
	}

	@Override
	public Blog getBlog(int blogId) {
		try
		{
			Session session = sessionFactory.openSession();
			Blog blog = (Blog)session.get(Blog.class, blogId);
			session.close();
			return blog;
		}
		catch(Exception e)
		{
			System.out.println("Exception occoured"+e);
			return null;
		}
	}

	@Override
	public List<Blog> listBlogs() {
		try
		{
			Session session=sessionFactory.openSession();
			Query query=session.createQuery("from Blog");
			List<Blog> listBlogs=query.list();
			session.close();
			return listBlogs;
		}
		catch(Exception e)
		{
			System.out.println("Exception occoured:"+e);
			return null;
		}
	}

	@Override
	public boolean approveBlog(int blogId) {
		try
		{
			Blog blog = getBlog(blogId);
			blog.setStatus("A");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception occoured" + e);
			return false;
		}
	}

	@Override
	public boolean rejectBLog(int blogId) {
		try
		{
			Blog blog = getBlog(blogId);
			blog.setStatus("NA");
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception occoured" + e);
			return false;
		}
	}

	@Override
	public boolean inclikes(int blogId) {
		try
		{
			Blog blog = this.getBlog(blogId);
			blog.setLikes(blog.getLikes()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception occoured" + e);
			return false;
		}
	}

	@Override
	public boolean incunlikes(int blogId) {
		try
		{
			Blog blog = this.getBlog(blogId);
			blog.setUnlikes(blog.getUnlikes()+1);
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception occoured" + e);
			return false;
		}
	}

}
