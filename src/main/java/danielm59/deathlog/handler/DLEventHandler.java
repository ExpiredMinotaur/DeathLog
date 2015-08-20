package danielm59.deathlog.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

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
				LogHandler.LogEvent(event);
			}
		}
	}

}
