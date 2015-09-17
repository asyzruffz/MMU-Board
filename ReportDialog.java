import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class ReportDialog extends JDialog implements ActionListener
{
	private Vector<User> userList = new Vector<User>();
	private Vector<Subject> subjectList = new Vector<Subject>();
	
	private int studNo = 0;
	private int lectNo = 0;
	private int xApprNo = 0;
	private int subjNo = 0;
	private int discNo = 0;
	private int commNo = 0;
	private int minDisc = 0;
	private int maxDisc = 0;
	private int minComm = 0;
	private int maxComm = 0;
	
	public ReportDialog(JFrame owner)
	{
		super(owner, true);
		setTitle(" Statistic Report");
		setSize(300, 400);
		setResizable(false);
		setLocationRelativeTo(this);
		
		initPanel();
		setVisible(true);
	}
	
	private void initPanel()
	{
		try
		{
			Container reportContent = getContentPane();
			reportContent.setLayout(new BorderLayout());
			
			calculateStatistic();
			
			JPanel middlePanel = new JPanel();
			
			middlePanel.add(new JLabel("Number of students registered"));
			middlePanel.add(new JLabel(": "+studNo));
			middlePanel.add(new JLabel("Number of lecturers registered"));
			middlePanel.add(new JLabel(": "+lectNo));
			middlePanel.add(new JLabel("Number of accounts unapproved"));
			middlePanel.add(new JLabel(": "+xApprNo));
			
			middlePanel.add(new JLabel("Total number of subjects available"));
			middlePanel.add(new JLabel(": "+subjNo));
			middlePanel.add(new JLabel("Total number of discussions created"));
			middlePanel.add(new JLabel(": "+discNo));
			middlePanel.add(new JLabel("Total number of comments posted"));
			middlePanel.add(new JLabel(": "+commNo));
			
			middlePanel.add(new JLabel("Min number of discussions in a subject"));
			middlePanel.add(new JLabel(": "+minDisc));
			middlePanel.add(new JLabel("Max number of discussions in a subject"));
			middlePanel.add(new JLabel(": "+maxDisc));
			middlePanel.add(new JLabel("Average number of discussions in a subject"));
			middlePanel.add(new JLabel(": " + ((subjNo > 0) ? ((double)discNo/subjNo) : 0)));
			
			middlePanel.add(new JLabel("Min number of comments in a discussion"));
			middlePanel.add(new JLabel(": "+minComm));
			middlePanel.add(new JLabel("Max number of comments in a discussion"));
			middlePanel.add(new JLabel(": "+maxComm));
			middlePanel.add(new JLabel("Average number of comments in a discussion"));
			middlePanel.add(new JLabel(": " + ((discNo > 0) ? ((double)commNo/discNo) : 0)));
			
			JButton okBtn = new JButton("OK");
			okBtn.addActionListener(this);
			
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new FlowLayout());
			bottomPanel.add(okBtn);
			
			reportContent.add(middlePanel, BorderLayout.CENTER);
			reportContent.add(bottomPanel, BorderLayout.PAGE_END);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error (ReportDialog.java) " + e.getMessage());
		}
	}
	
	private void calculateStatistic()
	{
		try
		{
			Object fromFile;
			
			fromFile = FileOperation.readFromFile("users");
			if(fromFile != null)
				userList = (Vector<User>)fromFile;
			fromFile = FileOperation.readFromFile("content");
			if(fromFile != null)
				subjectList = (Vector<Subject>)fromFile;
			
			if(userList.size() > 0)
			{
				for(User us : userList)
				{
					if(us.isPending())
						xApprNo++;
					
					if(us.getAccessLevel().equals(User.AccessLevel.STUDENT))
						studNo++;
					else if(us.getAccessLevel().equals(User.AccessLevel.LECTURER))
						lectNo++;
				}
			}
			
			if(subjectList.size() > 0)
			{
				minDisc = subjectList.elementAt(0).getAllDiscussions().size();
				maxDisc = subjectList.elementAt(0).getAllDiscussions().size();
				for(Subject sub : subjectList)
				{
					subjNo++;
					if(sub.getAllDiscussions().size() < minDisc)
						minDisc = sub.getAllDiscussions().size();
					if(sub.getAllDiscussions().size() > maxDisc)
						maxDisc = sub.getAllDiscussions().size();
					
					if(sub.getAllDiscussions().size() > 0)
					{
						minComm = sub.getAllDiscussions().elementAt(0).getAllComments().size();
						maxComm = sub.getAllDiscussions().elementAt(0).getAllComments().size();
						for(Discussion dis : sub.getAllDiscussions())
						{
							discNo++;
							if(dis.getAllComments().size() < minComm)
								minComm = dis.getAllComments().size();
							if(dis.getAllComments().size() > maxComm)
								maxComm = dis.getAllComments().size();
							for(Comment com : dis.getAllComments())
							{
								commNo++;
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error (ReportDialog.java) " + e.getMessage());
		}
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		try
		{
			String btnText = evt.getActionCommand();
			
			if(btnText.equals("OK"))
			{
				setVisible(false);
				dispose();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error (ReportDialog.java) " + e.getMessage());
		}
	}
}
