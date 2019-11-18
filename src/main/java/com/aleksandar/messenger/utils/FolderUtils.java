package com.aleksandar.messenger.utils;

import java.io.File;

public class FolderUtils {
	
	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("windows");
	}
	
	public static File folderName(int userId) {
		File home;
		if (isWindows()) {
			home = new File("C:\\");
		}else {
			home = new File("/var/");
		}
		
		File appFolder = getAppFolder(home, userId);
		return appFolder;
	}
	
	public static File getAppFolder(File home, int userId) {
		File root = new File(home, "Messenger");
		if (!root.exists()) {
			root.mkdir();
		}
		File upload = new File(root, "upload");
		if (!upload.exists()) {
			upload.mkdir();
		}
		File profile = new File(upload, "profile");
		if (!profile.exists()) {
			profile.mkdir();
		}
		File user = new File(profile, String.valueOf(userId));
		if (!user.exists()) {
			user.mkdir();
		}
				
		
		return user;
	}
	
	public static String getSlash() {
		String slash="";
		if(isWindows()) {
			slash = "\\";
		}else {
			slash = "/";
		}
		return slash;
	}

}
