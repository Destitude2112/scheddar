package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeleteGroupForm extends AbstractForm {
	private static final long serialVersionUID = 1L;
	ScheddarPane _scheddarPane;
	JComboBox<String> groupList;
	boolean _toDelete;
	public DeleteGroupForm(ScheddarPane s, boolean toDelete) {
		super(s);
		_scheddarPane = s;
		_toDelete = toDelete;
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(300,60));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2,5,5));
		topPanel.setPreferredSize(new Dimension(250,50));
		String[] groupNames = getAllGroupNames();
		groupList = new JComboBox<String>(groupNames);
		groupList.setSelectedItem(_scheddarPane.getCurrentGroup());
		groupList.setPreferredSize(new Dimension(250,25));
		topPanel.add(toDelete ? new JLabel("Group to Delete:") : new JLabel("Group to Edit"));
		topPanel.add(groupList);		
		panel.add(topPanel);
		
		// making "Remove Group" button
		JButton delete = toDelete ? new JButton("Remove Group") : new JButton("Edit Group");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(_toDelete) {
					if(!_scheddarPane._scheddar.getRootGroup().getName().equals((String)groupList.getSelectedItem())) { // If not the root group
						_scheddarPane._scheddar.removeGroup((String)groupList.getSelectedItem());
						_scheddarPane._groupTree.updateTree();
					}
				} else {
					new EditGroupForm(_scheddarPane, _scheddar.getGroupFromName((String)groupList.getSelectedItem()));
				}
				dispose();
			}
		});
		panel.add(delete);
		add(panel);
		pack();
		setVisible(true);
	}

}
