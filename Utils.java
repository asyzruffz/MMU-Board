import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/*
 *  soapUI, copyright (C) 2004-2009 eviware.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */


public class Utils {
	
	public static String toHtml(String string)
	{
		if(string == null || string.equals(""))
			return "<html><body></body></html>";

		BufferedReader st = new BufferedReader(new StringReader(string));
		StringBuffer buf = new StringBuffer("<html><body>");

		try
		{
			String str = st.readLine();

			while(str != null)
			{
				if(str.equalsIgnoreCase("<br/>"))
				{
					str = "<br>";
				}

				buf.append(str);

				if(!str.equalsIgnoreCase("<br>"))
				{
					buf.append("<br>");
				}

				str = st.readLine();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		buf.append("</body></html>");
		string = buf.toString();
		return string;
	}
}
