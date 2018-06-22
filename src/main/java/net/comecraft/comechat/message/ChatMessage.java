package net.comecraft.comechat.message;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.chat.BaseComponent;

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
	public MessageReceiver getReceiver();
	
	/**
	 * Gets the content of this message as a BaseComponent.
	 * @return The content of this message.
	 */
	public BaseComponent asBaseComponent();
	
	/**
	 * Send the message.
	 */
	public default void send() {
		getReceiver().receive(this);
	}
}
