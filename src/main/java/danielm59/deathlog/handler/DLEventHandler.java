package danielm59.deathlog.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class DLEventHandler
{
	public DLEventHandler()
	{

	}

	@SubscribeEvent
	public void onLivingDeathEvent(LivingDeathEvent event)
	{
		if (!event.entity.worldObj.isRemote)
		{
			if (event.entity instanceof EntityPlayer)
			{
				LogHandler.logEvent(event);
			}
		}
	}
	
	@SubscribeEvent
	public void onEntityJoinWorldEvent (EntityJoinWorldEvent event)
	{
		if (!event.world.isRemote)
		{
			if (event.entity instanceof EntityPlayer)
			{
				EntityPlayer playerE = (EntityPlayer) event.entity;
				String player = playerE.getCommandSenderName();
				if(!LogHandler.playerRecorded(player))
				{
					LogHandler.registerPlayer(player);
				}
			}
		}
	}

}
