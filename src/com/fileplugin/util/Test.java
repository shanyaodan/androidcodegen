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
				String packagname = CommUtitl.adapterPackageName.replace("/",".");
				strs = strs.replace(
						"adapterpackageName",
						packagname + ";\nimport "
								+ CommUtitl.projPackage.replace("/", ".")
								+ ".R;");

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
			String packagname = CommUtitl.adapterPackageName.replace("/", ".");
			strs = strs.replace("adapterPachagename", packagname);
			strs = strs.replace("projPath",CommUtitl.projPackage.replace("/", "."));

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
			strs = strs.replace("contentlayout", viewxmlname);
			strs = strs.replace("//findViewByIdscontent", ss[1]);

			String packagname = CommUtitl.activitypackageName.replace("/", ".")
					.substring(0, CommUtitl.activitypackageName.length());
			strs = strs.replace("activityPackageName", packagname
					+ ";\nimport " + CommUtitl.projPackage.replace("/", ".")
					+ ".R;");

			String activityName = "";
			if (null == CommUtitl.activityname
					|| CommUtitl.activityname.trim().equals("")) {
				activityName = getXMLClassName(layoutFil.substring(
						layoutFil.lastIndexOf("/") + 1,
						layoutFil.lastIndexOf(".")));
			} else {
				activityName = CommUtitl.activityname;
			}
			strs = strs.replace("TempleteActivity1", activityName);
			File f = new File(CommUtitl.projPath + "src/"+ CommUtitl.activitypackageName);
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

	public static void getFragment(String layoutFil) {

		Resource res = new Resource();
		String viewxmlname = layoutFil.substring(
				layoutFil.lastIndexOf("/") + 1, layoutFil.lastIndexOf("."));
		try {
			String strs = Utils.fromInputStreamToString(new FileInputStream(res
					.getFragmentResource()));
			String ss[] = ViewCodeUtil.getCode(layoutFil);
			strs = strs.replace("//classStatement", ss[0]);
			strs = strs.replace("fragmentlayout", viewxmlname);
			strs = strs.replace("//findViewByIdContent", ss[1]);
			String packagname = CommUtitl.fragmentpackagename.replace("/", ".");
			strs = strs.replace("fragmentPackageName", packagname);
			strs = strs.replace("projectpackage", CommUtitl.projPackage.replace("/", "."));
			String fragmentName = "";
			if (null == CommUtitl.fragmentname
					|| CommUtitl.fragmentname.trim().equals("")) {
				fragmentName = getXMLClassName(layoutFil.substring(layoutFil.lastIndexOf("/") + 1,layoutFil.lastIndexOf(".")));
			} else {
				fragmentName = CommUtitl.fragmentname;
			}
			strs = strs.replace("TempleteFragment", fragmentName);
			strs = strs.replace("findViewById(", "rootView.findViewById(");
			File f = new File(CommUtitl.projPath + "src/"+ CommUtitl.fragmentpackagename);
			f.mkdirs();
			File f2 = new File(f.getAbsolutePath() + "/" + fragmentName + ".java");
			f2.createNewFile();
			FileOutputStream fo = new FileOutputStream(f2);
			fo.write(strs.getBytes());
			fo.close();
			if (null != CommUtitl.entityName) {
				ViewCodeUtil.genEntity(layoutFil, CommUtitl.entityName);
			}
		} catch (Exception e) {
			System.out.println("Exception:Exception");
			e.printStackTrace();
		}

	}

	private static String getXMLClassName(String xmlname) {
		String acitivyname = "";
		try {
		int num = xmlname.lastIndexOf("_");
		String prfix = xmlname.substring(0, num);
		String subfix = xmlname.substring(num + 1);
		 acitivyname = CommUtitl.firstToupCast(subfix)
				+ CommUtitl.firstToupCast(prfix);
		}catch(Exception e) {
			acitivyname =CommUtitl.firstToupCast(xmlname) ;
		}
		
		return acitivyname;
	}
}