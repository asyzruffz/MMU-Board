import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class Operating extends Session implements ActionListener, ListSelectionListener, FocusListener
{
	private Vector<Subject> subjectList = new Vector<Subject>();
	private Subject selectedSubject = null;
	private Discussion selectedDiscussion = null;
	private JList allSubjects;
	private JList allDiscussions = new JList();
	private JTextArea messageArea = new JTextArea("Enter your post here...", 5, 0);
	private JTextArea note = new JTextArea(50, 0);
	
	public Operating()
	{
		initPanel();
	}
	
	public Operating(LayoutManager layout)
	{
		super(layout);
		initPanel();
	}
	
	protected void initPanel()
	{
		subjectList.add(new Subject("Calculus"));
		subjectList.add(new Subject("Discreet Structure"));
		subjectList.add(new Subject("Programming Fundamentals"));
		subjectList.add(new Subject("Prof Development"));
		subjectList.add(new Subject("Computational Methods"));
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		
		allSubjects = new JList(subjectList);
		allSubjects.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		allSubjects.setLayoutOrientation(JList.VERTICAL);
		allSubjects.setVisibleRowCount(-1);
		allSubjects.addListSelectionListener(this);
		JScrollPane subjScrollPane = new JScrollPane(allSubjects);
		JButton newSubjBtn = new JButton("Add New Subject");
		newSubjBtn.addActionListener(this);
		
		allDiscussions.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		allDiscussions.setLayoutOrientation(JList.VERTICAL);
		allDiscussions.setVisibleRowCount(-1);
		allDiscussions.addListSelectionListener(this);
		JScrollPane discScrollPane = new JScrollPane(allDiscussions);
		JButton newDiscBtn = new JButton("Add New Discussion");
		newDiscBtn.addActionListener(this);
		
		leftPanel.add(subjScrollPane);
		leftPanel.add(newSubjBtn);
		leftPanel.add(discScrollPane);
		leftPanel.add(newDiscBtn);
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.PAGE_AXIS));
		note.setEditable(false);
		middlePanel.add(new JScrollPane(note));
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.LINE_AXIS));
		messageArea.addFocusListener(this);
		messagePanel.add(new JScrollPane(messageArea));
		JButton postBtn = new JButton("Post");
		postBtn.addActionListener(this);
		messagePanel.add(postBtn);
		middlePanel.add(messagePanel);
		
		
		//Create a split pane dividing main post from Subject/Discussion selector.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
								   leftPanel, middlePanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(240);
		splitPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
															   BorderFactory.createLoweredBevelBorder()));
		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(240, 300);
		leftPanel.setMinimumSize(minimumSize);
		middlePanel.setMinimumSize(minimumSize);
		
		super.add(splitPane);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		String btnText = evt.getActionCommand();
		
		if(btnText.equals("Add New Subject"))
		{
			String subjectName = (String)JOptionPane.showInputDialog(this.getTopLevelAncestor(), "Subject Name: ", "Add New Subject", JOptionPane.PLAIN_MESSAGE);
			
			if((subjectName != null) && (subjectName.length() > 0))
			{
				subjectList.add(new Subject(subjectName));
				updateList(allSubjects, subjectList);
			}
		}
		else if(btnText.equals("Add New Discussion"))
		{
			if(selectedSubject != null)
			{
				String discussionTitle = (String)JOptionPane.showInputDialog(this.getTopLevelAncestor(), "Discussion Title: ", "Add New Discussion", JOptionPane.PLAIN_MESSAGE);
				
				if((discussionTitle != null) && (discussionTitle.length() > 0))
				{
					selectedSubject.addDiscussion(new Discussion(discussionTitle));
					updateList(allDiscussions, selectedSubject.getAllDiscussions());
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select a Subject!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(btnText.equals("Post"))
		{
			if(selectedDiscussion != null)
			{
				String commentText = messageArea.getText() + "\n";
				
				if((commentText != null) && (commentText.length() > 0) && (!commentText.equals("Enter your post here...\n")))
				{
					selectedDiscussion.addComment(new Comment(commentText));
					note.append(commentText);
					messageArea.setText("Enter your post here...");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Please select a Discussion of a Subject!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void valueChanged(ListSelectionEvent evt)
	{
		JList list = (JList)evt.getSource();
		
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
				
			updateMessageBoard(selectedDiscussion);
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
	
	public void updateList(JList list, Vector newVector)
	{
		// Recreate a new list model & add the updated object list
		DefaultListModel listModel = new DefaultListModel();
		for(Object obj : newVector)
		{
			listModel.addElement(obj);
		}
		
		// Set the list with the new list model
		list.setModel(listModel);
	}
	
	public void updateMessageBoard(Discussion disc)
	{
		note.setText("");
		if(selectedDiscussion != null)
		{
			for(Comment msg : disc.getAllComment())
			{
				note.append(msg.getText());
			}
		}
	}
}
