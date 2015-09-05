import java.io.*;

public class FileOperation
{
	public FileOperation() {}
	
	public static void saveToFile(Object obj, String path)
	{
		try
		{
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
			objectOutputStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Warning - " + e.getMessage() + " <creating a new file>");
		}
		catch(Exception e)
		{
			System.out.println("Error - " + e.getMessage());
		}
	}
	
	public static Object readFromFile(String path)
	{
		try
		{
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
			return objectInputStream.readObject();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Warning - " + e.getMessage());
			return null;
		}
		catch(Exception e)
		{
			System.out.println("Error - " + e.getMessage());
			return null;
		}
	}
}