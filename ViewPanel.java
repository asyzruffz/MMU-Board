import java.awt.*;
import javax.swing.*;

public class ViewPanel extends JPanel
{
	private Discussion discussion = new Discussion();
	
	public ViewPanel() {}
	
	public ViewPanel(Discussion disc)
	{
		discussion = disc;
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		initPanel();
	}
	
	private void initPanel()
	{
		if(discussion != null)
		{
			JTextArea title = new JTextArea("Discussion: "+discussion.getTitle());
			title.setFont(new Font("Default", Font.PLAIN, 16));
			title.setOpaque(false);
			title.setEditable(false);
			title.setLineWrap(true);
			title.setWrapStyleWord(true);
			
			JPanel titlePanel = new JPanel(new GridLayout(1, 0));
			titlePanel.add(title);
			titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
			titlePanel.setBorder(BorderFactory.createEmptyBorder(5,2,5,2));
			add(titlePanel);
			
			discussion.sortComments();
			for(Comment msg : discussion.getAllComment())
			{
				add(new RemarkPanel(msg));
			}
		}
		
		Box.Filler glue = (Box.Filler)Box.createVerticalGlue();
		glue.changeShape(glue.getMinimumSize(), new Dimension(0, Short.MAX_VALUE/4), glue.getMaximumSize());
		add(glue);
	}
	
	public void refresh()
	{
		JPanel parent = (JPanel)getParent();
		CardLayout cardLayout = (CardLayout)parent.getLayout();
		parent.add(new ViewPanel(discussion), "A");
		cardLayout.show(parent, "A");
		parent.remove(0);
		OperationPanel.save();
	}
}
