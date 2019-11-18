package com.aleksandar.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aleksandar.messenger.model.Comment;
import com.aleksandar.messenger.service.CommentService;
import com.aleksandar.messenger.service.impl.CommentServiceImpl;

@Path("/comments")
public class CommentResource {

	private CommentService service = new CommentServiceImpl(); 
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@QueryParam("postId") int postId, Comment comment, @QueryParam("userId") int userId) {
		return service.addComment(postId, comment, userId);
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@QueryParam("postId") int postId) {
		return service.getCommentsByPostId(postId);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Comment updateComment(@PathParam("id") int id, @QueryParam("postId")int postId, Comment comment, @QueryParam("userId") int userId) {
		return service.updateComment(id, postId, comment, userId);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response deleteComment( @QueryParam("postId")int postId, @PathParam("id") int id, @QueryParam("userId") int userId) {
		return service.deleteComment(postId, id, userId);
	}
}
