package danielm59.deathlog.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import danielm59.deathlog.utility.LogHelper;

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
				EntityPlayer player = (EntityPlayer) event.entity;
				String name = player.getCommandSenderName();
				String source = event.source.damageType;
				LogHelper.info("player death:" + name + " by " + source);
			}
		}
	}

}
