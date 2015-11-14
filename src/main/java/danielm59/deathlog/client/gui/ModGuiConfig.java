package danielm59.deathlog.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import danielm59.deathlog.handler.ConfigurationHandler;
import danielm59.deathlog.reference.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class ModGuiConfig extends GuiConfig
{

	public ModGuiConfig(GuiScreen guiScreen)
	{

		super(guiScreen,
				new ConfigElement(ConfigurationHandler.configuration
						.getCategory(Configuration.CATEGORY_GENERAL))
								.getChildElements(),
				Reference.MODID, false, false, GuiConfig.getAbridgedConfigPath(
						ConfigurationHandler.configuration.toString()));

	}

}
