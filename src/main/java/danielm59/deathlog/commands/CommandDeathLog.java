package danielm59.deathlog.commands;

import danielm59.deathlog.handler.LogHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandDeathLog extends CommandBase
{

	@Override
	public String getCommandName()
	{
		return "deathlog";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "/deathlog";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] astring)
	{
		if (astring.length == 0)
		{
			// display uses
		} else
		{
			if (astring[0].compareToIgnoreCase("reset") == 0)
			{
				LogHandler.reset();
			}
		}

	}

}
