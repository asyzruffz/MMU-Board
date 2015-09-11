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
		contentPanel.setBorder(BorderFactory.createTitledBorder(comment.getAuthor().getNickname()+" ("+comment.getKarma()+")"));
		
		JTextArea note = new JTextArea(comment.getText());
		note.setFont(UIManager.getFont("Label.font"));
		note.setOpaque(false);
		note.setEditable(false);
		note.setLineWrap(true);
		note.setWrapStyleWord(true);
		contentPanel.add(note);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
		JButton upvoteBtn = new JButton("^");
		upvoteBtn.addActionListener(this);
		upvoteBtn.setEnabled(MainFrame.currentUser.requireAccessLevel(User.AccessLevel.STUDENT));
		JButton downvoteBtn = new JButton("v");
		downvoteBtn.addActionListener(this);
		downvoteBtn.setEnabled(MainFrame.currentUser.requireAccessLevel(User.AccessLevel.STUDENT));
		JButton deleteBtn = new JButton("delete");
		deleteBtn.addActionListener(this);
		deleteBtn.setEnabled(MainFrame.currentUser.requireAccessLevel(User.AccessLevel.LECTURER));
		JButton editBtn = new JButton("edit");
		editBtn.addActionListener(this);
		editBtn.setEnabled(MainFrame.currentUser.requireAccessLevel(User.AccessLevel.LECTURER));
		//JButton replyBtn = new JButton("reply");
		//replyBtn.addActionListener(this);
		//replyBtn.setEnabled(MainFrame.currentUser.requireAccessLevel(User.AccessLevel.LECTURER));
		
		buttonsPanel.add(upvoteBtn);
		buttonsPanel.add(downvoteBtn);
		buttonsPanel.add(Box.createHorizontalGlue());
		buttonsPanel.add(deleteBtn);
		buttonsPanel.add(editBtn);
		//buttonsPanel.add(replyBtn);
		
		add(contentPanel, BorderLayout.CENTER);
		//add(new JLabel(""+comment.getKarma()), BorderLayout.LINE_END);
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
