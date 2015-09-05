import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class InvitationDialog extends JDialog implements ActionListener, ListSelectionListener
{
	private JList<User> pendingUsers = new JList<User>();
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
		
		updatePendingUsers(pendingUsers, userList);
		pendingUsers.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		pendingUsers.setLayoutOrientation(JList.VERTICAL);
		pendingUsers.setVisibleRowCount(-1);
		pendingUsers.addListSelectionListener(this);
		JScrollPane userScrollPane = new JScrollPane(pendingUsers);
		userScrollPane.setSize(250, 300);
		
		JButton okBtn = new JButton("Authorize");
		okBtn.addActionListener(this);
		
		JPanel userlistPanel = new JPanel();
		userlistPanel.setLayout(new BoxLayout(userlistPanel, BoxLayout.PAGE_AXIS));
		userlistPanel.add(userScrollPane);
		userlistPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		JPanel authorizePanel = new JPanel();
		authorizePanel.setLayout(new FlowLayout());
		authorizePanel.add(okBtn);
		
		invitationContent.add(userlistPanel, BorderLayout.CENTER);
		invitationContent.add(authorizePanel, BorderLayout.PAGE_END);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		try
		{
			String btnText = evt.getActionCommand();
			
			if(btnText.equals("Authorize"))
			{
				if(!pendingUsers.isSelectionEmpty())
				{
					ArrayList<User> approvedUser = (ArrayList<User>)pendingUsers.getSelectedValuesList();
					
					for(User us : userList)
					{
						for(User approved : approvedUser)
						{
							if(us.equals(approved))
							{
								us.setApproved();
							}
						}
					}
					
					updatePendingUsers(pendingUsers, userList);
					FileOperation.saveToFile(userList, "users");
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error - " + e.getMessage());
		}
	}
	
	public void valueChanged(ListSelectionEvent evt)
	{
		try
		{
			JList<?> list = (JList<?>)evt.getSource();
			
			// At least one user is selected
			if(!list.isSelectionEmpty())
			{
				
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error - " + e.getMessage());
		}
	}
	
	public void updatePendingUsers(JList<User> list, Vector<User> userVector)
	{
		DefaultListModel<User> listModel = new DefaultListModel<User>();
		
		for(User us : userVector)
		{
			if(us.isPending())
			{
				listModel.addElement(us);
			}
		}
		
		list.setModel(listModel);
	}
}
