package danielm59.deathlog.handler;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Set;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import danielm59.deathlog.DeathLog;
import danielm59.deathlog.utility.IOHelper;
import danielm59.deathlog.utility.LocalHelper;
import danielm59.deathlog.utility.LogHelper;
import danielm59.deathlog.utility.SortHelper;

public class LogHandler
{
	static LinkedHashMap<String, Integer> data = new LinkedHashMap();

	public static void loadData()
	{
		try
		{
			data = (LinkedHashMap<String, Integer>) IOHelper
					.readObject(DeathLog.proxy.getLogPath());
		} catch (IOException e)
		{
			LogHelper.info("Death data not found");
		}
	}

	private static void saveData()
	{
		try
		{
			IOHelper.writeObject(DeathLog.proxy.getLogPath(), data);
		} catch (IOException e)
		{
			LogHelper.info("Death data failed to save");
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
			sender.addChatMessage(new ChatComponentText(String.format(
					LocalHelper.getLocalString("deathmessage"), player,
					getDeaths(player))));
		} else
		{
			sender.addChatMessage(new ChatComponentText(String.format(
					LocalHelper.getLocalString("nodatamessage"), player)));
		}

	}

	public static int getDeaths(String player)
	{
		return data.get(player);
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
						new ChatComponentText(String.format(
								LocalHelper.getLocalString("deathmessage"),
								player, getDeaths(player))));
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
		LinkedHashMap<String, Integer> sortedData = SortHelper.sort(data);
		return sortedData.keySet();
	}

	public static void registerPlayer(String player)
	{
		data.put(player, 0);
	}
}
