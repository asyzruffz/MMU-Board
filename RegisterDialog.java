import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class RegisterDialog extends JDialog implements ActionListener
{
	User incomingUser = new User();
	JTextField usernameField = new JTextField(20);
	JTextField nicknameField = new JTextField(20);
	JPasswordField passwordField = new JPasswordField(20);
	private Vector<User> userList = new Vector<User>();
	
	public RegisterDialog(JFrame owner)
	{
		super(owner, true);
		setSize(300, 400);
		setResizable(false);
		setLocationRelativeTo(this);
		
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
		
		JPanel nickPanel = new JPanel(new FlowLayout());
		nickPanel.add(new JLabel("Nickname: "));
		nickPanel.add(nicknameField);
		
		JPanel passPanel = new JPanel(new FlowLayout());
		passPanel.add(new JLabel("Password: "));
		passPanel.add(passwordField);
		
		JPanel typePanel = new JPanel(new FlowLayout());
		JRadioButton studSelectBtn = new JRadioButton("Student");
		studSelectBtn.setActionCommand("Student");
		studSelectBtn.setSelected(true);
		incomingUser.setAccessLevel(User.AccessLevel.STUDENT);
		studSelectBtn.addActionListener(this);
		JRadioButton lectSelectBtn = new JRadioButton("Lecturer");
		lectSelectBtn.setActionCommand("Lecturer");
		lectSelectBtn.addActionListener(this);
		ButtonGroup userTypeGroup = new ButtonGroup();
		userTypeGroup.add(studSelectBtn);
		userTypeGroup.add(lectSelectBtn);
		typePanel.add(studSelectBtn);
		typePanel.add(lectSelectBtn);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(this);
		
		JPanel newLoginPanel = new JPanel();
		newLoginPanel.setLayout(new GridLayout(5, 1));
		newLoginPanel.add(userPanel);
		newLoginPanel.add(nickPanel);
		newLoginPanel.add(passPanel);
		newLoginPanel.add(typePanel);
		newLoginPanel.add(registerBtn);
		
		loginContent.add(newLoginPanel);
		
		Object fromFile = FileOperation.readFromFile("users");
		if(fromFile != null)
			userList = (Vector<User>)fromFile;
	}
	
	public User acquireUser()
	{
		return incomingUser;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		String btnText = evt.getActionCommand();
		
		if(btnText.equals("Register"))
		{
			if(!usernameField.getText().equals("") && passwordField.getPassword().length > 0)
			{
				incomingUser.setUsername(usernameField.getText());
				incomingUser.setNickname(nicknameField.getText());
				incomingUser.setPassword(passwordField.getPassword());
				
				userList.add(incomingUser);
				FileOperation.saveToFile(userList, "users");
				
				System.out.println(incomingUser.getUsername() + " is registered!");
				
				setVisible(false);
				dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Please fill in the required field!");
			}
			
		}
		else if(btnText.equals("Student"))
		{
			incomingUser.setAccessLevel(User.AccessLevel.STUDENT);
		}
		else if(btnText.equals("Lecturer"))
		{
			incomingUser.setAccessLevel(User.AccessLevel.LECTURER);
		}
	}
}
