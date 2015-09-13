import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class RegisterDialog extends JDialog implements ActionListener
{
	private User incomingUser = new User();
	private JTextField usernameField = new JTextField(20);
	private JTextField nicknameField = new JTextField(20);
	private JPasswordField passwordField1 = new JPasswordField(20);
	private JPasswordField passwordField2 = new JPasswordField(20);
	private Vector<User> userList = new Vector<User>();
	private boolean edit = false;
	
	public RegisterDialog(JFrame owner, boolean edit)
	{
		super(owner, true);
		this.edit = edit;
		setTitle(" Create an Account");
		setSize(350, 250);
		setResizable(false);
		setLocationRelativeTo(this);
		
		initPanel();
		setVisible(true);
	}
	
	private void initPanel()
	{
		Object fromFile = FileOperation.readFromFile("users");
		if(fromFile != null)
			userList = (Vector<User>)fromFile;
		
		if(edit)
		{
			for(User us : userList)
			{
				if(us.getUsername().equals(MainFrame.currentUser.getUsername()))
				{
					incomingUser = us;
					break;
				}
			}
		}
		
		Container loginContent = getContentPane();
		loginContent.setLayout(new FlowLayout());
		
		JPanel userPanel = new JPanel(new FlowLayout());
		if(edit)
		{
			usernameField.setText(incomingUser.getUsername());
			usernameField.setEnabled(!edit);
		}
		userPanel.add(new JLabel("Username              : "));
		userPanel.add(usernameField);
		
		JPanel nickPanel = new JPanel(new FlowLayout());
		if(edit)
			nicknameField.setText(incomingUser.getNickname());
		nickPanel.add(new JLabel("Nickname               : "));
		nickPanel.add(nicknameField);
		
		JPanel passPanel1 = new JPanel(new FlowLayout());
		if(edit)
			passPanel1.add(new JLabel("Old Password        : "));
		else
			passPanel1.add(new JLabel("Password              : "));
		passPanel1.add(passwordField1);
		
		JPanel passPanel2 = new JPanel(new FlowLayout());
		if(edit)
			passPanel2.add(new JLabel("New Password      : "));
		else
			passPanel2.add(new JLabel("Confirm Password: "));
		passPanel2.add(passwordField2);
		
		JPanel typePanel = new JPanel(new FlowLayout());
		JRadioButton studSelectBtn = new JRadioButton("Student");
		studSelectBtn.setActionCommand("Student");
		if(!edit)
		{
			studSelectBtn.setSelected(true);
			incomingUser.setAccessLevel(User.AccessLevel.STUDENT);
		}
		studSelectBtn.addActionListener(this);
		JRadioButton lectSelectBtn = new JRadioButton("Lecturer");
		lectSelectBtn.setActionCommand("Lecturer");
		lectSelectBtn.addActionListener(this);
		
		if(edit)
		{
			if(incomingUser.getAccessLevel() == User.AccessLevel.STUDENT)
				studSelectBtn.setSelected(true);
			else if(incomingUser.getAccessLevel() == User.AccessLevel.LECTURER)
				lectSelectBtn.setSelected(true);
			studSelectBtn.setEnabled(false);
			lectSelectBtn.setEnabled(false);
		}
		
		ButtonGroup userTypeGroup = new ButtonGroup();
		userTypeGroup.add(studSelectBtn);
		userTypeGroup.add(lectSelectBtn);
		typePanel.add(studSelectBtn);
		typePanel.add(lectSelectBtn);
		
		JButton registerBtn;
		if(edit)
			registerBtn = new JButton("Save");
		else
			registerBtn = new JButton("Register");
		registerBtn.addActionListener(this);
		
		JPanel newLoginPanel = new JPanel();
		newLoginPanel.setLayout(new GridLayout(6, 1));
		newLoginPanel.add(userPanel);
		newLoginPanel.add(nickPanel);
		newLoginPanel.add(passPanel1);
		newLoginPanel.add(passPanel2);
		newLoginPanel.add(typePanel);
		newLoginPanel.add(registerBtn);
		
		loginContent.add(newLoginPanel);
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
			boolean existed = false;
			for(User us : userList)
			{
				if(us.getUsername().equals(usernameField.getText()))
				{
					existed = true;
					break;
				}
			}
			
			if(!existed)
			{
				if(!usernameField.getText().equals("") && passwordField1.getPassword().length > 0)
				{
					if(Arrays.equals(passwordField1.getPassword(), passwordField2.getPassword()))
					{
						incomingUser.setUsername(usernameField.getText());
						incomingUser.setNickname(nicknameField.getText());
						incomingUser.setPassword(passwordField1.getPassword());
						
						userList.add(incomingUser);
						FileOperation.saveToFile(userList, "users");
						
						System.out.println(incomingUser.getNickname() + " is registered!");
						JOptionPane.showMessageDialog(this, "Your account is created. Please wait for a lecturer or an admin to approve.");
						
						setVisible(false);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Both the passwords are different!");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Please fill in the required field!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "This username already existed!");
			}
		}
		else if(btnText.equals("Save"))
		{
			if(passwordField1.getPassword().length > 0)
			{
				if(Arrays.equals(passwordField1.getPassword(), incomingUser.getPassword()))
				{
					incomingUser.setNickname(nicknameField.getText());
					incomingUser.setPassword(passwordField2.getPassword());
					
					FileOperation.saveToFile(userList, "users");
					
					System.out.println(incomingUser.getNickname() + "'s info is updated!");
					JOptionPane.showMessageDialog(this, "You can see the change in your account the next time you sign in.");
					
					setVisible(false);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Wrong password!");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Please at least fill in the old password for any change!");
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
