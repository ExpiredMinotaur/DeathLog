package danielm59.deathlog.handler.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import danielm59.deathlog.handler.LogHandler;

public class LogMessageHandler implements IMessageHandler<LogMessage, IMessage>
{
	@Override
	public IMessage onMessage(LogMessage message, MessageContext ctx)
	{
		LogHandler.update(message.toSend);
		return null;
	}
}
