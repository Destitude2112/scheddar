package edu.brown.cs32.scheddar.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	JMenuBar _mb;
	GUIScheddar frame;
	String group;
	
	
	public GUIScheddar() {
		super();
		_scheddarPane = null;
		frame = this;
		
		//_screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Scheddar");
		
		
		setMaximizedBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		//setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH); 
		setSize(getMaximizedBounds().getSize());
		initMenuBar();
		setJMenuBar(_mb);
		
		setVisible(true);
		setResizable(false);
		_screenSize = this.getContentPane().getSize();
		
	}
	
	private void initScheddarPane() {
		_scheddarPane = new ScheddarPane(this);
		this.add(_scheddarPane);
	}
	
	/**
	 * @return Menu bar for the primary Scheddar app, complete with listeners
	 */
	private JMenuBar initMenuBar() {
		boolean empty = (_scheddarPane == null);
//		boolean empty = false;
		
		_mb = new JMenuBar();
		
		// creating file menu
		JMenu fileMenu = new JMenu("File");
		JMenuItem newScheddar = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		if (empty) save.setEnabled(false); // Enable this somehow
		JMenuItem exit = new JMenuItem("Exit");
		
		
		// adding listeners for file menu
		newScheddar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initScheddarPane();
				new ScheddarForm(_scheddarPane,frame);
				//TODO: Handle initialization if box is cancelled.
				
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
		
		_mb.add(fileMenu);
		_mb.add(createMenu);
		_mb.add(emailMenu);
		return _mb;
	}
	
	public JMenuBar getMenu() {
		return _mb;
	}
	
	public void setGroup(String g) {
		group = g;
		if(g!=null && g.length()>0) {
			setTitle("Scheddar - "+g);
		}
	}
	
	/**
	 *                                                                              _ _                                                                               
	 *                                                                 ___   ___   (/(/  ___                                                                          
	 *                                                           ____ (___) (___)       (___)   _                                                                     
	 *                                                          (____)                         (_)                                                                    
	 *                                                      _ _                                       _ _                                                             
	 *                                               ___   (/(/                                      (/(/  ___                                                        
	 *                                          _   (___)                                                 (___)   _                                                   
	 *                                         (_)                                                               (_)                                                  
	 *                                    _     _ _   _ _                                                                _                                            
	 *                                   ( \   (/(/  (/(/  ___   ___                                                    (/   ___                                      
	 *                                   / /              (___) (___)   _     _                                             (___)   _                                 
	 *                                   \_)                           (_)   (_)                                                   (_)                                
	 *                                    _                                         _ _   _ _                                              _                          
	 *                                   ( \                                       (/(/  (/(/  ___   ___                                  (/   ___                    
	 *                                   / /                                                  (___) (___)   _     _                           (___)   _               
	 *                                   \_)                                                               (_)   (_)                                 (_)              
	 *                                    _                                         _ _                                 _ _   _ _                           _ _       
	 *                                   ( \         ___                     ___   (/(/                                (/(/  (/(/  ___   ___               (/(/       
	 *                                   / /    _   (___)   _           _   (___)         _                                       (___) (___)   _     _           _   
	 *                                   \_)   (_)         (_)         (_)               (_)                                                   (_)   (_)         (_)  
	 *                                    _      _           _                             _                                                                      _   
	 *                                   ( \    (/          / )              ___          (/                     ___                                             ( \  
	 *                                   / /          _    / /              (___)  ____                     _   (___)   _                                        / /  
	 *                                   \_)         (_)  (_/                     (____)                   (_)         (_)                                       \_)  
	 *                                    _                                                                  _                 _                                   _  
	 *                                   ( \                                             ___                (/         ___    (/                           ___    (/  
	 *                                   / /                                        _   (___)   _                 _   (___)                           _   (___)       
	 *                                   \_)                                       (_)         (_)               (_)                                 (_)              
	 *                                     _                                         _           _                                                                    
	 *                                    (/   ___   ___                            (/          (/                                 ___               ___              
	 *                                        (___) (___)   _     _                       _                                   _   (___)             (___)   _         
	 *                                                     (_)   (_)                     (_)                                 (_)                           (_)        
	 *                                                                  _ _   _ _                                              _           _                      _   
	 *                                                                 (/(/  (/(/  ___   ___                                  (/          (/                     (_)  
	 *                                                                            (___) (___)   _     _                            ____                           _   
	 *                                                                                         (_)   (_)                          (____)                         (_)  
	 *                                                                                                      _ _   _ _                                             _   
	 *                                                                                                     (/(/  (/(/  ___   ___                                 ( \  
	 *                                                                                                                (___) (___)   _     _                      / /  
	 *                                                                                                                             (_)   (_)                     \_)  
	 *                                                                                                                                          _ _   _ _          _  
	 *                                                                                                                                         (/(/  (/(/  ___    (/  
	 *                                                                                                                                                    (___)       
	 *                                                                                                                                                                
	 */
	public static void main(String[] args) {
		
		// TODO: when the program opens, there should be a dialog asking if the user wants to make a new scheddar or open an existing one
		// After that, the actual GUI should open.
		
		JFrame startFrame = new JFrame();
		JLabel l1 = new JLabel("Welcome to Scheddar, the cheesy scheduling app!");
		JLabel l2 = new JLabel("Would you like to start a new Scheddar project, or open an existing one?");
		JButton newButton = new JButton("New project");
		JButton openButton = new JButton("Open project");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		l1.setAlignmentX(CENTER_ALIGNMENT);
		l2.setAlignmentX(CENTER_ALIGNMENT);
		newButton.setAlignmentX(CENTER_ALIGNMENT);
		openButton.setAlignmentX(CENTER_ALIGNMENT);
		
		Scheddar s;
		
		newButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		openButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		panel.add(Box.createVerticalStrut(8));
		panel.add(l1);
		panel.add(Box.createVerticalStrut(5));
		panel.add(l2);
		panel.add(Box.createVerticalStrut(5));
		panel.add(newButton);
		panel.add(Box.createVerticalStrut(3));
		panel.add(openButton);
		panel.add(Box.createVerticalStrut(8));
		
		startFrame.add(panel);
		startFrame.pack();
		startFrame.setTitle("Scheddar");
		startFrame.setLocationRelativeTo(null);
		startFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		startFrame.setVisible(true);
		
//		new GUIScheddar();
	}
}
