package net.comecraft.comechat.message;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * GlobalMessagePipe pipes members to all online players, as well as the
 * plugin's chat logger.
 */
public final class GlobalMessagePipe implements MessagePipe {

	private GlobalMessagePipe globalPipe = null;
	
	/**
	 * Gets the global message pipe.
	 * @return The global message pipe.
	 */
	public GlobalMessagePipe get() {
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
	@Override
	public Stream<CommandSender> receivers() {
		return Bukkit.getServer().getOnlinePlayers().stream()

				// Cast <? extends Player> to Player
				.map(p -> (Player) p);

		// TODO add chat logger to this pipe
	}

}
