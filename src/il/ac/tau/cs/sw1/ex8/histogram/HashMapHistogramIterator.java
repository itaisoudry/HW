package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class HashMapHistogramIterator<T> implements Iterator<T> {
	private Map<T, Integer> map = new HashMap<T, Integer>();
	private Iterator<T> iterator;

	public HashMapHistogramIterator(Map<T, Integer> map) {
		this.map = sortMap(map);
		this.iterator = map.keySet().iterator();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public T next() {
		return iterator.next();
	}
	//Sorting map using the comperator
	private Map<T, Integer> sortMap(Map<T, Integer> unsortedMap) {
		Map<T, Integer> sortedMap = new TreeMap(new HashMapHistogramComparator(unsortedMap));
		sortedMap.putAll(unsortedMap);

		return sortedMap;
	}

}
