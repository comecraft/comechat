package net.comecraft.comechat.channel;

import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.Permission;

import com.google.common.collect.ImmutableSet;

import net.comecraft.comechat.format.ChatFormatTemplate;

/**
 * ChannelController represents a set of channels that can be reached by a
 * command or alias. When a user tries to speak in a specified channel, for
 * example by using /g for global chat or /f for faction chat, the
 * ChannelController checks their permissions, location, faction, world, etc.
 * and sends them to the correct Channel. <br>
 * <br>
 * The ChannelController should listen for events that would result in a channel
 * change. For example, a world-only chat should listen for a world change, and
 * a faction chat should listen for a faction change.
 */
public abstract class ChannelController implements CommandExecutor, Listener {

	/**
	 * Gets the unique identifier for this channel controller.
	 * 
	 * @return The unique ID for this channel.
	 */
	public abstract String getId();

	/**
	 * Gets the aliases for this channel. Aliases are usually one or two characters
	 * long and abbreviate the channel controller's ID. If you need to modify a
	 * ChannelController's aliases, do so through Config, so that listeners and
	 * command aliases can be updated.
	 * 
	 * @return A set containing the aliases for this ChannelController.
	 */
	public abstract ImmutableSet<String> getAliases();

	/**
	 * Gets the channels controlled by this ChannelController.
	 * 
	 * @return The set of Channels that is controlled by this ChannelController.
	 */
	public abstract ImmutableSet<Channel> getChannels();

	/**
	 * Get the format template for this ChannelController
	 * 
	 * @return The ChatFormatTemplate used by this ChannelController.
	 */
	public abstract ChatFormatTemplate getTemplate();

	/**
	 * Gets the permission required to read messages in this channel.
	 * 
	 * @return The Permission required to read messages in this channel.
	 */
	public abstract Permission getReadPerm();

	/**
	 * Gets the permission required to write messages in to channel.
	 * 
	 * @return The Permission required to write messages in to channel.
	 */
	public abstract Permission getWritePerm();

	/**
	 * Prevents a sender from sending messages to this controller. Silencing is
	 * different from lack of write permission.
	 * 
	 * @param mutee
	 *            The sender to silence.
	 * @return <tt>true</tt> if the sender was not already silenced.
	 */
	public abstract boolean silence(CommandSender mutee);

	/**
	 * Unsilences a sender. Silencing is different from lack of write permission.
	 * 
	 * @param mutee
	 *            The sender to unsilence.
	 * @return <tt>true</tt> if the sender was previously silenced.
	 */
	public abstract boolean unSilence(CommandSender mutee);

	/**
	 * Checks whether a sender is silenced. Silencing is different from lack of
	 * write permission.
	 * 
	 * @param mutee
	 *            The sender to check.
	 * @return <tt>true</tt> if the sender is silenced.
	 */
	public abstract boolean isSilenced(CommandSender mutee);

	/**
	 * Gets the senders who are silenced from this channel. Silencing is different
	 * from lack of write permission.
	 * 
	 * @return A set containing this controller's silenced senders.
	 */
	public abstract Set<CommandSender> getSilenced();

	/**
	 * Prevents a sender from viewing messages sent to this controller. Deafening is
	 * different from lack of read permission.
	 * 
	 * @param muter
	 *            The sender to deafen.
	 * @return <tt>true</tt> if the sender was not already deafened.
	 */
	public abstract boolean deafen(CommandSender muter);

	/**
	 * Undeafens a sender. Deafening is different from lack of read permission.
	 * 
	 * @param muter
	 *            The sender to undeafen.
	 * @return <tt>true</tt> if the sender was previously deafened.
	 */
	public abstract boolean unDeafen(CommandSender muter);

	/**
	 * Checks whether a sender is deafened. Deafening is different from lack of read
	 * permission.
	 * 
	 * @param mutee
	 *            The sender to check.
	 * @return <tt>true</tt> if the sender is deafened.
	 */
	public abstract boolean isDeafened(CommandSender muter);

	/**
	 * Gets the senders who are deafened from this channel. Deafening is different
	 * from lack of read permission.
	 * 
	 * @return A set containing this controller's deafened senders.
	 */
	public abstract Set<CommandSender> getDeafened();

	/**
	 * Sends a message to the appropriate channel.
	 * 
	 * @param sender
	 *            The sender of the message.
	 * @param message
	 *            The raw input text of the message.
	 */
	public abstract void sendMessage(CommandSender sender, String message);

	/**
 	 * If no chat message is included in the arguments, try to change
	 * this to the sender's active channel. If a chat message is included in the
	 * arguments, try to send that message.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Check sender perms

		// TODO If the sender has this channel deafened, undeafen it.
		// TODO If the sender is silenced from this channel, notify them.

		// Channel change
		if (args.length == 0) {
			// TODO Change active channel of sender
			// TODO Notify sender that they have changed channels
			// TODO Add channel change message to lang file
			// TODO Fire channel change event
			return true;
		}

		// Send the message.
		sendMessage(sender, String.join(" ", args));
		return true;
	}

	/**
	 * On player join, put that player into the correct channel.
	 * 
	 * @param event
	 *            The PlayerJoinEvent fired by the player joining the server.
	 */
	@EventHandler
	public abstract void onPlayerJoin(PlayerJoinEvent event);

	/**
	 * On player leave, remove that player from any channels.
	 * 
	 * @param event
	 *            The PlayerQuitEvent fired by the player leaving the server.
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
		if (obj == null)
			return false;
		return getId().equals(((ChatFormatTemplate) obj).getId());
	}
}
