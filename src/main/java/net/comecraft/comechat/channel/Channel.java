package net.comecraft.comechat.channel;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.ImmutableSet;

import net.comecraft.comechat.config.ChannelConfiguration;
import net.comecraft.comechat.format.FormatTemplate;
import net.comecraft.comechat.message.MessagePipe;
import net.comecraft.comechat.message.MessageSupplier;
import net.comecraft.comechat.message.PlayerReceiver;

/**
 * Channel represents a set of message pipes that can be reached by a command or
 * alias. When a user tries to speak in a specified channel, for example by
 * using /g for global chat or /f for faction chat, the Channel checks their
 * permissions, location, faction, world, etc. and sends them to the correct
 * pipe.
 */
public abstract class Channel implements CommandExecutor {

	private final ChannelConfiguration config;
	
	/**
	 * Creates a new channel from a ChannelConfiguration.
	 * @param config The ChannelConfiguration to create this channel from.
	 */
	Channel(ChannelConfiguration config) {
		this.config = config;
		this.silenced = new HashSet<CommandSender>();
		this.deafened = new HashSet<CommandSender>();
	}
	
	/**
	 * Gets the unique identifier for this channel.
	 * 
	 * @return The unique ID for this channel.
	 */
	public String getId() {
		return config.channelId;
	}

	/**
	 * Gets the aliases for this channel. Aliases are usually one or two characters
	 * long and abbreviate the channel's ID. If you need to modify a Channel's
	 * aliases, do so through Config, so that listeners and command aliases can be
	 * updated.
	 * 
	 * @return A set containing the aliases for this Channel.
	 */
	public ImmutableSet<String> getAliases() {
		return config.channelAliases;
	}

	/**
	 * Gets the outgoing pipe for a particular sender in this channel.
	 * @param sender The sender to get the outgoing pipe for.
	 * @return A MessagePipe for outgoing messages from a particular sender.
	 */
	public abstract MessagePipe outgoingPipe(CommandSender sender);

	/**
	 * Gets the format template for this Channel
	 * 
	 * @return The ChatFormatTemplate used by this Channel.
	 */
	public FormatTemplate getTemplate() {
		return config.format;
	}

	/**
	 * Gets the permission required to read messages in this channel.
	 * 
	 * @return The Permission required to read messages in this channel.
	 */
	public String getReadPerm() {
		return config.readPermission;
	}

	/**
	 * Gets the permission required to write messages in to channel.
	 * 
	 * @return The Permission required to write messages in to channel.
	 */
	public String getWritePerm() {
		return config.writePermission;
	}

	/**
	 * Prevents a sender from sending messages to this channel. Silencing is
	 * different from lack of write permission.
	 * 
	 * @param mutee
	 *            The sender to silence.
	 * @return <tt>true</tt> if the sender was not already silenced.
	 */
	public boolean silence(CommandSender mutee) {
		return getSilenced().add(mutee);
	}

	/**
	 * Unsilences a sender. Silencing is different from lack of write permission.
	 * 
	 * @param mutee
	 *            The sender to unsilence.
	 * @return <tt>true</tt> if the sender was previously silenced.
	 */
	public boolean unSilence(CommandSender mutee) {
		return getSilenced().remove(mutee);
	}

	/**
	 * Checks whether a sender is silenced. Silencing is different from lack of
	 * write permission.
	 * 
	 * @param mutee
	 *            The sender to check.
	 * @return <tt>true</tt> if the sender is silenced.
	 */
	public boolean isSilenced(CommandSender mutee) {
		return getSilenced().contains(mutee);
	}

	/**
	 * Gets the senders who are silenced from this channel. Silencing is different
	 * from lack of write permission.
	 * 
	 * @return A set containing this channel's silenced senders.
	 */
	public Set<CommandSender> getSilenced() {
		return silenced;
	}
	
	private final Set<CommandSender> silenced;

	/**
	 * Prevents a sender from viewing messages sent to this channel. Deafening is
	 * different from lack of read permission.
	 * 
	 * @param muter
	 *            The sender to deafen.
	 * @return <tt>true</tt> if the sender was not already deafened.
	 */
	public boolean deafen(CommandSender muter) {
		return getDeafened().add(muter);
	}

	/**
	 * Undeafens a sender. Deafening is different from lack of read permission.
	 * 
	 * @param muter
	 *            The sender to undeafen.
	 * @return <tt>true</tt> if the sender was previously deafened.
	 */
	public boolean unDeafen(CommandSender muter) {
		return getDeafened().remove(muter);
	}

	/**
	 * Checks whether a sender is deafened. Deafening is different from lack of read
	 * permission.
	 * 
	 * @param muter
	 *            The sender to check.
	 * @return <tt>true</tt> if the sender is deafened.
	 */
	public boolean isDeafened(CommandSender muter) {
		return getDeafened().contains(muter);
	}

	/**
	 * Gets the senders who are deafened from this channel. Deafening is different
	 * from lack of read permission.
	 * 
	 * @return A set containing this channel's deafened senders.
	 */
	public Set<CommandSender> getDeafened() {
		return deafened;
	}
	
	private final Set<CommandSender> deafened;

	/**
	 * Sends a message to the appropriate receivers.
	 * 
	 * @param sender
	 *            The sender of the message.
	 * @param message
	 *            The raw input text of the message.
	 */
	public void sendMessage(CommandSender sender, String message) {
		
		if (isSilenced(sender)) {
			// TODO notify the sender that they are silenced.
			return;
		}

		// Get the message supplier from the format template
		MessageSupplier supplier = getTemplate().getSupplier(sender, message);

		outgoingPipe(sender)

				// Filter receivers that should receive the message
				.filter(r -> {
					
					// Filter out players
					if (r instanceof PlayerReceiver) {
						Player p = ((PlayerReceiver) r).player();
						
						// Filter out players without read permission.
						if (!p.hasPermission(getReadPerm())) return false;
						
						// Filter out players who have this channel deafended.
						if (isDeafened(p)) return false;
					}
					
					return true;
				})

				// Send the message.
				.sendMessage(supplier);
	}

	/**
	 * If no chat message is included in the arguments, try to change this to the
	 * sender's active channel. If a chat message is included in the arguments, try
	 * to send that message.
	 * @param sender The sender of the command
	 * @param command The command which was sent
	 * @param label The command alias used to send the command
	 * @param message The message content of the command
	 * @return <tt>true</tt> if the usage of the command was correct.
	 */
	public boolean onCommand(CommandSender sender, Command command, String label, String message) {

		//Check sender write permission
		if (!sender.hasPermission(getWritePerm())) {
			// TODO notify sender they don't have permission
			return true;
		}

		// TODO If the sender has this channel deafened, undeafen it.
		// TODO If the sender is silenced from this channel, notify them.

		// No additional arguments result in a channel change.
		if (message.length() == 0) {
			// TODO Change active channel of sender
			// TODO Notify sender that they have changed channels
			// TODO Add channel change message to lang file
			// TODO Fire channel change event
			return true;
		}

		// Send the message.
		sendMessage(sender, message);
		return true;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return onCommand(sender, command, label, String.join(" ", args));
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	/**
	 * Only checks that the IDs of the two objects are equal.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return getId().equals(((FormatTemplate) obj).getId());
	}
}
