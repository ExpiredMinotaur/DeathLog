package danielm59.deathlog;

import danielm59.deathlog.commands.CommandDeathLog;
import danielm59.deathlog.commands.CommandDeaths;
import danielm59.deathlog.handler.ConfigurationHandler;
import danielm59.deathlog.handler.DLEventHandler;
import danielm59.deathlog.handler.GuiHandler;
import danielm59.deathlog.handler.LogHandler;
import danielm59.deathlog.handler.network.LogMessage;
import danielm59.deathlog.handler.network.LogMessageHandler;
import danielm59.deathlog.init.ModItems;
import danielm59.deathlog.init.Recipes;
import danielm59.deathlog.proxy.IProxy;
import danielm59.deathlog.reference.Reference;
import danielm59.deathlog.utility.LogHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION, guiFactory = Reference.GUIFACTORY)
public class DeathLog
{
	@Mod.Instance(Reference.MODID)
	public static DeathLog instance;
	public static SimpleNetworkWrapper snw;

	@SidedProxy(clientSide = Reference.CPROXY, serverSide = Reference.SPROXY)
	public static IProxy proxy;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		snw = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
		snw.registerMessage(LogMessageHandler.class, LogMessage.class, 0, Side.CLIENT);
		ModItems.init();
		LogHelper.info("Pre Initialization Complete!");
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Recipes.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new DLEventHandler());
		proxy.loadTextures();
		LogHelper.info("Initialization Complete!");
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		LogHelper.info("Post Initialization Complete!");
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event)
	{
		LogHandler.loadData();
		event.registerServerCommand(new CommandDeaths());
		event.registerServerCommand(new CommandDeathLog());
	}
}
