package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import edu.brown.cs32.scheddar.Group;
import edu.brown.cs32.scheddar.Person;
import edu.brown.cs32.scheddar.Scheddar;

public class EditPane extends AbstractForm {

	private static final long serialVersionUID = 1L;

	Group g;
	JComboBox<String> groupMembers;
	public EditPane(ScheddarPane s, Group group) {
		super(s);
		g = group;
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(250,100));
		List<String> names = new ArrayList<String>();
		for(Person p : _scheddar.getAllPeople()) {
			names.add(p.getFullName());
		}

		final JComboBox<String> importanceLabels = new JComboBox<String>(Scheddar.importanceArrayLabels);
		groupMembers = new JComboBox<String>(names.toArray(new String[0]));
		if(g.getMemberRankings()!=null && !g.getMemberRankings().isEmpty()){
			importanceLabels.setSelectedIndex((int)(Math.log(g.getMemberRanking(_scheddar.getPersonFromName((String)groupMembers.getSelectedItem())))/Math.log(2))-1);
		}
		groupMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importanceLabels.setSelectedIndex((int)(Math.log(g.getMemberRanking(_scheddar.getPersonFromName((String)groupMembers.getSelectedItem())))/Math.log(2))-1);
			}
		});

		panel.add(groupMembers);
		panel.add(importanceLabels);

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				g.addMemberRanking((String)groupMembers.getSelectedItem(), Scheddar.importanceArrayValues[importanceLabels.getSelectedIndex()]);
			}
		});
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		panel.add(importanceLabels);
		panel.add(save);
		panel.add(close);
		add(panel);
		this.pack();
		this.setVisible(true);
		
	}
	
}
