package io.github.danielm59.deathlog.handler.network;

import io.github.danielm59.deathlog.handler.LogHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LogMessageHandler implements IMessageHandler<LogMessage, IMessage>
{
	@Override
	public IMessage onMessage(LogMessage message, MessageContext ctx)
	{
		LogHandler.update(message.toSend);
		return null;
	}
}
