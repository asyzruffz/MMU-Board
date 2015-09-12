import java.io.*;
import java.nio.file.*;

public class FileOperation
{
	public static void saveToFile(Object obj, String path)
	{
		try
		{
			File dir = new File("data");
			if(!dir.exists())
				dir.mkdir();
			File target = new File(dir, path);
			
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(target.getPath()));
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
			objectOutputStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Warning (FileOperation.java) " + e.getMessage() + " <creating a new file>");
		}
		catch(Exception e)
		{
			System.out.println("Error (FileOperation.java) " + e.getMessage());
		}
	}
	
	public static Object readFromFile(String path)
	{
		try
		{
			File dir = new File("data");
			if(!dir.exists())
				dir.mkdir();
			File target = new File(dir, path);
			
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(target.getPath()));
			return objectInputStream.readObject();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Warning (FileOperation.java) " + e.getMessage());
			return null;
		}
		catch(Exception e)
		{
			System.out.println("Error (FileOperation.java) " + e.getMessage());
			return null;
		}
	}
	
	public static File uploadFile(File src, String subdir)
	{
		try
		{
			File dir = new File(subdir);
			if(!dir.exists())
				dir.mkdir();
			
			File dest = new File(dir, src.getName());
			
			Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
			return dest;
		}
		catch(NullPointerException e)
		{
			System.out.println("Error (FileOperation.java) " + e.getMessage());
			return null;
		}
		catch(Exception e)
		{
			System.out.println("Error (FileOperation.java) " + e.getMessage());
			return null;
		}
	}
	
	public static void downloadFile(File src, File dest)
	{
		try
		{
			Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		catch(NullPointerException e)
		{
			System.out.println("Error (FileOperation.java) " + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error (FileOperation.java) " + e.getMessage());
		}
	}
}