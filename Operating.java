import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Operating extends Session
{
	private Vector<Subject> subjectList = new Vector<Subject>();
	
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
		
		JList allSubjects = new JList(subjectList);
		allSubjects.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		allSubjects.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		allSubjects.setVisibleRowCount(-1);
		
		JScrollPane subjScrollPane = new JScrollPane(allSubjects);
		
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.PAGE_AXIS));
		JTextArea note = new JTextArea(50, 0);
		JScrollPane textScrollPane = new JScrollPane(note);
		middlePanel.add(textScrollPane);
		JPanel messagePanel = new JPanel();
		JTextField messageBar = new JTextField("Enter your post here...");
		messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.LINE_AXIS));
		messagePanel.add(messageBar);
		messagePanel.add(new JButton("Post"));
		middlePanel.add(messagePanel);
		
		
		//Create a split pane with the two scroll panes in it.
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
								   subjScrollPane, middlePanel);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(200);
		splitPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
															   BorderFactory.createLoweredBevelBorder()));
		//Provide minimum sizes for the two components in the split pane
		Dimension minimumSize = new Dimension(200, 50);
		subjScrollPane.setMinimumSize(minimumSize);
		textScrollPane.setMinimumSize(minimumSize);
		
		super.add(splitPane);
	}
}
