package net.comecraft.comechat.message;

import org.bukkit.entity.Player;

/**
 * A MessageReceiver that is a player on a server.
 */
public abstract class PlayerReceiver implements MessageReceiver {
	
	/**
	 * Gets the player represented by this PlayerReceiver
	 * @return the player.
	 */
	public abstract Player player();
	
	@Override
	public void receive(ChatMessage message) {
		player().spigot().sendMessage(message.asBaseComponent());
	}
}
