package net.comecraft.comechat.channel;

import java.util.Set;

import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.google.common.collect.ImmutableSet;

import net.comecraft.comechat.format.ChatFormatTemplate;

/**
 * ChannelController represents a set of channels that can be reached by a
 * command or alias. When a user tries to speak in a specified channel, for example
 * by using /g for global chat or /f for faction chat, the ChannelController checks
 * their permissions, location, faction, world, etc. and sends them to the correct
 * Channel.
 * <br><br>
 * The ChannelController should listen for events that would result in a channel
 * change. For example, a world-only chat should listen for a world change, and a
 * faction chat should listen for a faction change.
 */
public abstract class ChannelController implements Listener {

	/**
	 * Get the format template for this ChannelController
	 * @return The ChatFormatTemplate used by this ChannelController.
	 */
	public abstract ChatFormatTemplate getTemplate();
	
	/**
	 * Gets the aliases for this channel. Aliases are usually one or two
	 * characters long and abbreviate the channel controller's ID.
	 * @return A set containing the aliases for this ChannelController.
	 */
	public abstract Set<String> getAliases();
	
	/**
	 * Gets the channels controlled by this ChannelController.
	 * @return The set of Channels that is controlled by this ChannelController.
	 */
	public abstract ImmutableSet<Channel> getChannels();
	
	/**
	 * Gets the unique identifier for this channel controller.
	 * @return The unique ID for this channel.
	 */
	public abstract String getId();
	
	public abstract void sendMessage(CommandSender sender, String message);
	
	/**
	 * On player join, put that player into the correct channel.
	 * @param event The PlayerJoinEvent fired by the player joining the server.
	 */
	@EventHandler
	public abstract void onPlayerJoin(PlayerJoinEvent event);
	
	/**
	 * On player leave, remove that player from any channels.
	 * @param event The PlayerQuitEvent fired by the player leaving the server.
	 */
	@EventHandler
	public abstract void onPlayerQuit(PlayerQuitEvent event);
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
	
	/**
	 * Only checks that the IDs of the two objects are equal.
	 * 
	 * @inheritDoc
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		return getId().equals(((ChatFormatTemplate) obj).getId());
	}
}
