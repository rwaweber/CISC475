package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AppTimer {

	public String getStringTime(long time) {
//		Date date = new Date(time);
//		DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
//		String stringTime = formatter.format(date);
//		return stringTime;
		return String.format("%d min, %d sec", 
			    TimeUnit.MILLISECONDS.toMinutes(time),
			    TimeUnit.MILLISECONDS.toSeconds(time) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))
			);
	}
	
	

}
