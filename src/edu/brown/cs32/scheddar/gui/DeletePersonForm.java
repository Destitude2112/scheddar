package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.brown.cs32.scheddar.Person;

public class DeletePersonForm extends AbstractForm {
	private static final long serialVersionUID = 1L;
	JComboBox<String> personList;
	boolean _toDelete;
	public DeletePersonForm(ScheddarPane s, boolean toDelete) {
		super(s);
		_scheddarPane = s;
		_toDelete = toDelete;
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(250,90));
		ArrayList<String> people = new ArrayList<String>();
		for(Person p : _scheddarPane._scheddar.getAllPeople()) {
			people.add(p.getFullName());
		}
		
		String[] names = people.toArray(new String[people.size()]);		
		personList = new JComboBox<String>(names);
		personList.setSelectedItem(_scheddarPane.getCurrentGroup());
		personList.setMaximumSize(new Dimension(250,25));
		panel.add( toDelete ? new JLabel("Person to Delete:") : new JLabel("Person to Edit:"));
		panel.add(personList);
		
		// making "Remove Group" button
		JButton delete = new JButton("Remove Person");
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(_toDelete) {
					_scheddarPane._scheddar.removePerson((String)personList.getSelectedItem());
					if(_scheddarPane._scheddar.getAllPeople().size()==0) {
						_scheddarPane._gui._mb.getMenu(2).getMenuComponent(0).setEnabled(false);
						_scheddarPane._gui._mb.getMenu(2).getMenuComponent(2).setEnabled(false);
					}
					_scheddarPane._groupTree.updateTree();
				} else {
					new EditPersonForm(_scheddarPane, _scheddar.getPersonFromName((String)personList.getSelectedItem()));
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
