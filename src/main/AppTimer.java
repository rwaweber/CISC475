package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AppTimer {

	private long startTime;
	private long endTime;

	public String getStringTime(long time) {
		return String.format("%d min, %d sec", 
				TimeUnit.MILLISECONDS.toMinutes(time),
				TimeUnit.MILLISECONDS.toSeconds(time) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))
				);
	}

	public void startTimer() {
		setStartTime(System.currentTimeMillis());
	}

	public long getStartTime() {
		return startTime;
	}

	public void endTimer() {
		setEndTime(System.currentTimeMillis());
	}

	public long getEndTime() {
		return endTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getElapsedTime() {
		return getStringTime(endTime - startTime);
	}



}
