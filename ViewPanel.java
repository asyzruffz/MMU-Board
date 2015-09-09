import java.awt.*;
import javax.swing.*;

public class ViewPanel extends JPanel
{
	private Discussion discussion = new Discussion();
	
	public ViewPanel() {}
	
	public ViewPanel(Discussion disc)
	{
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		discussion = disc;
		
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
	
	public void update()
	{
		//doLayout();
		revalidate();
		repaint();
	}
}
