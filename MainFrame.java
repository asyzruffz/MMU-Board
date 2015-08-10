import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.*;

public class MainFrame extends JFrame implements ActionListener, MouseListener
{
	JLabel cood = new JLabel("Waiting mouse: ");
	JButton bttn;
	JFileChooser fch;
	
	public MainFrame(String title)
	{
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 600);
		addMouseListener(this);
		setIconImage(new ImageIcon("mmuico.png", "").getImage());
		fch = new JFileChooser();
		WindowUtilities.setNativeLookAndFeel();
		SwingUtilities.updateComponentTreeUI(fch);
		setPanels();
	}
	
	public void setMenus()
	{
		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();
		
		//Build the menu.
		JMenu menu1 = new JMenu(" File ");
		menu1.setMnemonic(KeyEvent.VK_F);
		menu1.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu1);
		
		JMenuItem menuItem;
		//a group of JMenuItems
		menuItem = new JMenuItem("New", KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu1.add(menuItem);
		
		menuItem = new JMenuItem("Open...", KeyEvent.VK_O);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu1.add(menuItem);
		
		menuItem = new JMenuItem("Save As...", KeyEvent.VK_S);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu1.add(menuItem);
		
		menu1.addSeparator();
		
		menuItem = new JMenuItem("Exit", KeyEvent.VK_Q);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu1.add(menuItem);
		
		JMenu menu2 = new JMenu(" Edit ");
		menu2.setMnemonic(KeyEvent.VK_E);
		menu2.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu2);
		
		menuItem = new JMenuItem("Cut", KeyEvent.VK_X);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu2.add(menuItem);
		
		menuItem = new JMenuItem("Copy", KeyEvent.VK_C);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu2.add(menuItem);
		
		menuItem = new JMenuItem("Paste", KeyEvent.VK_P);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu2.add(menuItem);
		
		JMenu menu3 = new JMenu(" Help ");
		menu3.setMnemonic(KeyEvent.VK_H);
		menu3.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu3);
		
		menuItem = new JMenuItem("About", KeyEvent.VK_A);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(this);
		menu3.add(menuItem);
		
		setJMenuBar(menuBar);
	}
	
	public void setPanels()
	{
		setMenus();
		
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
		
		JTextArea tree = new JTextArea();
		JScrollPane treeScrollPane = new JScrollPane(tree);
		tree.setEditable(false);
		JPanel middlePanel = new JPanel();
		//middlePanel.setLayout(new GridLayout(2, 0));
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.PAGE_AXIS));
		JTextArea note = new JTextArea(50, 0);
		JScrollPane textScrollPane = new JScrollPane(note);
		middlePanel.add(textScrollPane);
		JPanel messagePanel = new JPanel();
		JTextField messageBar = new JTextField("Enter your post here...");
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.LINE_AXIS));
		messagePanel.add(messageBar);
		messagePanel.add(new JButton("Post"));
		middlePanel.add(messagePanel);
		
		
		//Create a split pane with the two scroll panes in it.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
								   treeScrollPane, middlePanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(200);
		splitPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
															   BorderFactory.createLoweredBevelBorder()));
		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(200, 50);
		treeScrollPane.setMinimumSize(minimumSize);
		textScrollPane.setMinimumSize(minimumSize);
		
		//middlePanel.add(splitPane);
		//content.add(middlePanel, BorderLayout.CENTER);
		content.add(splitPane, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
		bottomPanel.add(new JTextField());
		bottomPanel.add(Box.createHorizontalStrut(5));
		bottomPanel.add(new JButton("Search"));
		bottomPanel.add(Box.createHorizontalStrut(5));
		bottomPanel.add(new JSeparator(JSeparator.VERTICAL));
		bottomPanel.add(Box.createHorizontalStrut(5));
		bottomPanel.add(new JLabel("                                                                                                                                                               "));
		bottomPanel.add(Box.createHorizontalStrut(5));
		bottomPanel.add(new JSeparator(JSeparator.VERTICAL));
		bottomPanel.add(Box.createHorizontalStrut(5));
		bottomPanel.add(new JLabel("Made by Asyraf"));
		bottomPanel.add(Box.createHorizontalStrut(5));
		//bottomPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		content.add(bottomPanel, BorderLayout.PAGE_END);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		String btnText = evt.getActionCommand();
		
		if(btnText.equals("Open..."))
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
		else
		{
			JOptionPane.showMessageDialog(this, btnText + " button was clicked!");
		}
	}
	
	public void mouseClicked(MouseEvent evt)
	{
		int x = evt.getX();
		int y = evt.getY();
		cood.setText("Mouse clicked at: " + x + "," + y);
	}
	public void mouseExited(MouseEvent evt){}
	public void mouseReleased(MouseEvent evt){}
	public void mousePressed(MouseEvent evt){}
	public void mouseEntered(MouseEvent evt)
	{
		int x = evt.getX();
		int y = evt.getY();
		cood.setText("Mouse enter from: " + x + "," + y);
	}
}
