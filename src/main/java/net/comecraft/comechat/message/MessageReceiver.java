package net.comecraft.comechat.message;

/**
 * Represents any entity that could receive a message.
 */
public interface MessageReceiver {

	/**
	 * Receive a message. The default implementation should work for spigot
	 * players.
	 * 
	 * @param message
	 *            The message to send.
	 */
	public default void receive(ChatMessage message) {

	}
}
