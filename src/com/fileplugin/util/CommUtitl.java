package com.fileplugin.util;

import java.io.File;

public class CommUtitl {
	
	public static String workspaceDir ="";
	public static String sourceDirSimpleName = "src/";
	
	
	
	public static String projectRelativeDir = "";
	public static String fragmentRelativeDir = "";
	public static String entityRelativeDir = "";
	public static String activityRelativeDir = "";
	public static String adapterRelativeDir = "";
	
	
	
	public static String entityName = "";
	public static String adapterName = "TempAdapter";
	public static String activityName = "";
	public static String fragmentName = "";
//	public static String ideaPackage = "src/";
//
//	public static String projPath = "";
//	public static String projPackage = "";
//	
//	public static String activityname = "";
//	public static String activitypackageName = "";
//	
//	public static String entityppackage = "";

//	
//	public static String adapterName = "TempAdapter";
//	public static String adapterPackageName = "";
//	
//	public static String fragmentpackagename = "";
//	public static String fragmentname="";
//	
//	public String tempPath;


	// private static final String projPath =
	// "D:\\Users\\lenovo\\workspace7\\HomeActivity\\";
	//
	// public static final String activitypath = projPath +
	// "src\\it\\polimi\\promemoria\\";
	// public static final String entityPath = projPath +
	// "src\\it\\polimi\\promemoria\\";
//	public static final String layoutFil = projPath + "res\\layout\\";

	public static String getAbsolutePath(String relativePath){
		
		return workspaceDir+File.separator+sourceDirSimpleName+File.separator+relativePath;
		
	}
	
	public static String firstToupCast(String prfix) {
		return prfix.substring(0, 1).toUpperCase()
				+ prfix.substring(1, prfix.length());
	}
}
