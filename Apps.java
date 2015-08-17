/**************************************************
* TCP2201 Project
* Group Members: 1.
*				 2.
*				 3.
*				 4.
*				 5.
**************************************************/

public class Apps
{
	MainFrame mFrame = new MainFrame("Online MMU-Board System");
	
	public Apps()
	{
		mFrame.setExtendedState(MainFrame.MAXIMIZED_BOTH);
		mFrame.setVisible(true);
		System.out.println("Apps initialized!");
	}
	
	public void run()
	{
		while(true)
		{
			//mFrame.run();
		}
	}
	
	public static void main(String[] args)
	{
		Apps myApps = new Apps();
		//myApps.run();
		
		//System.out.println("Apps destroyed!");
	}
}
