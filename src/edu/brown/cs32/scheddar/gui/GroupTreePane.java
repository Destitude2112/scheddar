package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

import edu.brown.cs32.scheddar.*;

/**
 * @author sdemane
 * 
 * A panel containing the tree of groups and subgroups.
 * 
 */
public class GroupTreePane extends ScheddarSubPane {
	
	private static final long serialVersionUID = 1L;
	ScheddarPane _gui;
	DummyScheddar _scheddar;
	
	JTree _tree;
	
	public GroupTreePane(ScheddarPane s) {
		super(s);
		
		setLayout(new GridLayout(1,1));
		
		DefaultMutableTreeNode top = constructTree(_scheddar.getTopGroup());
		_tree = new JTree(top);
		
		JScrollPane treeView = new JScrollPane(_tree);
		treeView.setMinimumSize(new Dimension(100,50));
		add(treeView);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(100,_gui.getHeight());
	}
	
	/**
	 * @return Name of group currently selected in tree
	 */
	public String getSelectedGroup() {
		if (_tree.getSelectionPath() == null) {
			return "";
		}
		
		TreeNode selection = (TreeNode) _tree.getSelectionPath().getLastPathComponent();
		
		if (selection.getAllowsChildren()) {
			return selection.toString();
		} else {
			selection = (TreeNode) _tree.getSelectionPath().getParentPath().getLastPathComponent();
			return selection.toString();
		}
	}
	
	
	private DefaultMutableTreeNode constructTree(DummyGroup g) {
		DefaultMutableTreeNode thisNode = new DefaultMutableTreeNode(g.toString());
		
		for (DummyGroup subgroup : g.getSubgroups()) {
			DefaultMutableTreeNode n = constructTree(subgroup);
			thisNode.add(n);
		}
		
		for (DummyPerson member : g.getMembers()) {
			thisNode.add(new DefaultMutableTreeNode(member,false));
		}
		
		return thisNode;
	}
}
