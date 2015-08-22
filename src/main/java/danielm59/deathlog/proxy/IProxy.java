package danielm59.deathlog.proxy;

import java.io.IOException;

public interface IProxy
{
	public abstract void loadTextures();

	public abstract String getLogPath() throws IOException;

}
