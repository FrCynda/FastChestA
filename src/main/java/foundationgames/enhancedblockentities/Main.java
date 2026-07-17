package foundationgames.enhancedblockentities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

	public static final String MOD_ID = "enhancedblockentities";
	public static final String MOD_VERSION = "0.13.1";
	public static final String MOD_FRIENDLY_NAME = "Enhanced Block Entities Reloaded";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	public static void onInitialize() {
		LOGGER.info("Initializing {}", MOD_ID);
		LOGGER.info("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}
}
