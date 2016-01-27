package io.github.danielm59.deathlog.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
	public void onEntityJoinWorldEvent(EntityJoinWorldEvent event)
	{
		if (!event.world.isRemote)
		{
			if (event.entity instanceof EntityPlayer)
			{
				EntityPlayer playerE = (EntityPlayer) event.entity;
				String player = playerE.getName();
				if (!LogHandler.statRecorded(player, "COUNT"))
				{
					LogHandler.registerPlayer(player);
				}
			}
		}
	}

}
