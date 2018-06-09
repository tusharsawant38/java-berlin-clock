package com.ubs.opsit.interviews;

public class BerlinTimeConverter implements TimeConverter {
	private static final int MAX_HOURS_MINUTES_LENGTH = 4;
	private static final int MAX_TOP_MINUTES_LENGTH = 11;

	public String getSecondsLight(int seconds) {
		if (seconds % 2 == 0) {
			return Lights.YELLOW_LIGHT.getLight();
		}
		return Lights.OFF_LIGHT.getLight();
	}

	private String getLightsInStringFormat(final int maxArrLength, final int maxValue, Lights light) {
		char[] result = new char[maxArrLength];
		char lightColor = light.getLight().charAt(0);

		for (int i = 0; i < maxArrLength; i++) {
			result[i] = Lights.OFF_LIGHT.getLight().charAt(0); // keep all light off initially
			if (i < maxValue) { // Turning on light based on condition
				result[i] = lightColor;
			}
		}
		return String.valueOf(result);
	}
	
	private String getTopMinutesLightInStringFormat(final int maxTopMinutesLength, int minutes) {
		char[] result = new char[maxTopMinutesLength];
		int divider = minutes / 5;
		int quaterCount = 0;

		for (int i = 0; i < maxTopMinutesLength; i++) {
			quaterCount = quaterCount + 5;
			result[i] = Lights.OFF_LIGHT.getLight().charAt(0);

			if (i < divider) {
				if (quaterCount % 15 == 0) {
					result[i] = Lights.RED_LIGHT.getLight().charAt(0);
					continue;
				}
				result[i] = Lights.YELLOW_LIGHT.getLight().charAt(0);
			}
		}
		return String.valueOf(result);
	}

	public String getTopHoursLight(int hours) {
		return getLightsInStringFormat(MAX_HOURS_MINUTES_LENGTH, hours / 5, Lights.RED_LIGHT);
	}

	public String getBottomHoursLight(int hours) {
		return getLightsInStringFormat(MAX_HOURS_MINUTES_LENGTH, hours % 5, Lights.RED_LIGHT);
	}
	
	public String getTopMinutesLight(int minutes) {
		return getTopMinutesLightInStringFormat(MAX_TOP_MINUTES_LENGTH, minutes);
	}

	public String getBottomMinutesLight(int minutes) {
		return getLightsInStringFormat(MAX_HOURS_MINUTES_LENGTH, minutes % 5, Lights.YELLOW_LIGHT);
	}

	@Override
	public String convertTime(String aTime) {
		String[] timeArr = aTime.split(":");
		int hours = new Integer(timeArr[0]);
		int minutes = new Integer(timeArr[1]);
		int seconds = new Integer(timeArr[2]);

		StringBuilder sb = new StringBuilder();

		sb.append(getSecondsLight(seconds));
		sb.append("\r\n");

		sb.append(getTopHoursLight(hours));
		sb.append("\r\n");

		sb.append(getBottomHoursLight(hours));
		sb.append("\r\n");

		sb.append(getTopMinutesLight(minutes));
		sb.append("\r\n");

		sb.append(getBottomMinutesLight(minutes));

		return sb.toString();
	}
}
