package com.niit.testCases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;

public class BlogTestCase {
	
	static BlogDAO blogDAO;

	@BeforeClass
	public static void init()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		blogDAO=(BlogDAO)context.getBean("blogDAO");
	}

	//@Ignore
	@Test
	public void addBlogTestCase()
	{
		Blog blog = new Blog();
		blog.setBlogName("Java ");
		blog.setBlogContent("Java Related Materials");
		blog.setCreatedDate(new java.util.Date());
		blog.setLikes(1);
		blog.setUnlikes(1);
		blog.setLoginname("sahil");
		blog.setStatus("NA");
		
		assertTrue("Problem occour in inserting data", blogDAO.addBlog(blog));
	}
	
	@Ignore
	@Test
	public void deleteBlogTestCase()
	{
		Blog blog = blogDAO.getBlog(953);
		
		assertTrue("Problem occour in deleting data", blogDAO.deleteBlog(blog));
		
	}
	
	@Test
	public void updateBlogTestCase()
	{
		Blog blog= blogDAO.getBlog(952);
		blog.setBlogName("Rabit Story");
		blog.setLikes(4);
		
		assertTrue("Problem occour in updating data", blogDAO.updateBlog(blog));
	}
	
	@Test
	public void listBlogsTestCase()
	{
		List<Blog> listBlogs = blogDAO.listBlogs();
		assertTrue("Problem occour in Listing data", listBlogs.size()>0);
		
		for(Blog blog:listBlogs)
		{
			System.out.print(blog.getBlogId()+"----");
			System.out.print(blog.getBlogName()+"----");
			System.out.println(blog.getBlogContent()+"----");
		}
	}
	
	
	@Test
	public void approveBlogTestCase()
	{
		assertTrue("Problem occour in approving data",blogDAO.approveBlog(951));
	}
	
	@Test
	public void rejectBlogTestCase()
	{
		assertTrue("Problem occour in rejecting data",blogDAO.rejectBLog(951));
	}
	
	@Test
	public void incLikeBlogTestCase()
	{
		assertTrue("Problem in increasing Blog likes",blogDAO.inclikes(951));
	}
	
	@Test
	public void incUnLikeBlogTestCase()
	{
		assertTrue("Problem in increasing Blog likes",blogDAO.incunlikes(954));
	}
	
}
