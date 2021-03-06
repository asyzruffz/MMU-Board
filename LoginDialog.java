import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class LoginDialog extends JDialog implements ActionListener
{
	private User incomingUser = new User();
	private JTextField usernameField = new JTextField(20);
	private JPasswordField passwordField = new JPasswordField(20);
	private Vector<User> userList = new Vector<User>();
	private char[] adminPass = {'1','2','3','4','5'};
	private User admin = new User("admin", adminPass, "Admin", User.AccessLevel.ADMIN, true);
	
	public LoginDialog(JFrame owner)
	{
		super(owner, true);
		setTitle(" Online MMU-Board System");
		setSize(300, 170);
		setResizable(false);
		setLocationRelativeTo(this);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent evt)
			{
				System.exit(0);
			}
		});
		
		initPanel();
		setVisible(true);
	}
	
	private void initPanel()
	{
		Container loginContent = getContentPane();
		loginContent.setLayout(new FlowLayout());
		
		JPanel userPanel = new JPanel(new FlowLayout());
		userPanel.add(new JLabel("Username: "));
		userPanel.add(usernameField);
		
		JPanel passPanel = new JPanel(new FlowLayout());
		passwordField.setActionCommand("Sign in");
		passwordField.addActionListener(this);
		passPanel.add(new JLabel("Password: "));
		passPanel.add(passwordField);
		
		JButton signinBtn = new JButton("Sign in");
		signinBtn.addActionListener(this);
		JButton guestBtn = new JButton("Be a Guest");
		guestBtn.addActionListener(this);
		
		JPanel newLoginPanel = new JPanel();
		newLoginPanel.setLayout(new GridLayout(4, 1));
		newLoginPanel.add(userPanel);
		newLoginPanel.add(passPanel);
		newLoginPanel.add(signinBtn);
		newLoginPanel.add(guestBtn);
		
		loginContent.add(newLoginPanel);
		
		Object fromFile = FileOperation.readFromFile("users");
		if(fromFile != null)
			userList = (Vector<User>)fromFile;
	}
	
	public User acquireUser()
	{
		return incomingUser;
	}
	
	public Vector<User> getUserList()
	{
		return userList;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		String btnText = evt.getActionCommand();
		
		if(btnText.equals("Sign in"))
		{
			if(userList.size() < 1)
			{
				userList.add(admin);
				FileOperation.saveToFile(userList, "users");
			}
			
			for(User us : userList)
			{
				if(us.getUsername().equals(usernameField.getText()))
				{
					if(Arrays.equals(us.getPassword(), passwordField.getPassword()))
					{
						if(!us.isPending())
						{
							incomingUser = us;
							
							setVisible(false);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(this, "This account has not been authorized!");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Wrong password!");
					}
					
					return;
				}
			}
			
			JOptionPane.showMessageDialog(this, "No such user registered!");
		}
		else if(btnText.equals("Be a Guest"))
		{
			setVisible(false);
			dispose();
		}
	}
}
