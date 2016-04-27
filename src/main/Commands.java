package main;

import java.util.List;

public interface Commands {
	
	public static ListToList MEAN() { return list -> Transformations.mean(list); }
	public static ListToList MAX()  { return list -> Transformations.max(list); }
	public static ListToList MIN()  { return list -> Transformations.min(list); }
	public static ListToList SUM()  { return list -> Transformations.sum(list); }
	public static ListToList NORMALIZE_LOCAL_EXTREMA() { return list -> Transformations.normalizeLocalExtrema(list);}
	public static ListToList STAND_DEV() { return list -> Transformations.standDev(list); }

}
