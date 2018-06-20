package net.comecraft.comechat.message;

import org.bukkit.command.CommandSender;

/**
 * Represents a supplier of chat messages.
 */
public interface MessageSupplier {
	
	/**
	 * Gets a message.
	 * @param receiver The receiver of the message.
	 * @return A message for that receiver.
	 */
	public ChatMessage get(CommandSender receiver);
}
