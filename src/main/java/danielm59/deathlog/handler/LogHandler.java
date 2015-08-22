package danielm59.deathlog.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Set;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import danielm59.deathlog.DeathLog;
import danielm59.deathlog.utility.LogHelper;

public class LogHandler
{
	static LinkedHashMap<String, Integer> data = new LinkedHashMap();

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

	public static void logEvent(LivingDeathEvent event)
	{
		EntityPlayer playerE = (EntityPlayer) event.entity;
		String player = playerE.getCommandSenderName();
		String source = event.source.damageType;
		addDeath(player);
		saveData();
		tellAll(player);
	}

	public static void deathsCommand(ICommandSender sender, String player)
	{
		if (playerRecorded(player))
		{
			sender.addChatMessage(new ChatComponentText(player + " has died "
					+ getDeaths(player) + " times"));
		} else
		{
			sender.addChatMessage(new ChatComponentText(
					"No Death record found for " + player));
		}

	}

	public static int getDeaths(String player)
	{
		return (Integer) data.get(player);
	}

	public static boolean playerRecorded(String player)
	{
		return data.containsKey(player);
	}

	private static void addDeath(String player)
	{
		int oldCount = 0;
		if (playerRecorded(player))
		{
			oldCount = getDeaths(player);
		}
		data.put(player, oldCount + 1);
	}

	private static void tellAll(String player)
	{
		MinecraftServer
				.getServer()
				.getConfigurationManager()
				.sendChatMsg(
						new ChatComponentText(player + " has died "
								+ getDeaths(player) + " times"));
	}

	public static void update(LinkedHashMap newData)
	{
		data.clear();
		data.putAll(newData);
	}

	public static LinkedHashMap getData()
	{
		return data;
	}

	public static Set<String> getPlayers()
	{
		return data.keySet();
	}

}
