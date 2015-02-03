package com.fileplugin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class Test {

	public static void main(String[] args) {
		
	}

	public static void genadapter(String viewxmlpath) {
		Resource res = new Resource();
		System.out.println("genadapterviewxmlpath:" + viewxmlpath);
		String xmlName = viewxmlpath.substring(
				viewxmlpath.lastIndexOf("/") + 1,
				viewxmlpath.lastIndexOf(".xml"));
		try {
			File adapterparentfile = new File(CommUtitl.projPath + "src/"
					+ CommUtitl.adapterPackageName);
			if (!adapterparentfile.exists()) {
				adapterparentfile.mkdirs();
			}
			File baseadapterfile = new File(adapterparentfile,
					"SimpleAdapter.java");
			if (!baseadapterfile.exists()) {
				String strs = Utils
						.fromInputStreamToString(new FileInputStream(res
								.getBaseAdapterResource().getFile()));
				FileOutputStream fout = new FileOutputStream(adapterparentfile);
				fout.write(strs.getBytes("utf-8"));
				fout.flush();
				fout.close();
			}
			String strs = Utils.fromInputStreamToString(new FileInputStream(res
					.getAdapterResource().getFile()));
			String ss[] = ViewCodeUtil.getAdapterCode(viewxmlpath,
					CommUtitl.entityName);
			strs = strs.replace(
					"adapterpackageName",
					CommUtitl.adapterPackageName.replace("/", ".").substring(0,
							CommUtitl.adapterPackageName.length() - 1));
			strs = strs.replace("vhcontentinit", ss[0]);
			strs = strs.replace("holdercontent", ss[1]);
			System.out.println("----getAdapterCode-" + ss[2]);
			strs = strs.replace("setAdapterViewWithData", ss[2]);
			System.out.println(strs);
			strs = strs.replace("itemlayout", xmlName);
			strs = strs.replace("Tempadapter", CommUtitl.adapterName);

			adapterparentfile = new File(CommUtitl.projPath + "src/"
					+ CommUtitl.adapterPackageName);
			if (!adapterparentfile.exists()) {
				adapterparentfile.mkdirs();
			}
			File adapterfile = new File(adapterparentfile,
					CommUtitl.adapterName + ".java");
			FileOutputStream fo = new FileOutputStream(adapterfile);

			fo.write(strs.getBytes("utf-8"));
			fo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void genActivity(String layoutFil, String entityname) {
		String viewxmlname = layoutFil.substring(
				layoutFil.lastIndexOf("/") + 1, layoutFil.lastIndexOf("."));
		try {
			URL fileURL = Test.class.getResource("/temp/TempleteActivity1.txt");
			String strs = Utils.fromInputStreamToString(new FileInputStream(
					fileURL.getFile()));
			String ss[] = ViewCodeUtil.getCode(layoutFil);
			strs = strs.replace("classStatement", ss[0]);
			strs = strs.replace("contentlayout", viewxmlname);
			strs = strs.replace("findViewByIdscontent", ss[1]);
			String activityName = "";
			if (null == CommUtitl.activityname
					|| CommUtitl.activityname.trim().equals("")) {
				activityName = getActivityName(layoutFil.substring(
						layoutFil.lastIndexOf("/") + 1,
						layoutFil.lastIndexOf(".")));
			} else {
				activityName = CommUtitl.activityname;
			}
			strs = strs.replace("TempleteActivity1", activityName);
			File f = new File(CommUtitl.projPath + "src/"
					+ CommUtitl.activitypackageName);
			f.mkdirs();
			File f2 = new File(f.getAbsolutePath() + "/" + activityName
					+ ".java");
			f2.createNewFile();
			FileOutputStream fo = new FileOutputStream(f2);
			fo.write(strs.getBytes());
			fo.close();
			System.out.println("finish finish");
			if (null != entityname) {
				ViewCodeUtil.genEntity(layoutFil, entityname);
			}
		} catch (Exception e) {
			System.out.println("Exception:Exception");
			e.printStackTrace();
		}
	}

	private static String getActivityName(String xmlname) {
		int num = xmlname.lastIndexOf("_");
		String prfix = xmlname.substring(0, num);
		String subfix = xmlname.substring(num + 1);
		String acitivyname = CommUtitl.firstToupCast(prfix)
				+ CommUtitl.firstToupCast(subfix);
		return acitivyname;
	}
}