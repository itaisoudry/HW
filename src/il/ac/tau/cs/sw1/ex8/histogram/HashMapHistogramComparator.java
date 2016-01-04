package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class HashMapHistogramComparator<T> implements Comparator<T> {
	private Map<T, Integer> map = new HashMap<T, Integer>();

	public HashMapHistogramComparator(Map<T, Integer> map) {
		this.map = map;
	}

	@Override
	public int compare(T arg0, T arg1) {
		// Comparing using compareable
		Comparable<T> valueA = (Comparable<T>) map.get(arg0);
		Comparable<T> valueB = (Comparable<T>) map.get(arg1);
		return valueB.compareTo((T) valueA);
	}

}
