package net.comecraft.comechat.channel;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

/**
 * Represents the collection of all channels. Keeps track
 * of senders' active channel. Manages channel aliases and makes sure
 * there are no conflicts.
 */
public final class ChannelController {
	
	private final Map<Command, Channel> channels;
	private final Map<CommandSender, Channel> activeChannels;
	private static ChannelController channelController;
	
	public ChannelController() {
		channels = new HashMap<Command, Channel>();
		activeChannels = new HashMap<CommandSender, Channel>();
		channelController = this;
		// TODO get the config and add controllers from config
	}
	
	/**
	 * Gets the current channelcontroller instance.
	 * @return The current ChannelController instance.
	 */
	public static final ChannelController get() {
		return channelController;
	}
	
	/**
	 * Gets the set of channels.
	 * @return A set containing all registered channels.
	 */
	public ImmutableSet<Channel> getControllers() {
		return ImmutableSet.copyOf(channels.values());
	}
	
	/**
	 * Register a new Channel in the master list.
	 * @param channel The channel to be registered.
	 * @param command The command to register the channel for.
	 * @return <tt>false</tt> if this command already mapped to a channel.
	 * @throws IllegalArgumentException If adding the channel would cause an alias conflict.
	 */
	public Optional<Channel> registerChannel(Command command, Channel channel) throws IllegalArgumentException {
		// TODO check for alias conflicts
		/* TODO
		 * Register new aliases as commands, remove old aliases.
		 * This will probably require a server restart, modifying the
		 * plugin.yml, or using reflection to obtain the server's commandmap.
		 */
		return null;
	}
	
	/**
	 * Set a sender's active channel. Chat inputs that don't specify channel will go to this channel.
	 * @param sender The sender to change the active channel for.
	 * @param channel The new active channel.
	 * @return <tt>false</tt> if the sender's active channel did not change.
	 */
	public boolean setActiveChannel(CommandSender sender, Channel channel) {
		// TODO
		return true;
	}
	
	/**
	 * Gets a sender's active channel.
	 * @param sender The sender to get the active channel for.
	 * @return The sender's active channel.
	 */
	public Channel getActiveChannel(CommandSender sender) {
		return activeChannels.get(sender);
	}
	
	
}
