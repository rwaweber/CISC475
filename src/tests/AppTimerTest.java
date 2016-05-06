package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import main.AppTimer;

public class AppTimerTest {

	@Test
	public void testConstructor() {
		AppTimer timer = new AppTimer();
		assertNotNull(timer);
	}
	
	@Test
	public void testGetStringTime(){
		AppTimer timer = new AppTimer();
		long time = 1800000 + 1920000 + 29000 + 373;
		System.out.println(time);
		String stringTime = timer.getStringTime(time);
		System.out.println(stringTime);
		assertNotNull(stringTime);
		assertEquals(stringTime, "62 min, 29 sec");
	}

}
