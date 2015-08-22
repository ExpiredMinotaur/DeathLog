package danielm59.deathlog.handler.network;

import io.netty.buffer.ByteBuf;

import java.util.LinkedHashMap;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class LogMessage implements IMessage
{

	LinkedHashMap<String, Integer> toSend = new LinkedHashMap();

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
		int size = toSend.size();
		buf.writeInt(size);
		for (String key : toSend.keySet())
		{
			ByteBufUtils.writeUTF8String(buf, key);
			buf.writeInt(toSend.get(key));
		}
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		toSend.clear();
		int size = buf.readInt();
		for (int i = 0; i < size; i++)
		{
			String name = ByteBufUtils.readUTF8String(buf);
			int deaths = buf.readInt();
			toSend.put(name, deaths);
		}
	}

}
