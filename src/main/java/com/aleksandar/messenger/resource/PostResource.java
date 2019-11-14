package com.aleksandar.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aleksandar.messenger.model.Post;
import com.aleksandar.messenger.service.PostService;
import com.aleksandar.messenger.service.impl.PostServiceImpl;

@Path("/post")
public class PostResource {
	PostService service = new PostServiceImpl();
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getPosts() {
		return service.getPosts();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Post> getPostByUserId(@QueryParam("userId")int userId){
		return service.getPostsByUserId(userId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPost(Post post, @QueryParam("userId") int userId) {
		return service.addPost(post, userId);
	}
	
}
