package danielm59.deathlog.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOHelper
{
	public static Object readObject(String filename)
	{
		try
		{
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Object data = in.readObject();
			in.close();
			fileIn.close();
			return data;
		} catch (IOException i)
		{
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c)
		{
			c.printStackTrace();
			return null;
		}
	}

	public static void writeObject(String filename, Object data)
	{
		try
		{
			File file = new File(filename);
			if (!file.exists())
			{
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
		} catch (IOException i)
		{
			i.printStackTrace();
		}
	}
}
