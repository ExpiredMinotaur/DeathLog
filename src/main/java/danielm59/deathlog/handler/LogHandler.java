package danielm59.deathlog.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import danielm59.deathlog.DeathLog;
import danielm59.deathlog.utility.LocalHelper;
import danielm59.deathlog.utility.LogHelper;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

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
			sender.addChatMessage(new ChatComponentText(
					String.format(LocalHelper.getLocalString("deathmessage"),
							player, getDeaths(player))));
		} else
		{
			sender.addChatMessage(new ChatComponentText(String.format(
					LocalHelper.getLocalString("nodatamessage"), player)));
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
		MinecraftServer.getServer().getConfigurationManager()
				.sendChatMsg(new ChatComponentText(String.format(
						LocalHelper.getLocalString("deathmessage"), player,
						getDeaths(player))));
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
		LinkedHashMap<String, Integer> sortedData = sort(data);
		return sortedData.keySet();
	}

	public static <K extends Comparable, V extends Comparable> LinkedHashMap<K, V> sort(
			Map<K, V> map)
	{
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(
				map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
		{

			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2)
			{
				int c = o2.getValue().compareTo(o1.getValue());
				if (c == 0)
				{
					c = o1.getKey().compareTo(o2.getKey());
				}
				return c;
			}
		});
		LinkedHashMap<K, V> sortedMap = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> entry : entries)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}
}
