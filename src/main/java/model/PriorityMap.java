package model;

import java.util.HashMap;
import java.util.Map;

public class PriorityMap {
	public static final Map<Integer, String> priorityMap = new HashMap<>();
	
	static {
		priorityMap.put(1, "Top Priority");
		priorityMap.put(2, "Priority");
		priorityMap.put(3, "Medium Priority");
		priorityMap.put(4, "Low Priority");
		priorityMap.put(5, "Lowest Priority");
	}
	
	public static String getPriority(int priority) {
		return priorityMap.getOrDefault(priority, "Medium Priority");
	}

}
