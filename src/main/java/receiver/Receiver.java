package receiver;

import net.comecraft.comechat.message.ChatMessage;

/**
 * Represents any entity that could receive a message.
 */
public interface Receiver {

	/**
	 * Receive a message. The default implementation should work for spigot players.
	 */
	public default void receive(ChatMessage message) {
		
	}
}
