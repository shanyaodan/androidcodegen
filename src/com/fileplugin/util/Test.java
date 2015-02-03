package com.fileplugin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.fileplugin.Activator;

public class Test {

	public static void main(String[] args) {
		// genActivity("head_askdetail", null);

	}

	public static void genadapter(String viewxmlpath) {
		Resource res = new Resource();
		// System.out.println(fileURL.getPath());
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
								.getBaseAdapterResource()));
				String packagname = CommUtitl.adapterPackageName.replace("/", ".")
						.substring(0, CommUtitl.adapterPackageName.length());
				strs = strs.replace("adapterpackageName", packagname + ";\nimport "
						+ CommUtitl.projPath.replace("/", ".") + "R;");
				
				FileOutputStream fout = new FileOutputStream(baseadapterfile);
				fout.write(strs.getBytes("utf-8"));
				fout.flush();
				fout.close();
			}

			System.out.println("jar path:" + res.getAdapterResource());
			String strs = Utils.fromInputStreamToString(new FileInputStream(res
					.getAdapterResource()));
			String ss[] = ViewCodeUtil.getAdapterCode(viewxmlpath,
					CommUtitl.entityName);
			String packagname = CommUtitl.adapterPackageName.replace("/", ".")
					.substring(0, CommUtitl.adapterPackageName.length());
			strs = strs.replace("adapterPachagename", packagname + ";\nimport "
					+CommUtitl.projPath.replace("/", ".") + "R;");
			strs = strs.replace("//vhcontentinit", ss[0]);
			strs = strs.replace("//holdercontent", ss[1]);
			strs = strs.replace("//setAdapterViewWithData", ss[2]);
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
			ILog log = Activator.getDefault().getLog();
			log.log(new Status(IStatus.OK, Activator.PLUGIN_ID, e.getMessage()));
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void genActivity(String layoutFil, String entityname) {

		Resource res = new Resource();
		String viewxmlname = layoutFil.substring(
				layoutFil.lastIndexOf("/") + 1, layoutFil.lastIndexOf("."));
		System.out.println("sssss:" + viewxmlname);
		try {

			String strs = Utils.fromInputStreamToString(new FileInputStream(res
					.getActivityResource()));
			String ss[] = ViewCodeUtil.getCode(layoutFil);
			strs = strs.replace("//classStatement", ss[0]);
			strs = strs.replace("//contentlayout", viewxmlname);
			strs = strs.replace("//findViewByIdscontent", ss[1]);

			String packagname = CommUtitl.activitypackageName.replace("/", ".")
					.substring(0, CommUtitl.activitypackageName.length());
			strs = strs.replace("activityPackageName", packagname
					+ ";\nimport " + CommUtitl.projPath.replace("/", ".") + "R;");

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
			// System.out.println("path path  :" + CommUtitl.projPath
			// + "src/"+CommUtitl.activitypackageName + activityName + ".java");
			File f = new File(CommUtitl.projPath + "src/"
					+ CommUtitl.activitypackageName);
			f.mkdirs();
			File f2 = new File(f.getAbsolutePath() + "/" + activityName
					+ ".java");
			System.out.println("dfsdfsdf" + f2.getAbsolutePath());
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
		String acitivyname = CommUtitl.firstToupCast(subfix)
				+ CommUtitl.firstToupCast(prfix);
		return acitivyname;
	}
}