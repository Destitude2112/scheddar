package edu.brown.cs32.scheddar;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class GUIScheddar extends JFrame {
	
	public static final boolean DEBUG = true;
	
	private static final long serialVersionUID = 1L;
	private Scheddar _scheddar;
	private Dimension _screenSize;
	private GroupTree _tree;
	
	
	public GUIScheddar(Scheddar s) {
		_scheddar = s;
		_screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Scheddar");
		setSize(new Dimension((int)_screenSize.getWidth()/2, (int)_screenSize.getHeight()/2));
		
		
		setJMenuBar(initMenuBar());
		
		_tree = new GroupTree(this, _scheddar);
		
		JPanel calendarPlaceholder = new JPanel() {
			public Dimension getPreferredSize() {
				return new Dimension((int)(getSize().getWidth() - _tree.getPreferredSize().getWidth()), (int)getSize().getHeight());
			}
		};
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
		panel.add(_tree);
		panel.add(calendarPlaceholder);
		
		add(panel);
		
		//pack();
		setVisible(true);
	}
	
	
	private JMenuBar initMenuBar() {
		JMenuBar mb = new JMenuBar();
		
		// creating file menu
		JMenu fileMenu = new JMenu("File");
		JMenuItem newScheddar = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem exit = new JMenuItem("Exit");
		fileMenu.add(newScheddar);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(exit);
		
		// creating create menu
		JMenu createMenu = new JMenu("Create");
		JMenuItem person = new JMenuItem("New person");
		JMenuItem group = new JMenuItem("New group");
		JMenuItem meeting = new JMenuItem("New meeting");
		createMenu.add(person);
		createMenu.add(group);
		createMenu.add(meeting);
		
		// creating email menu
		JMenu emailMenu = new JMenu("Email");
		JMenuItem organizationEmail = new JMenuItem("Organization");
		JMenuItem personEmail = new JMenuItem("Individual");
		JMenuItem groupEmail = new JMenuItem("Group");
		JMenuItem meetingEmail = new JMenuItem("Meeting");
		emailMenu.add(organizationEmail);
		emailMenu.add(personEmail);
		emailMenu.add(groupEmail);
		emailMenu.add(meetingEmail);
		
		mb.add(fileMenu);
		mb.add(createMenu);
		mb.add(emailMenu);
		
		return mb;
	}
	
	public static void main(String[] args) {
		Scheddar s = new Scheddar("Project Blue");
		Group g0 = s.getTopGroup();
		
		Group g1a = new Group("Roquefort");
		Group g1b = new Group("Stilton");
		Group gb2a = new Group("Village Blue");
		
		gb2a.addMember("Tina");
		gb2a.addMember("Bob");
		g1b.addMember("Sam");
		g1b.addMember("Jenn");
		g1a.addMember("Tim");
		g1a.addMember("Rafael");
		g1a.addMember("Siri");
		
		g1b.add(gb2a);
		g0.add(g1a);
		g0.add(g1b);
		s.setTopGroup(g0);
		
		new GUIScheddar(s);
	}
}
