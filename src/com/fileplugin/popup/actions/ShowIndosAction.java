package com.fileplugin.popup.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.text.View;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.fileplugin.util.Resource;
import com.fileplugin.util.Utils;
import com.fileplugin.util.ViewCodeUtil;

public class ShowIndosAction implements IObjectActionDelegate {

	private Shell shell;
	private String path;

	/**
	 * Constructor for Action1.
	 */
	public ShowIndosAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		Resource res = new Resource();
		String ss[] = ViewCodeUtil.getCode(path);
		try {
			String strs = Utils.fromInputStreamToString(new FileInputStream(res
					.getDosCmd()));
			System.out.println("-----"+ss[0]);
			System.out.println("-----"+ss[1]);
			strs.replace("genStatementdata", ss[0]);
			strs.replace("genfindviewByIddata", ss[1]);
			System.out.println("-----"+strs);
			File file = new File(res.getDosCmd());
			file.setWritable(true);
			FileOutputStream fo = new FileOutputStream(file);
			fo.write(strs.getBytes("utf-8"));
			fo.flush();
			fo.close();
//			System.out.println("6@@@@@@@@@@@"+"cmd /k  start " + res.getDosCmd());
//			Runtime.getRuntime().exec(
//					"cmd /k  start echo" + ss[0]+ss[1]);
			FindViewByIdEditDialog dialog = new FindViewByIdEditDialog(ss[0]+""+ "\n"+ss[1]);
			dialog.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// try {
		// Process process =
		// Runtime.getRuntime().exec("cmd /k start echo ==========statement==========echo"+ss[0]+"echo======findviewByid========echo"+ss[1]);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {

		StructuredSelection treeSelection = (StructuredSelection) selection;
		IResource iFile = (IResource) treeSelection.getFirstElement();
		path = iFile.getLocation().toString();
		System.out.println(iFile.getLocation());
		System.out.println(iFile.getFullPath().toFile().getAbsolutePath());

	}

}
