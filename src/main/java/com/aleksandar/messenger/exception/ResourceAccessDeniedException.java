package com.aleksandar.messenger.exception;

import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.aleksandar.messenger.model.ExceptionModel;

public class ResourceAccessDeniedException extends WebApplicationException {
	private static final long serialVersionUID = 1L;
	
	public ResourceAccessDeniedException(String message) {
        super(Response.status(Response.Status.FORBIDDEN)
            .entity(new ExceptionModel(message, new Date())).build());
    }
}
