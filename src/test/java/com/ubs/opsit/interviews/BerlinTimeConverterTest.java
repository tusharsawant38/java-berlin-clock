package com.ubs.opsit.interviews;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class BerlinTimeConverterTest {
	private BerlinTimeConverter berlinTimeConverter;

	private static final String ALL_LIGTHS_OFF = "OOOO";
	private static final String ALL_TOP_MINUTES_LIGTHS_OFF = "OOOOOOOOOOO";

	@Before
	public void setupBeforeTest() {
		berlinTimeConverter = new BerlinTimeConverter();
	}

	@Test
	public void secondLightShouldHaveOnly1Lamp() {
		assertEquals(1, berlinTimeConverter.getSecondsLight(20).length());
	}

	@Test
	public void topHoursLightShouldHaveOnly4Lamps() {
		assertEquals(4, berlinTimeConverter.getTopHoursLight(23).length());
	}

	@Test
	public void bottomHoursLightShouldHaveOnly4Lamps() {
		assertEquals(4, berlinTimeConverter.getBottomHoursLight(2).length());
	}

	@Test
	public void topMinutesLightShouldHaveOnly11Lamps() {
		assertEquals(11, berlinTimeConverter.getTopMinutesLight(54).length());
	}

	@Test
	public void bottomMinutesLightShouldHaveOnly4Lamps() {
		assertEquals(4, berlinTimeConverter.getBottomMinutesLight(4).length());
	}

	@Test
	public void secondLightShouldBlinkOnOffEveryTwoSeconds() {
		assertEquals(Lights.YELLOW_LIGHT.getLight(), berlinTimeConverter.getSecondsLight(20));
		assertEquals(Lights.OFF_LIGHT.getLight(), berlinTimeConverter.getSecondsLight(23));
	}

	@Test
	public void secondLightShouldNotLightRedLight() {
		assertNotEquals(Lights.RED_LIGHT.getLight(), berlinTimeConverter.getSecondsLight(20));
		assertNotEquals(Lights.RED_LIGHT.getLight(), berlinTimeConverter.getSecondsLight(23));
	}

	@Test
	public void topHoursLightShouldNotLightIfGivenHoursAreLessThan5() {
		assertEquals(ALL_LIGTHS_OFF, berlinTimeConverter.getTopHoursLight(3));
		assertEquals(ALL_LIGTHS_OFF, berlinTimeConverter.getTopHoursLight(0));
		assertNotEquals(ALL_LIGTHS_OFF, berlinTimeConverter.getTopHoursLight(5));
	}

	@Test
	public void bottomHoursLightShouldNotLightIfGivenHoursAreLessThan1() {
		assertEquals(ALL_LIGTHS_OFF, berlinTimeConverter.getBottomHoursLight(0));
		assertNotEquals(ALL_LIGTHS_OFF, berlinTimeConverter.getBottomHoursLight(4));
	}

	@Test
	public void topMinutesLightShouldNotLightIfGivenMinutesAreLessThan5() {
		assertEquals(ALL_TOP_MINUTES_LIGTHS_OFF, berlinTimeConverter.getTopMinutesLight(3));
		assertEquals(ALL_TOP_MINUTES_LIGTHS_OFF, berlinTimeConverter.getTopMinutesLight(0));
		assertNotEquals(ALL_TOP_MINUTES_LIGTHS_OFF, berlinTimeConverter.getTopMinutesLight(5));
	}

	@Test
	public void bottomMinutesLightShouldNotLightIfGivenMinutesAreLessThan1() {
		assertEquals(ALL_LIGTHS_OFF, berlinTimeConverter.getBottomMinutesLight(0));
		assertNotEquals(ALL_LIGTHS_OFF, berlinTimeConverter.getBottomMinutesLight(4));
	}

	@Test
	public void topHoursShouldLightRedLampForEvery5Hours() {
		assertEquals("ROOO", berlinTimeConverter.getTopHoursLight(8));
		assertEquals("RROO", berlinTimeConverter.getTopHoursLight(12));
		assertEquals("RRRO", berlinTimeConverter.getTopHoursLight(15));
		assertEquals("RRRR", berlinTimeConverter.getTopHoursLight(20));
		assertEquals("RRRR", berlinTimeConverter.getTopHoursLight(22));
	}

	@Test
	public void bottomHoursShouldLightRedLampForEvery1Hour() {
		assertEquals("ROOO", berlinTimeConverter.getBottomHoursLight(1));
		assertEquals("RROO", berlinTimeConverter.getBottomHoursLight(2));
		assertEquals("RRRO", berlinTimeConverter.getBottomHoursLight(3));
		assertEquals("RRRR", berlinTimeConverter.getBottomHoursLight(4));
	}

	@Test
	public void topMinutesShouldLightYellowLampForEvery5Minutes() {
		assertEquals("YOOOOOOOOOO", berlinTimeConverter.getTopMinutesLight(9));
		assertEquals("YYRYYOOOOOO", berlinTimeConverter.getTopMinutesLight(25));
		assertEquals("YYRYYRYYROO", berlinTimeConverter.getTopMinutesLight(47));
	}

	@Test
	public void bottomMinutesShouldLightYellowLampForEvery5Minutes() {
		assertEquals("YOOO", berlinTimeConverter.getBottomMinutesLight(1));
		assertEquals("YYOO", berlinTimeConverter.getBottomMinutesLight(2));
		assertEquals("YYYO", berlinTimeConverter.getBottomMinutesLight(3));
		assertEquals("YYYY", berlinTimeConverter.getBottomMinutesLight(4));
	}

	@Test
	public void berlinTimeConverterShouldProvideCorrectResult() {
		StringBuilder sb = new StringBuilder();
		sb.append("O").append("\r\n").append("RRRR").append("\r\n").append("RRRO").append("\r\n").append("YYROOOOOOOO")
				.append("\r\n").append("YYOO");

		assertEquals(sb.toString(), berlinTimeConverter.convertTime("23:17:49"));
	}
}