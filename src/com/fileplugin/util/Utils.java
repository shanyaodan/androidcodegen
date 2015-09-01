package com.fileplugin.util;

import java.io.*;

public final class Utils {
	public static String fromInputStreamToString(InputStream inputStream) {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String readLine = "";
		StringBuilder stringBuilder = new StringBuilder();
		try {
			while ((readLine = bufferedReader.readLine()) != null) {
				stringBuilder.append(readLine + "\n");
			}
			inputStream.close();
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}

	public static String fromInputStreamToString(String path) {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(path)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String readLine = "";
		StringBuilder stringBuilder = new StringBuilder();
		try {
			while ((readLine = bufferedReader.readLine()) != null) {
				stringBuilder.append(readLine + "\n");
			}
			
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
}
