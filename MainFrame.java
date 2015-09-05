import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.*;

public class MainFrame extends JFrame implements ActionListener
{
	JButton bttn;
	JFileChooser fch = new JFileChooser();
	User currentUser = new User();
	
	public MainFrame(String title)
	{
		super(title);
		setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setIconImage(new ImageIcon("mmuico.png", "").getImage());
		WindowUtilities.setNativeLookAndFeel();
		SwingUtilities.updateComponentTreeUI(fch);
		
		LoginDialog loginPrompt = new LoginDialog(this);
		currentUser = loginPrompt.acquireUser();
		System.out.println(currentUser.getUsername() + " is signed in!");
		
		System.out.println("List of users:\n{");
		for(User us : loginPrompt.getUserList())
		{
			System.out.println("\t" + us);
		}
		System.out.println("}");
		
		setMenus();
		setPanels();
		
		setExtendedState(MainFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
	public void setMenus()
	{
		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;
		
		//File menu  ----------------------------------------------------------------------------------------------------------|
		menu = new JMenu(" File ");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription("The menu for file related operation");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("New", KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open...", KeyEvent.VK_O);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This only opens the File Chooser");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save As...", KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This only opens the File Chooser");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit", KeyEvent.VK_Q);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This exits the program");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		//Edit menu  ----------------------------------------------------------------------------------------------------------|
		menu = new JMenu(" Edit ");
		menu.setMnemonic(KeyEvent.VK_E);
		menu.getAccessibleContext().setAccessibleDescription("The menu for editing");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Cut", KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Copy", KeyEvent.VK_C);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Paste", KeyEvent.VK_P);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		//User menu  ----------------------------------------------------------------------------------------------------------|
		menu = new JMenu(" User ");
		menu.setMnemonic(KeyEvent.VK_U);
		menu.getAccessibleContext().setAccessibleDescription("The menu for managing users");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Register", KeyEvent.VK_R);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This register guest as user");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Authorize Registration", KeyEvent.VK_I);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This authorize the user registrations");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		//Help menu  ----------------------------------------------------------------------------------------------------------|
		menu = new JMenu(" Help ");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.getAccessibleContext().setAccessibleDescription("The menu for help");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("About", KeyEvent.VK_A);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		//  -------------------------------------------------------------------------------------------------------------------|
		
		setJMenuBar(menuBar);
	}
	
	public void setPanels()
	{
		Container content = getContentPane();
		content.setBackground(Color.WHITE);
		content.setLayout(new BorderLayout());
		
		JToolBar toolBar = new JToolBar("Trivial Toolbar");
		bttn = new JButton(" A ");
		toolBar.add(bttn);
		bttn = new JButton(" B ");
		toolBar.add(bttn);
		bttn = new JButton(" C ");
		toolBar.add(bttn);
		content.add(toolBar, BorderLayout.PAGE_START);
		
		content.add(new OperationPanel(currentUser), BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
		bottomPanel.add(Box.createHorizontalStrut(5));
		bottomPanel.add(new JButton("Setting"));
		bottomPanel.add(Box.createHorizontalStrut(5));
		bottomPanel.add(new JSeparator(JSeparator.VERTICAL));
		bottomPanel.add(Box.createHorizontalStrut(5));
		bottomPanel.add(new JLabel("Welcome! You are signed in as "));
		bottomPanel.add(new JLabel(currentUser.getUsername()));
		bottomPanel.add(Box.createHorizontalStrut(5));
		bottomPanel.add(new JSeparator(JSeparator.VERTICAL));
		bottomPanel.add(Box.createHorizontalStrut(5));
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(Box.createHorizontalGlue());
		//bottomPanel.add(new JLabel("                                                                                                                                                               "));
		//bottomPanel.add(Box.createHorizontalStrut(5));
		//bottomPanel.add(new JSeparator(JSeparator.VERTICAL));
		//bottomPanel.add(Box.createHorizontalStrut(5));
		//bottomPanel.add(new JLabel("Made by Asyraf"));
		//bottomPanel.add(Box.createHorizontalStrut(5));
		//bottomPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		content.add(bottomPanel, BorderLayout.PAGE_END);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		String btnText = evt.getActionCommand();
		
		if(btnText.equals("Exit"))
		{
			System.exit(0);
		}
		else if(btnText.equals("Open..."))
		{
			int returnVal = fch.showOpenDialog(MainFrame.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //File file = fch.getSelectedFile();
                //This is where a real application would open the file.
				
				JOptionPane.showMessageDialog(this, "Can't open anything yet!");
            }
		}
		else if(btnText.equals("Save As..."))
		{
			int returnVal = fch.showSaveDialog(MainFrame.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //File file = fch.getSelectedFile();
                //This is where a real application would open the file.
				
				JOptionPane.showMessageDialog(this, "Can't save anything yet!");
            }
		}
		else if(btnText.equals("Register"))
		{
			RegisterDialog registerPrompt = new RegisterDialog(this);
		}
		else if(btnText.equals("Authorize Registration"))
		{
			InvitationDialog invitePrompt = new InvitationDialog(this);
		}
		else
		{
			JOptionPane.showMessageDialog(this, btnText + " button was clicked!");
		}
	}
}
