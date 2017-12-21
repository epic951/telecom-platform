package com.epic951.utilities;

public class JPAUtilities {

	public static String interpretTelecomServiceType(boolean type) {
		if (type) {
			return TelecomServiceTypes.SUBSCRIPTION.toString();
		} else {
			return TelecomServiceTypes.ALACARTE.toString();
		}
	}
}
