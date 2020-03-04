package ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("messages");

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
