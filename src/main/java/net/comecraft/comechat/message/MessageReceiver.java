package net.comecraft.comechat.message;

/**
 * Represents any entity that could receive a message.
 */
public interface MessageReceiver {

	/**
	 * Receive a message.
	 * 
	 * @param message
	 *            The message to send.
	 */
	public void receive(ChatMessage message);
}
