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

	
	JTree _tree;
	DefaultMutableTreeNode _topNode;
	JScrollPane _treeView;
	
	public GroupTreePane(ScheddarPane s) {
		super(s);
		
		setLayout(new GridLayout(1,1));
		
		_topNode = constructTree(_scheddar.getRootGroup());
		_tree = new JTree(_topNode);
		
		_treeView = new JScrollPane(_tree);
		_treeView.setMinimumSize(new Dimension(100,50));
		add(_treeView);
	}
	
	public void updateTree() {
		
		_topNode = constructTree(_scheddar.getRootGroup());
		_tree = new JTree(_topNode);
		
		_treeView = new JScrollPane(_tree);
		remove(0);
		add(_treeView,0);
		revalidate();
	}
	
	public Dimension getPreferredSize() {
		Dimension d = _scheddarPane.getPreferredSize();
		return new Dimension(d.width / 8, d.height);
	}
	
	/**
	 * @return Name of group currently selected in tree.
	 * If a person is selected, it returns the immediate parent.
	 * If nothing is selected, it returns the root group.
	 */
	public String getSelectedGroup() {
		if (_tree.getSelectionPath() == null) {
			return _scheddarPane.getRootGroupName();
		}
		
		TreeNode selection = (TreeNode) _tree.getSelectionPath().getLastPathComponent();
		
		if (selection.getAllowsChildren()) {
			return selection.toString();
		} else {
			selection = (TreeNode) _tree.getSelectionPath().getParentPath().getLastPathComponent();
			return selection.toString();
		}
	}
	
	
	private DefaultMutableTreeNode constructTree(Group g) {
		DefaultMutableTreeNode thisNode = new DefaultMutableTreeNode(g.toString());
		
		for (Group subgroup : g.getSubgroups()) {
			DefaultMutableTreeNode n = constructTree(subgroup);
			thisNode.add(n);
		}
		
		for (Person member : g.getMembers()) {
			DefaultMutableTreeNode person = new DefaultMutableTreeNode(member.getFullName());
			thisNode.add(person);
		}
		return thisNode;
	}
}
