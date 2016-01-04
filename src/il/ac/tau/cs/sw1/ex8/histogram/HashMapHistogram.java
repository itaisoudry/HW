package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapHistogram<T> implements IHistogram<T> {
	private Map<T, Integer> map = new HashMap<T, Integer>();

	@Override
	public Iterator<T> iterator() {

		return map.keySet().iterator();
	}

	@Override
	public void addItem(T item) {
		// If item is in the hashmap, update its value ( counter )
		if (map.containsKey(item)) {
			map.put(item, map.get(item) + 1);
		} else
			map.put(item, 1);

	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValue {
		// Deals with Illeagle k values.
		if (k < 1) {
			throw new IllegalKValue(k);
		}
		// If item is in the hashmap, update its value ( counter )
		if (map.containsKey(item)) {
			map.put(item, map.get(item) + k);
		} else
			map.put(item, k);

	}

	@Override
	public void addAll(Collection<T> items) {
		for (T item : items) {
			// If item is in the hashmap, update its value ( counter )
			if (map.containsKey(item)) {
				map.put(item, map.get(item) + 1);
			} else
				map.put(item, 1);
		}

	}

	@Override
	public int getCountForItem(T item) {
		try {
			return map.get(item);
		} catch (NullPointerException e) {
			return 0;
		}

	}

	@Override
	public void clear() {
		map.clear();

	}

	@Override
	public Set<T> getItemsSet() {
		return map.keySet();
	}

}
