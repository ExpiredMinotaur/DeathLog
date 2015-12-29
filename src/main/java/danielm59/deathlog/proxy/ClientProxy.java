package danielm59.deathlog.proxy;

import java.io.IOException;

import danielm59.deathlog.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.server.integrated.IntegratedServer;

public class ClientProxy extends CommonProxy
{

	@Override
	public void loadTextures()
	{
		ModItems.textures();
	}

	@Override
	public String getLogPath() throws IOException
	{
		IntegratedServer server = Minecraft.getMinecraft().getIntegratedServer();
		String worldName = (server != null) ? server.getFolderName() : "world";

		return Minecraft.getMinecraft().mcDataDir + "/saves/" + worldName + "/DeathLog/log.dat";
	}

}
