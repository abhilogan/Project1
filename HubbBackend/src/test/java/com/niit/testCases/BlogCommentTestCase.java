package com.niit.testCases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.BlogCommentDAO;
import com.niit.model.BlogComment;

public class BlogCommentTestCase {

	static BlogCommentDAO blogCommentDAO;

	@BeforeClass
	public static void init()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		blogCommentDAO=(BlogCommentDAO)context.getBean("blogCommentDAO");
	}

	@Test
	public void addCommentTestCase()
	{
		BlogComment blogComment = new BlogComment();
		blogComment.setBlogId(951);
		blogComment.setCommentDate(new java.util.Date());
		blogComment.setLoginname("sahil");
		blogComment.setCommentText("This is Comment for Collage Life");
		
		assertTrue("Problem in adding Comment",blogCommentDAO.addComment(blogComment));
	}
	
	@Test
	public void listAllBlogsTestCase()
	{
		List<BlogComment> listComments = blogCommentDAO.getAllComments(951);
		assertTrue("Problem in listing all Comments", listComments.size()>0);
		
		for(BlogComment blogComment:listComments)
		{
			System.out.print(blogComment.getBlogId()+"====");
			System.out.println(blogComment.getCommentText()+"====");
		}
	}
}
