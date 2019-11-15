package com.aleksandar.messenger.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.aleksandar.messenger.exception.ResourceNotFoundException;
import com.aleksandar.messenger.model.ApplicationUser;
import com.aleksandar.messenger.model.Upload;
import com.aleksandar.messenger.service.ProfileService;
import com.aleksandar.messenger.utils.FolderUtils;

public class ProfileServiceImpl implements ProfileService{

	public static Map<Integer, ApplicationUser> users = new HashMap<>();
	static {
		users.put(1,  new ApplicationUser(1, "Aleksandar", "Stojkovski"));
	}
	
	public static Map<Integer, Upload> uploads = new HashMap<>();
	
	@Override
	public Response createUser(ApplicationUser user) {
		int id = users.size() + 1;
		user.setId(id);
		users.put(id, user);
		Response response = Response.status(201).build();
		return response;
	}

	@Override
	public List<ApplicationUser> getUsers() {
		return users.entrySet()
				.stream()
				.map(m -> m.getValue())
				.collect(Collectors.toList());
	}
	
	@Override
	public Upload getUpload(int userId) {
		return uploads.entrySet()
				.stream()
				.map(m -> m.getValue())
				.filter(p -> p.getUserId() == userId)
				.findFirst().orElseThrow(() -> new ResourceNotFoundException(String.format("There is no upload for userId=%1$s", userId)));
	}

	@Override
	public Response uploadProfileImg(InputStream uploadedInputStream, FormDataContentDisposition fileDetail, Integer userId) {
		String folderName = FolderUtils.folderName();
		String slash = FolderUtils.getSlash();
		
		String uploadedLocation = folderName + slash + fileDetail.getFileName();
		writeToFile(uploadedInputStream, uploadedLocation);
		String output = "File uploaded to: " + uploadedLocation;
		
		Upload upload = createUpload(fileDetail.getFileName(), userId);
		upload.setPath("\\upload\\" + fileDetail.getFileName());
		
		ApplicationUser user = users.get(userId);
		user.setUpload(upload);
		uploads.put(upload.getId(), upload);
		
		return Response.status(200).entity(output).build();
	}
	
	
	
	@Override
	public String getProfileImg(int userId) {
		return uploads.entrySet()
		.stream()
		.map(m -> m.getValue())
		.filter(p -> p.getUserId() == userId)
		.findFirst()
		.map(m -> m.getPath())
		.orElseThrow(() -> new ResourceNotFoundException(String.format("Image for userID=%1$s not found", userId)));
		
	}

	private void writeToFile(InputStream uploadedInputStream, String uploadedLocation) {
		try {
			OutputStream os = new FileOutputStream(new File(uploadedLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			
			while((read = uploadedInputStream.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}
			
			os.flush();
			os.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Upload createUpload(String fileName, int userId) {
		Upload u = new Upload();
		u.setId(uploads.size() + 1);
		u.setUserId(userId);
		u.setFileName(fileName);
		
		return u;
	}

}
