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
		mFrame.setVisible(true);
		System.out.println("Apps initialized!");
	}
	
	public void run()
	{
		while(true)
		{
			//mFrame.run();
		}
		
		//System.out.println("Apps destroyed!");
	}
	
	public static void main(String[] args)
	{
		Apps myApps = new Apps();
		//myApps.run();
	}
}
