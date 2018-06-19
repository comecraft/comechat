package net.comecraft.comechat.format;

import org.bukkit.command.CommandSender;

import net.comecraft.comechat.message.ChatMessage;

/**
 * Represents a chat message factory tailored to a single message receiver.
 */
public interface ChatFormatter {
	
	/**
	 * Gets the template used by this formatter to format messages.
	 * @return The template used by this formatter to format messages.
	 */
	public ChatFormatTemplate getTemplate();
	
	/**
	 * Gets the message receiver that this formatter tailors messages to.
	 * @return The receiver of messages from this Formatter.
	 */
	public CommandSender getReceiver();
	
	/**
	 * Builds a ChatMessage.
	 * @param message The original message sent by the CommandSender.
	 * @param sender The sender of the message.
	 * @return A fully formatted ChatMessage.
	 */
	public ChatMessage format(String message, CommandSender sender);
}
