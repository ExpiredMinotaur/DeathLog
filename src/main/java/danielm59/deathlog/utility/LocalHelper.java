package danielm59.deathlog.utility;

import danielm59.deathlog.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.util.StatCollector;

public class LocalHelper
{
	public static String getLocalString(String string)
	{
		String stringL = String.format("string.%s:%s.string", Reference.MODID,
				string);
		if (StatCollector.canTranslate(stringL))
		{
			return StatCollector.translateToLocal(stringL);
		} else
		{
			LogHelper.error(
					String.format("Translation not found for: %s", stringL));
			return string;
		}
	}

	public static String getLocalEntityName(Entity entity)
	{
		String entityName = String.format("entity.%s.name",
				EntityList.getEntityString(entity));
		if (StatCollector.canTranslate(entityName))
		{
			return StatCollector.translateToLocal(entityName);
		} else
		{
			LogHelper.error(
					String.format("Translation not found for: %s", entityName));
			return EntityList.getEntityString(entity);
		}
	}

}