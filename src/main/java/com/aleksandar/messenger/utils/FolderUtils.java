package com.aleksandar.messenger.utils;

import java.io.File;

public class FolderUtils {
	
	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("windows");
	}
	
	public static String folderName() {
		File home;
		if (isWindows()) {
			home = new File("C:\\");
		}else {
			home = new File("/var/");
		}
		
		File appFolder = getAppFolder(home);
		return appFolder.getPath();
	}
	
	private static File getAppFolder(File home) {
		File root = new File(home, "Messenger");
		if (!root.exists()) {
			root.mkdir();
		}
		File upload = new File(root, "upload");
		if (!upload.exists()) {
			upload.mkdir();
		}
		
		return upload;
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
