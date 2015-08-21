package danielm59.deathlog.proxy;

import java.io.IOException;
import java.nio.file.Path;

public interface IProxy
{
	public abstract void loadTextures();

	public abstract String getLogPath() throws IOException;

}
