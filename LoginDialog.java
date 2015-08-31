import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginDialog extends JDialog implements ActionListener
{
	boolean signed = false;
	User user = new User();
	JTextField usernameField = new JTextField(20);
	JPasswordField passwordField = new JPasswordField(20);
	
	public LoginDialog(JFrame owner)
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
		
		JPanel passPanel = new JPanel(new FlowLayout());
		passPanel.add(new JLabel("Password: "));
		passPanel.add(passwordField);
		
		JButton signinBtn = new JButton("Sign in");
		signinBtn.addActionListener(this);
		
		JPanel newLoginPanel = new JPanel();
		newLoginPanel.setLayout(new GridLayout(3, 1));
		newLoginPanel.add(userPanel);
		newLoginPanel.add(passPanel);
		newLoginPanel.add(signinBtn);
		
		loginContent.add(newLoginPanel);
	}
	
	public User acquireUser()
	{
		return user;
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		String btnText = evt.getActionCommand();
		
		if(btnText.equals("Sign in"))
		{
			user.setUsername(usernameField.getText());
			user.setPassword(passwordField.getPassword());
			
			setVisible(false);
			dispose();
		}
	}
}
