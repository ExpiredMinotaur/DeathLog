package danielm59.deathlog.handler;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import danielm59.deathlog.reference.Reference;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler
{

	public static Configuration configuration;

	public static void init(File configFile)
	{

		if (configuration == null)
		{

			configuration = new Configuration(configFile);
			loadConfiguration();

		}

	}

	@SubscribeEvent
	public void onConfigurationChangedEvent(
			ConfigChangedEvent.OnConfigChangedEvent event)
	{

		if (event.modID.equalsIgnoreCase(Reference.MODID))
		{

			loadConfiguration();

		}

	}

	private static void loadConfiguration()
	{

		// load config values

		if (configuration.hasChanged())
		{

			configuration.save();

		}

	}

}
