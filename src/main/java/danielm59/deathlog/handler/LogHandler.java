package danielm59.deathlog.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import danielm59.deathlog.utility.LogHelper;

public class LogHandler
{
	static LinkedHashMap data = new LinkedHashMap();

	public static void loadData()
	{
		{
			try
			{
				FileInputStream fileIn = new FileInputStream(
						Minecraft.getMinecraft().mcDataDir
								+ "/DeathLog/log.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				data = (LinkedHashMap) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i)
			{
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c)
			{
				LogHelper.info("No DeathLog Data Found");
			}
		}
	}

	private static void saveData()
	{
		try
		{
			File file = new File(Minecraft.getMinecraft().mcDataDir
					+ "/DeathLog/log.ser");
			if (!file.exists())
			{
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			LogHelper.info(file.getAbsolutePath());
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(data);
			out.close();
			fileOut.close();
			LogHelper.info("Serialized data is saved in /deathlog/log.ser");
		} catch (IOException i)
		{
			i.printStackTrace();
		}
	}

	public static void LogEvent(LivingDeathEvent event)
	{
		EntityPlayer player = (EntityPlayer) event.entity;
		String name = player.getCommandSenderName();
		String source = event.source.damageType;
		LogHelper.info("player death:" + name + " by " + source);
		int old = 0;
		if (data.containsKey(name))
		{
			old = (Integer) data.get(name);
		}
		data.put(name, old + 1);
		saveData();
		LogHelper.info(name + " : " + data.get(name));
	}
}
