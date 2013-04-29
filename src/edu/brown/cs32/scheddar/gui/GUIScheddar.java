package edu.brown.cs32.scheddar.gui;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import edu.brown.cs32.scheddar.*;


/**
 * @author sdemane
 * 
 * This is the primary runnable class of the Scheddar application.
 * It initializes an application window waiting to be filled with
 * a ScheddarPanel which is project-specific. The ScheddarPanel is
 * initialized and added when a project is opened from a file or 
 * a new project is created.
 *
 */
public class GUIScheddar extends JFrame {
	
	public static final boolean DEBUG = true;
	
	private static final long serialVersionUID = 1L;
	Dimension _screenSize;
	ScheddarPane _scheddarPane;
	
	
	public GUIScheddar() {
		_scheddarPane = null;
		
		//_screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Scheddar");
		
		setMaximizedBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); 
		
		setJMenuBar(initMenuBar());
		
		setVisible(true);
		setResizable(false);
		_screenSize = this.getContentPane().getSize();
		
	}
	
	
	/**
	 * @return Menu bar for the primary Scheddar app, complete with listeners
	 */
	private JMenuBar initMenuBar() {
		boolean empty = _scheddarPane == null;
		
		JMenuBar mb = new JMenuBar();
		
		// creating file menu
		JMenu fileMenu = new JMenu("File");
		JMenuItem newScheddar = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		if (empty) save.setEnabled(false);
		JMenuItem exit = new JMenuItem("Exit");
		
		
		// adding listeners for file menu
		newScheddar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		fileMenu.add(newScheddar);
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.add(exit);
		
		// creating create menu
		JMenu createMenu = new JMenu("Create");
		if (empty) createMenu.setEnabled(false);
		JMenuItem person = new JMenuItem("New person");
		JMenuItem group = new JMenuItem("New group");
		JMenuItem meeting = new JMenuItem("New meeting");
		
		
		// adding listeners for create menu
		person.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PersonForm(_scheddarPane);
			}
		});
		
		group.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_scheddarPane.initializeMeeting();
			}
		});
		
		meeting.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PersonForm(_scheddarPane);
			}
		});
		
		createMenu.add(person);
		createMenu.add(group);
		createMenu.add(meeting);
		
		// creating email menu
		JMenu emailMenu = new JMenu("Email");
		if (empty) emailMenu.setEnabled(false);
		JMenuItem organizationEmail = new JMenuItem("Organization");
		JMenuItem personEmail = new JMenuItem("Individual");
		JMenuItem groupEmail = new JMenuItem("Group");
		JMenuItem meetingEmail = new JMenuItem("Meeting");
		
		
		// adding listeners for email menu
		organizationEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmailForm(_scheddarPane);
			}
		});
		
		personEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmailForm(_scheddarPane);
			}
		});
		
		groupEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmailForm(_scheddarPane);
			}
		});
		
		meetingEmail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmailForm(_scheddarPane);
			}
		});
		
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
		DummyScheddar s = new DummyScheddar("Project Blue");
		DummyGroup g0 = s.getTopGroup();
		
		DummyGroup g1a = new DummyGroup("Roquefort");
		DummyGroup g1b = new DummyGroup("Stilton");
		DummyGroup gb2a = new DummyGroup("Village Blue");
		
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
		
		new GUIScheddar();
	}
}
