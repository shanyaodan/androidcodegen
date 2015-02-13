package com.fileplugin.popup.actions;

import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class GenAdapterAction implements IObjectActionDelegate {

	private Shell shell;
	private String path;

	/**
	 * Constructor for Action1.
	 */
	public GenAdapterAction() {
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
		EditDialog wd = new EditDialog(path,2);
		wd.setSize(400, 335);
		wd.setVisible(true);
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {

		StructuredSelection treeSelection = (StructuredSelection) selection;
		IResource iFile = (IResource) treeSelection.getFirstElement();
		path = iFile.getLocation().toString();
	}

}
