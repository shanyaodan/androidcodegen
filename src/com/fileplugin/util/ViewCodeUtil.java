package com.fileplugin.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

public class ViewCodeUtil {
	static SAXParserFactory saxfac = SAXParserFactory.newInstance();

	static SaxHander mySax = new SaxHander();

	public static String[] getCode(String resFileName) {

		File f = new File(resFileName);
		if (!f.exists()) {
			return null;
		}

		try {
			saxfac.newSAXParser().parse(f, mySax);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		List<IdNamePair> res = mySax.getRes();
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();

		for (IdNamePair idNamePair : res) {
			String viewname = idNamePair.getId().replace("_data", "");
			sb.append(" private " + idNamePair.getName() + "  " + viewname
					+ idNamePair.getName() + ";\n");

			sb1.append("    " + viewname + idNamePair.getName() + " = ("
					+ idNamePair.getName() + ")findViewById(R.id."
					+ idNamePair.getId() + ");\n");
		}

		return new String[] { sb.toString(), sb1.toString(), };
	}

	public static String[] getAdapterCode(String resFileName, String entityName) {

		File f = new File(resFileName);
		if (!f.exists()) {
			return null;
		}

		try {
			saxfac.newSAXParser().parse(f, mySax);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		List<IdNamePair> res = mySax.getRes();
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		sb2.append(entityName + " item =(" + entityName
				+ ")mData.get(position);\n");

		for (IdNamePair idNamePair : res) {
			String viewname = idNamePair.getId().replace("_data", "");
			sb.append("\npublic " + idNamePair.getName() + " " + viewname + ";");
			sb1.append("vh." + viewname + " = (" + idNamePair.getName()
					+ ")convertView.findViewById(R.id." + idNamePair.getId()
					+ ");\n");
			if (idNamePair.getId().contains("_data")) {
				if (idNamePair.getName().equals("TextView")) {
					System.out.println("add textView");
					sb2.append("vh." + viewname + ".setText(item." + viewname
							+ ");\n");
				}
			}
		}
		if (null != entityName || entityName.length() > 0) {
			genEntity(resFileName, entityName);
		}

		return new String[] { sb1.toString(), sb.toString(), sb2.toString() };
	}

	
	
	
	
	public static void genEntity(String resFileName, String entityname) {
		File f = new File(resFileName);
		if (!f.exists()) {
			return;
		}

		try {
			saxfac.newSAXParser().parse(f, mySax);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		List<IdNamePair> res = mySax.getRes();
		try {
			File entitypackage = new File(CommUtitl.projPath + "src/"
					+ CommUtitl.entityppackage);
			if (!entitypackage.exists()) {
				entitypackage.mkdirs();
			}
			File newentityFile = new File(entitypackage, entityname + ".java");
			FileOutputStream fo = new FileOutputStream(newentityFile);
			StringBuilder sb = new StringBuilder();
			sb.append("package "+CommUtitl.entityppackage.replace("/", ".")+";");
			sb.append("\n public class " + entityname + "{");
			for (IdNamePair idNamePair : res) {
				if (idNamePair.getId().contains("_data")) {
					String viewname = idNamePair.getId().replace("_data", "");
					sb.append("\npublic String  " + viewname + ";\n");
				}
			}
			sb.append("}\n");
			fo.write(sb.toString().getBytes());
			fo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}