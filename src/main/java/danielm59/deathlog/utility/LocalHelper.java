package danielm59.deathlog.utility;

import net.minecraft.util.StatCollector;
import danielm59.deathlog.reference.Reference;

public class LocalHelper
{
	public static String getLocalString(String string)
	{
		string = "string." + Reference.MODID + ":" + string + ".string";
		return StatCollector.translateToLocal(string);
	}
}
