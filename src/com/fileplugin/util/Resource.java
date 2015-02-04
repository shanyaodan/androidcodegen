package com.fileplugin.util;

import java.io.IOException;
import java.net.URL;

import com.fileplugin.Activator;

public class Resource {
	public String getFragmentResource() throws IOException {
		return Activator.getResourceString("temp/TempleteFragment.txt");

	}
	public String getActivityResource() throws IOException {
		return Activator.getResourceString("temp/TempleteActivity1.txt");
		
	}

	public String getAdapterResource() throws IOException {
		// 查找指定资源的URL
		return Activator.getResourceString("temp/Tempadapter.txt");
	}

	public String getBaseAdapterResource() throws IOException {
		// 查找指定资源的URL
		return Activator.getResourceString("temp/SimpleAdapter.txt");

	}
	public String getDosCmd() throws IOException {
		// 查找指定资源的URL
		return Activator.getResourceString("temp/dos.bat");

	}
	
	
}