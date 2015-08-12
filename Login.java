import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends Session
{
	boolean signed = false;
	public JButton signinBtn = new JButton("Sign in");
	
	public Login()
	{
		initPanel();
	}
	
	public Login(LayoutManager layout)
	{
		super(layout);
		initPanel();
	}
	
	protected void initPanel()
	{
		JPanel userPanel = new JPanel(new FlowLayout());
		userPanel.add(new JLabel("Username: "));
		userPanel.add(new JTextField(20));
		
		JPanel passPanel = new JPanel(new FlowLayout());
		passPanel.add(new JLabel("Password: "));
		passPanel.add(new JPasswordField(20));
		
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(new GridLayout(3, 1));
		//loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
		loginPanel.add(userPanel);
		loginPanel.add(passPanel);
		loginPanel.add(signinBtn);
		
		super.add(loginPanel);
		super.setBorder(BorderFactory.createLoweredBevelBorder());
	}
	
	public void setSourceListener(ActionListener al)
	{
		signinBtn.addActionListener(al);
	}
}
