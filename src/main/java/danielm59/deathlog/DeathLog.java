package danielm59.deathlog;

import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import danielm59.deathlog.handler.ConfigurationHandler;
import danielm59.deathlog.handler.DLEventHandler;
import danielm59.deathlog.handler.GuiHandler;
import danielm59.deathlog.handler.LogHandler;
import danielm59.deathlog.init.ModItems;
import danielm59.deathlog.proxy.IProxy;
import danielm59.deathlog.reference.Reference;
import danielm59.deathlog.utility.LogHelper;

@Mod(modid = Reference.MODID, name = Reference.MODNAME, version = Reference.VERSION, guiFactory = Reference.GUIFACTORY)
public class DeathLog
{
	@Mod.Instance(Reference.MODID)
	public static DeathLog instance;

	@SidedProxy(clientSide = Reference.CPROXY, serverSide = Reference.SPROXY)
	public static IProxy proxy;

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		ModItems.init();
		LogHelper.info("Pre Initialization Complete!");
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(new DLEventHandler());
		LogHelper.info("Initialization Complete!");
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		LogHandler.loadData();
		LogHelper.info("Post Initialization Complete!");
	}

}
