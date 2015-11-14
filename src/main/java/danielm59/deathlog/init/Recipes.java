package danielm59.deathlog.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
	public static void init()
	{
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.deathlog,
				Items.wooden_sword, Items.book));
	}
}
