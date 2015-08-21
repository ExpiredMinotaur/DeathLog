package danielm59.deathlog.proxy;

import java.io.IOException;
import java.nio.file.Path;

import net.minecraft.client.Minecraft;

public class ClientProxy extends CommonProxy
{

	@Override
	public void loadTextures()
	{

	}

	@Override
	public String getLogPath() throws IOException
	{

		return Minecraft.getMinecraft().mcDataDir + "/DeathLog/log.ser";
	}

}
