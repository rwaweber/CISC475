package main;

import java.util.List;

public interface Commands {
	
	public static ListToList MEAN = list -> Transformations.mean(list);
	public static ListToList MAX = list -> Transformations.max(list);
	public static ListToList MIN = list -> Transformations.min(list);
	public static ListToList SUM = list -> Transformations.sum(list);
	public static ListToList NORMALIZE_LOCAL_EXTREMA = list -> Transformations.normalizeLocalExtrema(list);
	public static ListToList STAND_DEV = list -> Transformations.standDev(list);
	public static ListToMap FREQ_LIST = list -> Transformations.getFrequency(list);

}
