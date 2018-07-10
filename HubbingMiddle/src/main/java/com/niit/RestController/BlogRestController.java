package com.niit.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.BlogDAO;
import com.niit.model.Blog;

@RestController
public class BlogRestController 
{
	
	@Autowired
	BlogDAO blogDAO;
	
	@GetMapping("/showAllBlogs")
	public ResponseEntity<List<Blog>> showAllBlog()
	{
		List<Blog> listOfAllBlogs=blogDAO.listBlogs();
		
		if(listOfAllBlogs!=null)
		{
		return new ResponseEntity<List<Blog>>(listOfAllBlogs,HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<List<Blog>>(listOfAllBlogs,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
	}
	
	
	@PostMapping("/addingBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog)
	{
		
		
		if(blogDAO.addBlog(blog))
		{
			return new ResponseEntity<String>("AddBlog Success", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("AddBlog Faliure", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/gettingBlog/{blogId}")
	public ResponseEntity<Blog> getBLog(@PathVariable("blogId") int blogId)
	{
		Blog blog = blogDAO.getBlog(blogId);
		if(blog != null)
		{
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Blog>(blog, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/approvingBlog/{blogId}")
	public ResponseEntity<String> approveBlog(@PathVariable("blogId") int blogId)
	{
		if(blogDAO.approveBlog(blogId))
		{
			return new ResponseEntity<String>("Approving success", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Approving Error Occoured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/rejectingBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable("blogId") int blogId)
	{
		if(blogDAO.rejectBLog(blogId))
		{
			return new ResponseEntity<String>("Rejecting success", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Rejecting Error Occoured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/incrementingLikesOnBlog/{blogId}")
	public ResponseEntity<String> IncLikes(@PathVariable("blogId") int blogId)
	{
		if(blogDAO.inclikes(blogId))
		{
			return new ResponseEntity<String>("Incrementing Likes On Blog success", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Incrementing Likes On Blog Error Occoured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/incrementingUnLikesOnBlog/{blogId}")
	public ResponseEntity<String> IncUnLikes(@PathVariable("blogId") int blogId)
	{
		if(blogDAO.incunlikes(blogId))
		{
			return new ResponseEntity<String>("Incrementing Likes On Blog success", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Incrementing Likes On Blog Error Occoured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("deletingBlog/{blogId}")
	public ResponseEntity<String> deletingBlog(@PathVariable("BlogId") int blogId)
	{
		Blog blog = blogDAO.getBlog(blogId);
		
		if(blogDAO.deleteBlog(blog))
		{
			return new ResponseEntity<String>("Deleting success", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Deleting Error Occoured ", HttpStatus.OK);
		}
	}
	
	@PostMapping("/updatingBlog")
	public ResponseEntity<String> updatingBlog(@RequestBody Blog blog)
	{
		blog.setCreatedDate(new java.util.Date());
		
		if(blogDAO.updateBlog(blog))
		{
			return new ResponseEntity<String>("UpdatingBlog Success", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("UpdatingBlog Error Occoured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
