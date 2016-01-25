package danielm59.deathlog.utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SortHelper
{
	@SuppressWarnings("rawtypes")
	public static <K extends Comparable, V extends Comparable> LinkedHashMap<K, V> sort(Map<K, V> map)
	{
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
		{

			@SuppressWarnings("unchecked")
			@Override
			public int compare(Entry<K, V> o1, Entry<K, V> o2)
			{
				int c = o2.getValue().compareTo(o1.getValue());
				if (c == 0)
				{
					c = o1.getKey().compareTo(o2.getKey());
				}
				return c;
			}
		});
		LinkedHashMap<K, V> sortedMap = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> entry : entries)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

}
