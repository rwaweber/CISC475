package tests;

import static org.junit.Assert.*;


import org.junit.Test;

import main.AppTimer;

public class AppTimerTest {

	long startTime = 1800000 + 1920000 + 29000 + 373;
	long endTime = startTime + (60000 * 22) + (1000 * 45);

	@Test
	public void testConstructor() {
		AppTimer timer = new AppTimer();
		assertNotNull(timer);
	}

	@Test
	public void testGetStringTime(){
		AppTimer timer = new AppTimer();
		String stringTime = timer.getStringTime(startTime);
		assertNotNull(stringTime);
		assertEquals(stringTime, "62 min, 29 sec");
	}

	@Test
	public void testStartTimer(){
		AppTimer timer = new AppTimer();
		timer.startTimer();
		long startTime = timer.getStartTime();
		assertNotNull(startTime);
		assertNotEquals(startTime, 0);
	}

	@Test
	public void testEndTimer(){
		AppTimer timer = new AppTimer();
		timer.endTimer();
		long endTime = timer.getEndTime();
		assertNotNull(endTime);
		assertNotEquals(endTime, 0);
	}
	
	@Test
	public void testSetStartTime(){
		AppTimer timer = new AppTimer();
		timer.setStartTime(1000l);
		assertEquals(timer.getStartTime(), 1000);
	}
	
	@Test
	public void testSetEndTime(){
		AppTimer timer = new AppTimer();
		timer.setEndTime(1000l);
		assertEquals(timer.getEndTime(), 1000);
	}
	
	@Test
	public void getElapsedTime(){
		AppTimer timer = new AppTimer();
		timer.setStartTime(startTime);
		timer.setEndTime(endTime);
		String elapsedTime = timer.getElapsedTime();
		assertEquals(elapsedTime, "22 min, 45 sec");
		
	}

}
