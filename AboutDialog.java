import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class AboutDialog extends JDialog implements ActionListener
{
	public AboutDialog(JFrame owner)
	{
		super(owner, true);
		setTitle(" About");
		setSize(300, 400);
		setResizable(false);
		setLocationRelativeTo(this);
		
		initPanel();
		setVisible(true);
	}
	
	private void initPanel()
	{
		Container aboutContent = getContentPane();
		aboutContent.setLayout(new BorderLayout());
		
		JButton okBtn = new JButton("OK");
		okBtn.addActionListener(this);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(okBtn);
		
		//aboutContent.add(new RemarkPanel(new Comment("Testing...")), BorderLayout.CENTER);
		aboutContent.add(bottomPanel, BorderLayout.PAGE_END);
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
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error - " + e.getMessage());
		}
	}
}
