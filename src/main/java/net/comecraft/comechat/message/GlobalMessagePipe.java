package net.comecraft.comechat.message;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * GlobalMessagePipe pipes messages to all online players, as well as the
 * plugin's chat logger.
 */
public final class GlobalMessagePipe extends MessagePipe {

	private static GlobalMessagePipe globalPipe = null;
	
	/**
	 * Gets the global message pipe.
	 * @return The global message pipe.
	 */
	public static GlobalMessagePipe get() {
		if (globalPipe == null) globalPipe = new GlobalMessagePipe();
		return globalPipe;
	}
	
	/**
	 * You are not allowed to construct a GlobalMessagePipe. There is only one
	 * GlobalMessagePipe. Instead use get().
	 */
	private GlobalMessagePipe() {
	}
	
	/**
	 * Gets a pipe containing all online players and comechat's chat logger.
	 */
	public Stream<MessageReceiver> receivers() {
		return Bukkit.getServer().getOnlinePlayers().stream()
				
				// Get a MessageReceiver for each player.
				.map(p -> new PlayerReceiver() {

					@Override
					public Player player() {
						return p;
					}	
				});

		// TODO add chat logger to this pipe
	}

}
