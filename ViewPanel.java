import java.awt.*;
import javax.swing.*;

public class ViewPanel extends JPanel
{
	private Discussion discussion = new Discussion();
	
	public ViewPanel() {}
	
	public ViewPanel(Discussion disc)
	{
		discussion = disc;
		int count = 10;
		if(discussion != null)
			count = Math.max(discussion.getAllComment().size(), 10);
		setLayout(new GridLayout(count, 1));
		
		initPanel();
	}
	
	private void initPanel()
	{
		if(discussion != null)
		{
			//note.append("Subject: " + selectedSubject.getSubjName() + "\n\n");
			//note.append("Title: " + selectedDiscussion.getTitle() + "\n\n\n");
			
			for(Comment msg : discussion.getAllComment())
			{
				add(new RemarkPanel(msg));
			}
		}
	}
}
