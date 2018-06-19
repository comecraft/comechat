package net.comecraft.comechat.message;

import org.bukkit.command.CommandSender;

public interface ChatFormat {
	
	/**
	 * Builds a ChatMessage.
	 * @param message The original message sent by the CommandSender.
	 * @param sender The sender of the message.
	 * @param receiver The receiver of hte message.
	 * @return A fully formatted ChatMessage.
	 */
	public ChatMessage format(String message, CommandSender sender, CommandSender receiver);
}
