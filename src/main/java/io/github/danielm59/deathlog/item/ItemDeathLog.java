package io.github.danielm59.deathlog.item;

import io.github.danielm59.deathlog.DeathLog;
import io.github.danielm59.deathlog.reference.GuiIDs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemDeathLog extends ItemDL
{
	public ItemDeathLog()
	{
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName("deathlog");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World worldObj, EntityPlayer playerEntity)
	{
		playerEntity.openGui(DeathLog.instance, GuiIDs.DEATH_LOG.ordinal(), worldObj, 0, 0, 0);
		return itemStack;
	}

}
