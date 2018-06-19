package net.comecraft.comechat.message;

import org.bukkit.command.CommandSender;

/**
 * Represents a single chat message between two entities.
 */
public interface ChatMessage {
	
	/**
	 * Gets the sender of this message.
	 * @return The sender of this message.
	 */
	public CommandSender getSender();
	
	/**
	 * Gets the receiver of this message.
	 * @return The receiver of this message.
	 */
	public CommandSender getReceiver();
	
	/**
	 * Send the message.
	 */
	public void send();
}
