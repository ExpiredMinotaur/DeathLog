package danielm59.deathlog.proxy;

import java.io.IOException;

import cpw.mods.fml.server.FMLServerHandler;

public class ServerProxy extends CommonProxy
{

	@Override
	public void loadTextures()
	{

	}

	@Override
	public String getLogPath() throws IOException
	{
		return FMLServerHandler.instance().getSavesDirectory()
				+ "/DeathLog/log.ser";

	}

}
