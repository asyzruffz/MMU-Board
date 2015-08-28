import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NameDialog extends JDialog implements ActionListener
{
	String name = "";
	JTextField nameField = new JTextField(30);
	
	public NameDialog(String title)
	{
		super(new JFrame(), title);
		
		Container content = getContentPane();
		content.setLayout(new FlowLayout());
		content.add(new JLabel("Name: "));
		content.add(nameField);
		JButton confirm = new JButton("OK");
		content.add(confirm);
		confirm.addActionListener(this);
		
		setSize(400, 60);
		setLocation(500, 500);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(NameDialog.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		String btnText = evt.getActionCommand();
		
		if(btnText.equals("OK"))
		{
			name = nameField.getText();
			System.out.println(name);
			
			setVisible(false);
		}
	}
	
	public String getInput()
	{
		return name;
	}
}
