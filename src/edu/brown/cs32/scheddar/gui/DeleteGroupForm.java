package edu.brown.cs32.scheddar.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import edu.brown.cs32.scheddar.Group;

public class DeleteGroupForm extends AbstractForm {
	ScheddarPane _scheddarPane;
	JComboBox groupList;
	public DeleteGroupForm(ScheddarPane s) {
		super(s);
		_scheddarPane = s;
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(250,200));

		String[] groupNames = getAllGroupNames();
		groupList = new JComboBox<String>(groupNames);
		groupList.setSelectedItem(_scheddarPane.getCurrentGroup());
		groupList.setMaximumSize(new Dimension(250,25));
		panel.add(new JLabel("Group to Delete:"));
		panel.add(groupList);		
		
		// making "Remove Group" button
		JButton delete = new JButton("Remove Group");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!_scheddarPane._scheddar.getRootGroup().getName().equals((String)groupList.getSelectedItem())) {
					_scheddarPane._scheddar.removeGroup((String)groupList.getSelectedItem());
					_scheddarPane._groupTree.updateTree();
					dispose();
				}
			}
		});
		panel.add(delete);
		add(panel);
		pack();
		setVisible(true);
	}

}
