/**************************************************
* TCP2201 Project
* Group Members: 1.
*				 2.
*				 3.
*				 4.
*				 5.
**************************************************/

import java.awt.*;
import javax.swing.*;

public class Apps
{
	MainFrame mFrame = new MainFrame("Online MMU-Board System");
	
	public Apps()
	{
		System.out.println("Apps initialized!");
		
		JDialog loginDialog = new JDialog(mFrame);
		Container loginContent = loginDialog.getContentPane();
		loginContent.add(new Login(new FlowLayout()));
		loginDialog.setSize(300, 400);
		loginDialog.setResizable(false);
		loginDialog.setVisible(true);
		
		mFrame.setExtendedState(MainFrame.MAXIMIZED_BOTH);
		mFrame.setVisible(true);
	}
	
	public void run()
	{
		while(true)
		{
			//
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			Apps myApps = new Apps();
			//myApps.run();
			
			//System.out.println("Apps destroyed!");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
