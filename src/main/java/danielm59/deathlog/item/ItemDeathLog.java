package danielm59.deathlog.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import danielm59.deathlog.DeathLog;
import danielm59.deathlog.reference.GuiIDs;

public class ItemDeathLog extends ItemDL
{
	public ItemDeathLog()
	{
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName("deathlog");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World worldObj,
			EntityPlayer playerEntity)
	{
		playerEntity.openGui(DeathLog.instance, GuiIDs.DEATH_LOG.ordinal(),
				worldObj, 0, 0, 0);
		return itemStack;
	}

}
