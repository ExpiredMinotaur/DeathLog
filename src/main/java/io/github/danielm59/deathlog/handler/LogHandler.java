package io.github.danielm59.deathlog.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import io.github.danielm59.deathlog.DeathLog;
import io.github.danielm59.deathlog.utility.IOHelper;
import io.github.danielm59.deathlog.utility.LocalHelper;
import io.github.danielm59.deathlog.utility.SortHelper;
import io.github.danielm59.m59Libs.log.LogHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class LogHandler
{
	static LinkedHashMap<String, LinkedHashMap<String, Integer>> data = new LinkedHashMap<String, LinkedHashMap<String, Integer>>();

	@SuppressWarnings("unchecked")
	public static void loadData()
	{
		try
		{
			data = (LinkedHashMap<String, LinkedHashMap<String, Integer>>) IOHelper.readObject(DeathLog.proxy.getLogPath());
		} catch (IOException e)
		{
			LogHelper.info("Death data not found");
		} catch (ClassNotFoundException e)
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
		String player = playerE.getName();
		String damageType = event.source.getDamageType();
		addDeath(player, String.format("TYPE:%s", damageType));
		if (damageType == "mob")
		{
			Entity entity = event.source.getSourceOfDamage();
			String entityName = LocalHelper.getLocalEntityName(entity);
			addDeath(player, String.format("MOB:%s", entityName));
		}
		addDeath(player, "COUNT");
		;
		saveData();
		tellAll(player);
	}

	public static void deathsCommand(ICommandSender sender, String player)
	{
		if (statRecorded(player, "COUNT"))
		{
			sender.addChatMessage(new ChatComponentText(
					String.format(LocalHelper.getLocalString("deathmessage"), player, getDeaths(player, "COUNT"))));
		} else
		{
			sender.addChatMessage(new ChatComponentText(String.format(LocalHelper.getLocalString("nodatamessage"), player)));
		}
	}

	public static int getDeaths(String player, String stat)
	{
		if (data.get(player).containsKey(stat))
		{
			return data.get(player).get(stat);
		}
		return 0;
	}

	public static boolean statRecorded(String player, String stat)
	{
		if (data.containsKey(player))
		{
			return data.get(player).containsKey(stat);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private static void addDeath(String player, String stat)
	{
		int oldCount = 0;
		LinkedHashMap<String, Integer> playerData = new LinkedHashMap<String, Integer>();
		if (data.containsKey(player))
		{
			playerData = (LinkedHashMap<String, Integer>) data.get(player).clone();
			if (statRecorded(player, stat))
			{
				oldCount = playerData.get(stat);
			}
		}
		playerData.put(stat, oldCount + 1);
		data.put(player, playerData);
	}

	private static void tellAll(String player)
	{
		MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(
				String.format(LocalHelper.getLocalString("deathmessage"), player, getDeaths(player, "COUNT"))));
	}

	public static void update(LinkedHashMap<String, LinkedHashMap<String, Integer>> newData)
	{
		data.clear();
		data.putAll(newData);
	}

	public static LinkedHashMap<String, LinkedHashMap<String, Integer>> getData()
	{
		return data;
	}

	public static Set<String> getPlayers(String type)
	{
		LinkedHashMap<String, Integer> sortData = new LinkedHashMap<String, Integer>();
		if (data.size() != 0)
		{
			for (String key : data.keySet())
			{
				if (data.get(key).containsKey(type))
				{
					sortData.put(key, data.get(key).get(type));
				} else
				{
					sortData.put(key, 0);
				}
			}
			LinkedHashMap<String, Integer> sortedData = SortHelper.sort(sortData);
			return sortedData.keySet();
		}
		return data.keySet();
	}

	public static void registerPlayer(String player)
	{
		LinkedHashMap<String, Integer> entry = new LinkedHashMap<String, Integer>();
		entry.put("COUNT", 0);
		data.put(player, entry);
		saveData();
	}

	public static ArrayList<String> getDeathTypes()
	{
		ArrayList<String> types = new ArrayList<String>();
		for (String player : getPlayers("COUNT"))
		{
			for (String record : data.get(player).keySet())
			{
				if (record.substring(0, Math.min(record.length(), 5)).compareTo("TYPE:") == 0)
				{
					if (!types.contains(record))
					{
						types.add(record);
					}
				}
			}
		}
		return types;
	}

	public static void reset()
	{
		data.clear();
		saveData();
	}

}
