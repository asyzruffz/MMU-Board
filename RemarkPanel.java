import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.BorderFactory; 
import javax.swing.border.*;

public class RemarkPanel extends JPanel implements ActionListener, FocusListener
{
	private Comment comment = new Comment();
	private JTextArea note;
	private JButton editBtn;
	private ViewPanel parent;
	
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
		
		TitledBorder titleBorder = BorderFactory.createTitledBorder(comment.getAuthor().getNickname()+" ("+comment.getKarma()+")");
		TitledBorder dateBorder = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), comment.getTimeEdited(),
																	TitledBorder.TRAILING, TitledBorder.ABOVE_BOTTOM);
		contentPanel.setBorder(BorderFactory.createCompoundBorder(titleBorder, dateBorder));
		
		note = new JTextArea(comment.getText());
		note.setFont(UIManager.getFont("Label.font"));
		note.addFocusListener(this);
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
		editBtn = new JButton("edit");
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
			
			if(btnText.equals("^"))
			{
				comment.upvote();
			}
			else if(btnText.equals("v"))
			{
				comment.downvote();
			}
			else if(btnText.equals("delete"))
			{
				comment.deleteThis();
			}
			else if(btnText.equals("edit"))
			{
				note.setOpaque(true);
				note.setEditable(true);
				editBtn.setText("done");
				return;
			}
			else if(btnText.equals("done"))
			{
				comment.setText(note.getText());
				note.setOpaque(false);
				note.setEditable(false);
				editBtn.setText("edit");
			}
			else
			{
				JOptionPane.showMessageDialog(this.getTopLevelAncestor(), "Y U No Implement?");
			}
			
			parent = (ViewPanel)getParent();
			if(parent != null)
				parent.refresh();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(this.getTopLevelAncestor(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error - " + e.getMessage());
		}
	}
	
	public void focusGained(FocusEvent evt) {}
	
	public void focusLost(FocusEvent evt)
	{
		comment.setText(note.getText());
		note.setOpaque(false);
		note.setEditable(false);
		editBtn.setText("edit");
		
		parent = (ViewPanel)getParent();
		if(parent != null)
			parent.refresh();
	}
}
