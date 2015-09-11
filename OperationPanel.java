import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class OperationPanel extends JPanel implements ActionListener, ListSelectionListener, FocusListener
{
	private Vector<Subject> subjectList = new Vector<Subject>();
	private Subject selectedSubject = null;
	private Discussion selectedDiscussion = null;
	private JList<Subject> allSubjects;
	private JList<Discussion> allDiscussions = new JList<Discussion>();
	private JTextArea messageArea = new JTextArea("Enter your post here...", 5, 0);
	private JPanel view = new JPanel(new CardLayout());
	
	public OperationPanel()
	{
		setLayout(new GridLayout(1, 1));
		initPanel();
	}
	
	private void initPanel()
	{
		Object fromFile = FileOperation.readFromFile("content");
		if(fromFile != null)
			subjectList = (Vector<Subject>)fromFile;
		
		//subjectList.add(new Subject("Calculus"));
		//subjectList.add(new Subject("Discreet Structure"));
		//subjectList.add(new Subject("Programming Fundamentals"));
		//subjectList.add(new Subject("Prof Development"));
		//subjectList.add(new Subject("Computational Methods"));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		leftPanel.setBorder(BorderFactory.createEmptyBorder(1,1,0,0));
		
		allSubjects = new JList<Subject>(subjectList);
		allSubjects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		allSubjects.setLayoutOrientation(JList.VERTICAL);
		allSubjects.setVisibleRowCount(-1);
		allSubjects.addListSelectionListener(this);
		JScrollPane subjScrollPane = new JScrollPane(allSubjects);
		JButton newSubjBtn = new JButton("+");
		newSubjBtn.setActionCommand("Add New Subject");
		newSubjBtn.addActionListener(this);
		newSubjBtn.setEnabled(MainFrame.currentUser.requireAccessLevel(User.AccessLevel.LECTURER));
		JButton delSubjBtn = new JButton("-");
		delSubjBtn.setActionCommand("Delete Subject");
		delSubjBtn.addActionListener(this);
		delSubjBtn.setEnabled(MainFrame.currentUser.requireAccessLevel(User.AccessLevel.LECTURER));
		JPanel editSubjPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 2, 2));
		editSubjPanel.add(delSubjBtn);
		editSubjPanel.add(newSubjBtn);
		
		allDiscussions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		allDiscussions.setLayoutOrientation(JList.VERTICAL);
		allDiscussions.setVisibleRowCount(-1);
		allDiscussions.addListSelectionListener(this);
		JScrollPane discScrollPane = new JScrollPane(allDiscussions);
		JButton newDiscBtn = new JButton("+");
		newDiscBtn.setActionCommand("Add New Discussion");
		newDiscBtn.addActionListener(this);
		newDiscBtn.setEnabled(MainFrame.currentUser.requireAccessLevel(User.AccessLevel.LECTURER));
		JButton delDiscBtn = new JButton("-");
		delDiscBtn.setActionCommand("Delete Discussion");
		delDiscBtn.addActionListener(this);
		delDiscBtn.setEnabled(MainFrame.currentUser.requireAccessLevel(User.AccessLevel.LECTURER));
		JPanel editDiscPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 2, 2));
		editDiscPanel.add(delDiscBtn);
		editDiscPanel.add(newDiscBtn);
		
		JPanel upperLeftPanel = new JPanel(new BorderLayout());
		JPanel lowerLeftPanel = new JPanel(new BorderLayout());
		upperLeftPanel.add(subjScrollPane, BorderLayout.CENTER);
		upperLeftPanel.add(editSubjPanel, BorderLayout.PAGE_END);
		lowerLeftPanel.add(discScrollPane, BorderLayout.CENTER);
		lowerLeftPanel.add(editDiscPanel, BorderLayout.PAGE_END);
		
		leftPanel.add(upperLeftPanel);
		leftPanel.add(lowerLeftPanel);
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());
		
		view.add(new ViewPanel(selectedDiscussion), "A");
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.LINE_AXIS));
		messageArea.addFocusListener(this);
		messagePanel.add(new JScrollPane(messageArea));
		JButton postBtn = new JButton("Post");
		postBtn.setEnabled(MainFrame.currentUser.requireAccessLevel(User.AccessLevel.STUDENT));
		postBtn.addActionListener(this);
		messagePanel.add(postBtn);
		
		middlePanel.add(new JScrollPane(view), BorderLayout.CENTER);
		middlePanel.add(messagePanel, BorderLayout.PAGE_END);
		
		
		//Create a split pane dividing main post from Subject/Discussion selector.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
								   leftPanel, middlePanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(240);
		splitPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
															   BorderFactory.createLoweredBevelBorder()));
		//Provide minimum sizes for the two components in the split pane
		leftPanel.setMinimumSize(new Dimension(240, 300));
		middlePanel.setMinimumSize(new Dimension(800, 300));
		
		this.add(splitPane);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		try
		{
			String btnText = evt.getActionCommand();
			
			if(btnText.equals("Add New Subject"))
			{
				String subjectName = JOptionPane.showInputDialog(this.getTopLevelAncestor(), "Subject Name: ", "Add New Subject", JOptionPane.PLAIN_MESSAGE);
				
				if((subjectName != null) && (subjectName.length() > 0))
				{
					subjectList.add(new Subject(subjectName));
					selectedSubject = updateList(allSubjects, subjectList);
				}
			}
			else if(btnText.equals("Delete Subject"))
			{
				if(selectedSubject != null)
				{
					subjectList.remove(allSubjects.getSelectedIndex());
					selectedSubject = updateList(allSubjects, subjectList);
				}
				else
				{
					JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select a Subject!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(btnText.equals("Add New Discussion"))
			{
				if(selectedSubject != null)
				{
					String discussionTitle = JOptionPane.showInputDialog(this.getTopLevelAncestor(), "Discussion Title: ", "Add New Discussion", JOptionPane.PLAIN_MESSAGE);
					
					if((discussionTitle != null) && (discussionTitle.length() > 0))
					{
						selectedSubject.addDiscussion(new Discussion(discussionTitle));
						selectedDiscussion = updateList(allDiscussions, selectedSubject.getAllDiscussions());
						updateMessageBoard();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select a Subject!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(btnText.equals("Delete Discussion"))
			{
				if(selectedDiscussion != null)
				{
					selectedSubject.removeDiscussion(allDiscussions.getSelectedIndex());
					selectedDiscussion = updateList(allDiscussions, selectedSubject.getAllDiscussions());
					updateMessageBoard();
				}
				else
				{
					JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select a Discussion of a Subject!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(btnText.equals("Post"))
			{
				if(selectedDiscussion != null)
				{
					String commentText = messageArea.getText();
					
					if((commentText != null) && (commentText.length() > 0) && (!commentText.equals("Enter your post here...")))
					{
						selectedDiscussion.addComment(new Comment(commentText, MainFrame.currentUser));
						updateMessageBoard();
						messageArea.setText("Enter your post here...");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select a Discussion of a Subject!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			FileOperation.saveToFile(subjectList, "content");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error - " + e.getMessage());
		}
	}
	
	public void valueChanged(ListSelectionEvent evt)
	{
		try
		{
			JList<?> list = (JList<?>)evt.getSource();
			
			// At least one object is selected
			if(!list.isSelectionEmpty())
			{
				// the selection is in discussion list
				if(list.getSelectedValue() instanceof Discussion)
				{
					selectedDiscussion = (Discussion)list.getSelectedValue();
					//System.out.println(selectedDiscussion.getTitle() + " is selected!");
				}
				else
				{
					//System.out.println("Discussion is null!");
					selectedDiscussion = null;
					
					// the selection is in subject list
					if(list.getSelectedValue() instanceof Subject)
					{
						selectedSubject = (Subject)list.getSelectedValue();
						//System.out.println(selectedSubject.getSubjName() + " is selected!");
						updateList(allDiscussions, selectedSubject.getAllDiscussions());
					}
					else
					{
						//System.out.println("Subject is null!");
						selectedSubject = null;
					}
				}
					
				updateMessageBoard();
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error - " + e.getMessage());
		}
	}
	
	public void focusGained(FocusEvent evt)
	{
		String msg = messageArea.getText();
		
		if(msg.equals("Enter your post here..."))
		{
			messageArea.setText("");
		}
	}
	
	public void focusLost(FocusEvent evt)
	{
		String msg = messageArea.getText();
		
		if(msg.equals(""))
		{
			messageArea.setText("Enter your post here...");
		}
	}
	
	public <T> T updateList(JList<T> list, Vector<T> newVector)
	{
		// Recreate a new list model & add the updated object list
		DefaultListModel<T> listModel = new DefaultListModel<T>();
		for(T obj : newVector)
		{
			listModel.addElement(obj);
		}
		
		// Set the list with the new list model
		list.setModel(listModel);
		
		if(newVector.isEmpty())
			return null;
		
		// Visually select the added object in the list
		list.setSelectedIndex(newVector.size() - 1);
		list.ensureIndexIsVisible(newVector.size() - 1);
		return newVector.lastElement();
	}
	
	public void updateMessageBoard()
	{
		CardLayout cardLayout = (CardLayout)view.getLayout();
		view.add(new ViewPanel(selectedDiscussion), "A");
		cardLayout.show(view, "A");
		view.remove(0);
		FileOperation.saveToFile(subjectList, "content");
	}
}
