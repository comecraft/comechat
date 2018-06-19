package net.comecraft.comechat.message;

import net.md_5.bungee.api.chat.ComponentBuilder;

/**
 * Represents a chat message that is formatted using bungeecord's chat component
 * api.
 */
public interface ComponentMessage extends ChatMessage {
	
	/**
	 * Get the ComponentBuilder for this message.
	 * @return the ComponentBuilder for this message.
	 */
	public ComponentBuilder getBuilder();
	
}
