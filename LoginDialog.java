import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class LoginDialog extends JDialog implements ActionListener
{
	User incomingUser = new User();
	JTextField usernameField = new JTextField(20);
	JPasswordField passwordField = new JPasswordField(20);
	private Vector<User> userList = new Vector<User>();
	
	public LoginDialog(JFrame owner)
	{
		super(owner, true);
		setSize(300, 400);
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
