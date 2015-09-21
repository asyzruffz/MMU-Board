import java.io.*;
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
		setSize(300, 440);
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
			
			JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
			JPanel rowPanel;
			
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Number of students registered                       "));
			rowPanel.add(new JLabel(": "+studNo));
			middlePanel.add(rowPanel);
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Number of lecturers registered                       "));
			rowPanel.add(new JLabel(": "+lectNo));
			middlePanel.add(rowPanel);
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Number of accounts unapproved                    "));
			rowPanel.add(new JLabel(": "+xApprNo));
			middlePanel.add(rowPanel);
			
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Total number of subjects available                 "));
			rowPanel.add(new JLabel(": "+subjNo));
			middlePanel.add(rowPanel);
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Total number of discussions created              "));
			rowPanel.add(new JLabel(": "+discNo));
			middlePanel.add(rowPanel);
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Total number of comments posted                 "));
			rowPanel.add(new JLabel(": "+commNo));
			middlePanel.add(rowPanel);
			
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Min number of discussions in a subject           "));
			rowPanel.add(new JLabel(": "+minDisc));
			middlePanel.add(rowPanel);
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Max number of discussions in a subject          "));
			rowPanel.add(new JLabel(": "+maxDisc));
			middlePanel.add(rowPanel);
			
			double avrDisc = ((subjNo > 0) ? (1000*discNo/subjNo) : 0);
			avrDisc /= 1000;  // rounded to 3 decimal places
			
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Average number of discussions in a subject   "));
			rowPanel.add(new JLabel(": " + avrDisc));
			middlePanel.add(rowPanel);
			
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Min number of comments in a discussion         "));
			rowPanel.add(new JLabel(": "+minComm));
			middlePanel.add(rowPanel);
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Max number of comments in a discussion       "));
			rowPanel.add(new JLabel(": "+maxComm));
			middlePanel.add(rowPanel);
			
			double avrComm = ((discNo > 0) ? (1000*commNo/discNo) : 0);
			avrComm /= 1000;  // rounded to 3 decimal places
			
			rowPanel = new JPanel();
			rowPanel.add(new JLabel("Average number of comments in a discussion"));
			rowPanel.add(new JLabel(": " + avrComm));
			middlePanel.add(rowPanel);
			
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
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			
			JOptionPane.showMessageDialog(this, errors.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println(errors.toString());
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
			
			subjNo = subjectList.size();
			if(subjNo > 0)
			{
				minDisc = subjectList.elementAt(0).getAllDiscussions().size();
				maxDisc = subjectList.elementAt(0).getAllDiscussions().size();
				for(Subject sub : subjectList)
				{
					discNo += sub.getAllDiscussions().size();
					
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
							commNo += dis.getAllComments().size();
							
							if(dis.getAllComments().size() < minComm)
								minComm = dis.getAllComments().size();
							if(dis.getAllComments().size() > maxComm)
								maxComm = dis.getAllComments().size();
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			
			JOptionPane.showMessageDialog(this, errors.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println(errors.toString());
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
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			
			JOptionPane.showMessageDialog(this, errors.toString(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println(errors.toString());
		}
	}
}
