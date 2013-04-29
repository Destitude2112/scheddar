package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.brown.cs32.scheddar.DummyGroup;
import edu.brown.cs32.scheddar.DummyPerson;
import edu.brown.cs32.scheddar.DummyScheddar;

public class GroupTree extends JPanel {
	
	private static final long serialVersionUID = 1L;
	GUIScheddar _gui;
	DummyScheddar _scheddar;
	
	public GroupTree(GUIScheddar g, DummyScheddar s) {
		super(new GridLayout(1,1));
		
		_gui = g;
		_scheddar = s;
		
		JScrollPane treeView = new JScrollPane(initGroupTree());
		treeView.setMinimumSize(new Dimension(100,50));
		add(treeView);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(_gui.getWidth()/6,_gui.getHeight());
	}
	
	private JTree initGroupTree() {
		DefaultMutableTreeNode top = constructTree(_scheddar.getTopGroup());
		JTree tree = new JTree(top);
		return tree;
	}
	
	private DefaultMutableTreeNode constructTree(DummyGroup g) {
		DefaultMutableTreeNode thisNode = new DefaultMutableTreeNode(g.toString());
		
		for (DummyGroup subgroup : g.getSubgroups()) {
			DefaultMutableTreeNode n = constructTree(subgroup);
			thisNode.add(n);
		}
		
		for (DummyPerson member : g.getMembers()) {
			thisNode.add(new DefaultMutableTreeNode(member));
		}
		
		return thisNode;
	}
}
