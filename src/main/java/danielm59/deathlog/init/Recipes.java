package danielm59.deathlog.init;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
	public static void init()
	{
		GameRegistry.addRecipe(new ShapelessOreRecipe(ModItems.deathlog,
				Items.wooden_sword, Items.book));
	}
}
