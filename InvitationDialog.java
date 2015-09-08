import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class InvitationDialog extends JDialog implements ActionListener
{
	private JTable usersTable;
	private Vector<User> userList = new Vector<User>();
	
	public InvitationDialog(JFrame owner)
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
		Container invitationContent = getContentPane();
		invitationContent.setLayout(new BorderLayout());
		
		Object fromFile = FileOperation.readFromFile("users");
		if(fromFile != null)
			userList = (Vector<User>)fromFile;
		
		usersTable = new JTable(new UserTableModel(userList));
		JScrollPane tableScrollPane = new JScrollPane(usersTable);
		tableScrollPane.setSize(251, 300);
		usersTable.setFillsViewportHeight(true);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(this);
		
		JPanel userlistPanel = new JPanel();
		userlistPanel.setLayout(new BoxLayout(userlistPanel, BoxLayout.PAGE_AXIS));
		userlistPanel.add(tableScrollPane);
		userlistPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		JPanel authorizePanel = new JPanel();
		authorizePanel.setLayout(new FlowLayout());
		authorizePanel.add(saveBtn);
		
		invitationContent.add(userlistPanel, BorderLayout.CENTER);
		invitationContent.add(authorizePanel, BorderLayout.PAGE_END);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		try
		{
			String btnText = evt.getActionCommand();
			
			if(btnText.equals("Save"))
			{
				FileOperation.saveToFile(userList, "users");
				
				setVisible(false);
				dispose();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error - " + e.getMessage());
		}
	}
}
