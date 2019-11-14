package com.aleksandar.messenger.resource;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.aleksandar.messenger.model.ApplicationUser;
import com.aleksandar.messenger.model.Upload;
import com.aleksandar.messenger.service.ProfileService;
import com.aleksandar.messenger.service.impl.ProfileServiceImpl;

@Path("/profile")
public class ProfileResource {
	
	ProfileService service = new ProfileServiceImpl();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(ApplicationUser user) {
		return service.createUser(user);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ApplicationUser> getUsers(){
		return service.getUsers();
	}

	@POST
	@Path("/uploadImg")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response uploadProfileImg(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("userId") Integer userId) {
		
		return service.uploadProfileImg(uploadedInputStream, fileDetail, userId);
	}
	
	@GET
	@Path("/{userId}/upload")
	@Produces(MediaType.APPLICATION_JSON)
	public Upload getUpload(@PathParam("userId") int userId) {
		return service.getUpload(userId);
	}
	
	@GET
	@Path("/{userId}/upload/show")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProfileImg(@PathParam("userId") int userId) {
		return service.getProfileImg(userId);
	}
}
