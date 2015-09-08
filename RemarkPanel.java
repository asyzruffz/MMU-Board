import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RemarkPanel extends JPanel implements ActionListener
{
	private Comment comment = new Comment();
	
	public RemarkPanel() {}
	
	public RemarkPanel(Comment comm)
	{
		setLayout(new BorderLayout());
		comment = comm;
		
		initPanel();
	}
	
	private void initPanel()
	{
		JPanel contentPanel = new JPanel(new GridLayout(1, 0));
		contentPanel.add(new JLabel(comment.getText()));
		
		contentPanel.setBorder(BorderFactory.createTitledBorder(comment.getAuthor().getNickname()));
		
		JPanel buttonsPanel = new JPanel(new FlowLayout());
		JButton upvoteBtn = new JButton("^");
		upvoteBtn.addActionListener(this);
		JButton downvoteBtn = new JButton("v");
		downvoteBtn.addActionListener(this);
		JButton deleteBtn = new JButton("delete");
		deleteBtn.addActionListener(this);
		JButton editBtn = new JButton("edit");
		editBtn.addActionListener(this);
		JButton replyBtn = new JButton("reply");
		replyBtn.addActionListener(this);
		
		buttonsPanel.add(upvoteBtn);
		buttonsPanel.add(downvoteBtn);
		buttonsPanel.add(deleteBtn);
		buttonsPanel.add(editBtn);
		buttonsPanel.add(replyBtn);
		
		add(contentPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.PAGE_END);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		try
		{
			String btnText = evt.getActionCommand();
			
			if(btnText.equals("..."))
			{
				
			}
			else if(btnText.equals(",,,"))
			{
				
			}
			else if(btnText.equals("!!!"))
			{
				
			}
			else
			{
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Y U No Implement?");
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error - " + e.getMessage());
		}
	}
}
