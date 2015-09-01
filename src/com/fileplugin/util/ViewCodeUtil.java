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
		StringBuilder sb2 = new StringBuilder();
		for (IdNamePair idNamePair : res) {
			String viewname = idNamePair.getId().replace("_data", "");
			sb.append(" private " + idNamePair.getName() + "  " + viewname
					+ idNamePair.getName() + ";\n");

			sb1.append("    " + viewname + idNamePair.getName() + " = ("
					+ idNamePair.getName() + ")findViewById(R.id."
					+ idNamePair.getId() + ");\n");
			if (idNamePair.getName().equals("TextView")
					&& idNamePair.getId().contains("_data")) {
				sb2.append(viewname + ".setText(entity." + viewname + ");\n");
			}
		}

		return new String[] { sb.toString(), sb1.toString(), sb2.toString() };
	}

//	public static String[] getAdapterCode(String resFileName, String entityName) {
//
//		File f = new File(resFileName);
//		if (!f.exists()) {
//			return null;
//		}
//
//		try {
//			saxfac.newSAXParser().parse(f, mySax);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//
//		List<IdNamePair> res = mySax.getRes();
//		StringBuilder sb1 = new StringBuilder();
//		StringBuilder sb2 = new StringBuilder();
//		StringBuilder sb = new StringBuilder();
//		if (null != res && res.size() > 0) {{
//			sb.append("return new int[]{");
//			sb1.append(entityName + " item =(" + entityName
//					+ ")entitys.get(position);\n");
//			for (IdNamePair idNamePair : res) {
//				sb.append("R.id."+idNamePair.getId()+",");
//				if (idNamePair.getId().contains("_data")) {
//					if (idNamePair.getName().equals("TextView")) {
//						System.out.println("add textView");
//						String viewname = idNamePair.getId().replace("_data", "");
//						sb1.append("((TextView)vh.getView("+"R.id."+idNamePair.getId()+")).setText(item."+viewname+");");
//					}
//				}
//			}
//			sb.append("};");
//		}}
//		if (null != entityName || entityName.length() > 0) {
//			genEntity(resFileName, entityName);
//		}
//
//		return new String[] { sb1.toString(), sb.toString(), sb2.toString() };
//	}

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
			File entitypackage = new File(
					CommUtitl.getAbsolutePath(CommUtitl.entityRelativeDir));
			if (!entitypackage.exists()) {
				entitypackage.mkdirs();
			}
			File newentityFile = new File(entitypackage, entityname + ".java");
			FileOutputStream fo = new FileOutputStream(newentityFile);
			StringBuilder sb = new StringBuilder();
			sb.append("package "
					+ CommUtitl.entityRelativeDir.replace("/", ".") + ";");
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

	public static String[] getNewAdapterCode(String resFileName,
			String entityName) {
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
		if (null !=res&& res.size() > 0) {
			sb.append("return new int[]{");
			sb1.append(entityName + " item =(" + entityName
					+ ")entitys.get(position);\n");
			for (IdNamePair idNamePair : res) {
				sb.append("R.id." + idNamePair.getId() + ",");
				if (idNamePair.getId().contains("_data")) {
					if (idNamePair.getName().equals("TextView")) {
						System.out.println("add textView");
						String viewname = idNamePair.getId().replace("_data",
								"");
						sb1.append("((TextView)vh.getView("
								+ idNamePair.getId() + ")).setText(item."
								+ viewname + ");");
					}
				}
			}
			sb.append("};");
		}
		if (null != entityName || entityName.length() > 0) {
			genEntity(resFileName, entityName);
		}
		return new String[] { sb.toString(), sb1.toString() };
	}
}