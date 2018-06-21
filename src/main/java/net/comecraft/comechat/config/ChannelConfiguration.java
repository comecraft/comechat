package net.comecraft.comechat.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;

import net.comecraft.comechat.format.FormatTemplate;

public class ChannelConfiguration {

	private static final String pluginName = "comechat"; // TODO pull this out

	private static final File defaultConfig = null; // TODO add default config

	// Identifiers
	@Expose public String channelType;
	@Expose public String channelId;
	@Expose public String[] channelAliases;

	// Permissions
	@Expose public String readPermission;
	@Expose public String writePermission;

	@Expose public FormatTemplate format;

	// TODO add save config method
	
	/**
	 * Generates a ChannelConfig from a .json file. If the specified file does not
	 * exist, it is created and populated with default values.
	 * 
	 * @param configFile
	 *            The configuration file.
	 * @return A ChannelConfig generated from this file.
	 * @throws IOException
	 *             If there was an error reading or parsing the file.
	 */
	public static ChannelConfiguration getConfig(File configFile) throws IOException {

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();
		String message;
		Logger logger = Bukkit.getLogger();

		try {

			// Try to parse config file
			ChannelConfiguration config = gson.fromJson(new FileReader(configFile), ChannelConfiguration.class);

			// Log success
			message = String.format("[%s] Successfully loaded %s", pluginName, configFile.getName());
			logger.info(message);

			return config;
		}

		// Json is malformed
		catch (JsonSyntaxException e) {

			// Log
			message = String.format("[%s] Invalid Json syntax in %s. Disabling %s.", pluginName, configFile.getName(),
					pluginName);
			logger.severe(message);

			// Kill the plugin
			throw new IOException(e);
		}

		// Json reader fail
		catch (JsonIOException e) {

			// Log
			message = String.format("[%s] Could not read %s. Disabling %s.", pluginName, configFile.getName(),
					pluginName);
			logger.severe(message);

			// Kill the plugin
			throw new IOException(e);
		}

		// No config file exists.
		catch (FileNotFoundException e) {

			// Log
			message = String.format("[%s] File %s not found. Using default configuration.", pluginName,
					configFile.getName());
			logger.info(message);

			// Use default config
			return getConfig(defaultConfig);
		}
	}
}
