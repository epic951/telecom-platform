package com.epic951.utilities;

public enum TelecomServiceTypes {

	SUBSCRIPTION("subscription"), ALACARTE("Alacarte");

	private final String text;

	private TelecomServiceTypes(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
