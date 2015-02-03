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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.fileplugin.util.CommUtitl;
import com.fileplugin.util.Test;

public class FindViewByIdEditDialog extends JDialog {

	private JPanel jEditorPane = null;
	private String conent;

	/**
	 * This method initializes / public WatherDialog(String city) { super();
	 * this.city=city; initialize(); } /** This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setContentPane(getJEditorPane());

		this.setSize(500, 500);
	}

	public FindViewByIdEditDialog(String content) {
		// TODO Auto-generated constructor stub
		this.conent = content;
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
			JPanel buttonpannel = new JPanel();
			buttonpannel
					.setLayout(new BoxLayout(buttonpannel, BoxLayout.X_AXIS));
			JPanel entitypathpannel = new JPanel();
			entitypathpannel.setLayout(new BoxLayout(entitypathpannel,
					BoxLayout.X_AXIS));
			JPanel entityNamePannel = new JPanel();
			entityNamePannel.setLayout(new BoxLayout(entityNamePannel,
					BoxLayout.X_AXIS));

			final JTextArea ja = new JTextArea();
			entityNamePannel.add(ja);
			ja.setText(conent);
			final JButton obkbut = new JButton(" 确定  ");
			buttonpannel.add(obkbut);
			obkbut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					FindViewByIdEditDialog.this.setVisible(false);
				}
			});

			jEditorPane.add(entityNamePannel);

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