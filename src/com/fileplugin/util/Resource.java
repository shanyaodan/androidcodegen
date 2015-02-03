package com.fileplugin.util;

import java.io.IOException;
import java.net.URL;

import com.fileplugin.Activator;

public class Resource {
	public String getActivityResource() throws IOException {
		// 查找指定资源的URL，其中res.txt仍然开始的bin目录下
		return Activator.getResourceString("temp/TempleteActivity1.txt");

	}

	public String getAdapterResource() throws IOException {
		// 查找指定资源的URL，其中res.txt仍然开始的bin目录下
		return Activator.getResourceString("temp/Tempadapter.txt");
	}

	public String getBaseAdapterResource() throws IOException {
		// 查找指定资源的URL，其中res.txt仍然开始的bin目录下
		return Activator.getResourceString("temp/SimpleAdapter.txt");

	}
	public String getDosCmd() throws IOException {
		// 查找指定资源的URL，其中res.txt仍然开始的bin目录下
		return Activator.getResourceString("temp/dos.bat");

	}
	
	
}