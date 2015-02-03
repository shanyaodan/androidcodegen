/* 
 * Created on 2004-9-23 
 * */
package com.fileplugin.popup.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fileplugin.util.CommUtitl;
import com.fileplugin.util.Test;

public class EditDialog extends JDialog {

	private JPanel jEditorPane = null;
	private String path = "";
	private int type;

	/**
	 * This method initializes / public WatherDialog(String city) { super();
	 * this.city=city; initialize(); } /** This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJEditorPane());

		this.setSize(400, 166);
	}

	public EditDialog(String path, int type) {
		this.path = path;
		this.type = type;
		// TODO Auto-generated constructor stub
		initialize();

	}

	/**
	 * This method initializes jEditorPane
	 * 
	 * @return javax.swing.JEditorPane
	 */
	private JPanel getJEditorPane() {
		if (jEditorPane == null) {
			jEditorPane = new JPanel();

			JPanel activitypackagepath = new JPanel();
			activitypackagepath.setLayout(new BoxLayout(activitypackagepath,
					BoxLayout.X_AXIS));

			JPanel buttonpannel = new JPanel();
			buttonpannel
					.setLayout(new BoxLayout(buttonpannel, BoxLayout.X_AXIS));
			JPanel entitypathpannel = new JPanel();
			entitypathpannel.setLayout(new BoxLayout(entitypathpannel,
					BoxLayout.X_AXIS));
			JPanel entityNamePannel = new JPanel();
			entityNamePannel.setLayout(new BoxLayout(entityNamePannel,
					BoxLayout.X_AXIS));
			JPanel adapterNamePannel = new JPanel();
			adapterNamePannel.setLayout(new BoxLayout(adapterNamePannel,
					BoxLayout.X_AXIS));

			JLabel activitypackage = new JLabel("activityPackage:");
			final JTextField activitypackageNameStr = new JTextField(20);
			activitypackagepath.add(activitypackage);
			activitypackagepath.add(activitypackageNameStr);

			JLabel entityPath = new JLabel("  entityPackage:");
			final JTextField entityPath_data = new JTextField(20);
			entitypathpannel.add(entityPath);
			entitypathpannel.add(entityPath_data);

			JLabel entityName = new JLabel("    entityName:");
			final JTextField entityName_data = new JTextField(20);
			entityNamePannel.add(entityName);
			entityNamePannel.add(entityName_data);

			JLabel adapterName = new JLabel("    adapterName:");
			final JTextField adapterName_data = new JTextField(20);
			adapterNamePannel.add(adapterName);
			adapterNamePannel.add(adapterName_data);

			final JButton obkbut = new JButton(" 确定  ");
			JButton cancelbut = new JButton(" 取消  ");
			buttonpannel.add(obkbut);
			buttonpannel.add(cancelbut);

			obkbut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					CommUtitl.projPath = path.substring(0, path.indexOf("/res"))
							+ "/";
					System.out.println("projectPath" + CommUtitl.projPath);

					CommUtitl.entityName = getStr(entityName_data);
					Properties props = new Properties();

					try {
						FileReader fr = new FileReader(new File(
								CommUtitl.projPath + "andgen.properties"));

						props.load(fr);
						fr.close();
					} catch (Exception e1) {

						e1.printStackTrace();
					}

					CommUtitl.projPackage = props.getProperty("projpackage",
							"com/temp/proj/");

					CommUtitl.entityppackage = props
							.getProperty("entityppackage",
									CommUtitl.projPackage + "entity/");
					CommUtitl.activitypackageName = props.getProperty(
							"activitypackageName", CommUtitl.projPackage
									+ "activity");
					CommUtitl.adapterPackageName = props.getProperty(
							"adapterPackageName", CommUtitl.projPackage
									+ "adapter/");
					CommUtitl.adapterName = getStr(adapterName_data);
					if (null != CommUtitl.activitypackageName) {
						CommUtitl.activitypackageName = CommUtitl.activitypackageName
								.replace(".", "/");
					}
					if (null != CommUtitl.entityppackage) {
						CommUtitl.entityppackage = CommUtitl.entityppackage
								.replace(".", "/");
					}
					if (null != CommUtitl.adapterPackageName) {
						CommUtitl.adapterPackageName = CommUtitl.adapterPackageName
								.replace(".", "/");
					}
					System.out.println(CommUtitl.entityppackage);
					System.out.println(CommUtitl.activitypackageName);

					if (type == 1) {
						Test.genActivity(path, null);
					} else if (type == 2) {
						Test.genadapter(path);
					}
					EditDialog.this.setVisible(false);
				}
			});
			cancelbut.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					EditDialog.this.setVisible(false);
				}
			});
			jEditorPane.add(entityNamePannel);
			if (type == 2) {
				jEditorPane.add(adapterNamePannel);
			}
			jEditorPane.add(buttonpannel);
		}
		return jEditorPane;
	}

	public String getStr(JTextField jf) {
		if (null != jf && null != jf.getText()) {
			return jf.getText().toString().trim();
		} else {
			return "";
		}

	}

}