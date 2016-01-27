package io.github.danielm59.deathlog.init;

import io.github.danielm59.deathlog.item.ItemDL;
import io.github.danielm59.deathlog.item.ItemDeathLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems
{
	public static final ItemDL deathlog = new ItemDeathLog();

	public static void init()
	{
		GameRegistry.registerItem(deathlog, "deathlog");
	}

	@SideOnly(Side.CLIENT)
	public static void textures()
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(deathlog, 0,
				new ModelResourceLocation("deathlog:deathlog", "inventory"));
	}
}
