package com.aleksandar.messenger.service;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.aleksandar.messenger.model.ApplicationUser;
import com.aleksandar.messenger.model.Upload;

public interface ProfileService {

	Response createUser(ApplicationUser user);
	List<ApplicationUser> getUsers();
	
	Response uploadProfileImg(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer userId);
	
	Upload getUpload(int userId);
	
	String getProfileImg(int userId);
}
