package com.aleksandar.messenger.exception;

import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.aleksandar.messenger.model.ExceptionModel;

@Provider
public class ResourceNotFoundException extends RuntimeException implements ExceptionMapper<ResourceNotFoundException>{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super("Resource not found");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

	@Override
	public Response toResponse(ResourceNotFoundException exception) {
		ExceptionModel em = new ExceptionModel(exception.getMessage(), new Date());
		return Response.status(Response.Status.NOT_FOUND)
				.entity(em)
				.build();
	}
}
