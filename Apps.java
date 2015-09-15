/****************************************************
* TCP2201 Project									*
* Group Members: 1. Muhammad Asyraf bin Ibrahim		*
*				 2. Khairul Azmi					*
*				 3. Sakinah binti Burhan			*
*				 4. Kamil Pervaiz					*
*****************************************************/

import java.awt.*;
import javax.swing.*;

public class Apps
{
	private MainFrame mFrame = new MainFrame();
	
	public Apps()
	{
		System.out.println("Apps initialized!");
		run();
	}
	
	public void run()
	{
		while(true)
		{
			if(!mFrame.isVisible())
			{
				LoginDialog loginPrompt = new LoginDialog(mFrame);
				mFrame = new MainFrame("Online MMU-Board System", loginPrompt.acquireUser());
			}
			System.out.print("");
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			Apps myApps = new Apps();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			System.out.println("Error - " + e.getMessage());
			System.out.println("Apps destroyed!");
			System.exit(0);
		}
	}
}
