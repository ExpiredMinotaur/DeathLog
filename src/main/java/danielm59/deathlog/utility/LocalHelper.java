package danielm59.deathlog.utility;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.StatCollector;
import danielm59.deathlog.reference.Reference;

public class LocalHelper
{
	public static String getLocalString(String string)
	{
		string = String.format("string.%s:%s.string", Reference.MODID, string);
		return StatCollector.translateToLocal(string);
	}

	public static String getLocalEntityName(Entity entity)
	{
		String entityName = String.format("entity.%s.name",
				EntityList.getEntityString(entity));
		return StatCollector.translateToLocal(entityName);
	}

}
