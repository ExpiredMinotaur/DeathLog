package danielm59.deathlog.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import danielm59.deathlog.DeathLog;
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
						DeathLog.proxy.getLogPath());
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
			File file = new File(DeathLog.proxy.getLogPath());
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
			LogHelper.info("Saved Death Data");
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
		int old = 0;
		if (data.containsKey(name))
		{
			old = (Integer) data.get(name);
		}
		data.put(name, old + 1);
		saveData();
		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(name + " has died "+ data.get(name) + " times"));
	}
}
