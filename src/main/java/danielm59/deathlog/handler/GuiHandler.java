package danielm59.deathlog.handler;

import danielm59.deathlog.DeathLog;
import danielm59.deathlog.client.gui.log.GuiDeathLog;
import danielm59.deathlog.handler.network.LogMessage;
import danielm59.deathlog.reference.GuiIDs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == GuiIDs.DEATH_LOG.ordinal())
		{
			DeathLog.snw.sendTo(new LogMessage(LogHandler.getData()), (EntityPlayerMP) player);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{

		if (ID == GuiIDs.DEATH_LOG.ordinal())
		{
			GuiDeathLog log = GuiDeathLog.log;
			return log;
		}
		return null;
	}
}
