package danielm59.deathlog.handler.network;

import io.netty.buffer.ByteBuf;

import java.util.LinkedHashMap;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class LogMessage implements IMessage
{
	LinkedHashMap<String, LinkedHashMap<String, Integer>> toSend = new LinkedHashMap();

	public LogMessage()
	{
	}

	public LogMessage(LinkedHashMap toSend)
	{
		this.toSend.clear();
		this.toSend.putAll(toSend);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(toSend.size());
		for (String key : toSend.keySet())
		{
			ByteBufUtils.writeUTF8String(buf, key);
			LinkedHashMap<String, Integer> playerData = toSend.get(key);
			buf.writeInt(playerData.size());
			for (String key2 : playerData.keySet())
			{
				ByteBufUtils.writeUTF8String(buf, key2);
				buf.writeInt(playerData.get(key2));
			}
		}
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		toSend.clear();
		int size = buf.readInt();
		for (int i = 0; i < size; i++)
		{
			String player = ByteBufUtils.readUTF8String(buf);
			LinkedHashMap<String, Integer> playerData = new LinkedHashMap();
			int size2 = buf.readInt();
			for (int j = 0; j < size2; j++)
			{
				String type = ByteBufUtils.readUTF8String(buf);
				int count = buf.readInt();
				playerData.put(type, count);
			}
			toSend.put(player, playerData);
		}
	}
}
