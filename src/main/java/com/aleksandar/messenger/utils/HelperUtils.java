package com.aleksandar.messenger.utils;

import java.util.Map;

import com.aleksandar.messenger.exception.ResourceNotFoundException;
import com.aleksandar.messenger.model.ApplicationUser;

public class HelperUtils {
	
	public static ApplicationUser findUserById(Map<Integer, ApplicationUser> users, int userId) {
		return users.entrySet()
				.stream()
				.filter(p -> p.getKey() == userId)
				.map(m -> m.getValue()).findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

}
