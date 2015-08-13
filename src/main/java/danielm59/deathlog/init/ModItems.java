package danielm59.deathlog.init;

import cpw.mods.fml.common.registry.GameRegistry;
import danielm59.deathlog.item.ItemDL;
import danielm59.deathlog.item.ItemDeathLog;

public class ModItems
{
	public static final ItemDL deathlog = new ItemDeathLog();

	public static void init()
	{
		GameRegistry.registerItem(deathlog, "deathlog");
	}
}
