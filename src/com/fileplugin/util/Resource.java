package com.fileplugin.util;

import java.io.IOException;
import java.net.URL;

public class Resource {
	public URL getActivityResource() throws IOException {
		// 查找指定资源的URL，其中res.txt仍然开始的bin目录下
		URL fileURL = this.getClass().getResource("/resource/TempleteActivity1.txt");
		System.out.println(fileURL.getFile());
		return fileURL;
	}
	
	public URL getAdapterResource() throws IOException {
		// 查找指定资源的URL，其中res.txt仍然开始的bin目录下
		URL fileURL = this.getClass().getResource("/temp/Tempadapter.txt");
		System.out.println(fileURL.getFile());
		return fileURL;
	}
	
	public URL getBaseAdapterResource() throws IOException {
		// 查找指定资源的URL，其中res.txt仍然开始的bin目录下
		URL fileURL = this.getClass().getResource("/temp/SimpleAdapter.txt");
		System.out.println(fileURL.getFile());
		return fileURL;
	}
	
}