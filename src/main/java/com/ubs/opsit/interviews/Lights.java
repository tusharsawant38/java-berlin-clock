package com.ubs.opsit.interviews;

public enum Lights {

	OFF_LIGHT("O"), RED_LIGHT("R"), YELLOW_LIGHT("Y");

	private String light;

	Lights(String light) {
		this.light = light;
	}

	public String getLight() {
		return light;
	}
}
