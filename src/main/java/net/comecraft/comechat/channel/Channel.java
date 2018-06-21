package net.comecraft.comechat.channel;

import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import com.google.common.collect.ImmutableSet;

import net.comecraft.comechat.format.FormatTemplate;
import net.comecraft.comechat.message.MessagePipe;
import net.comecraft.comechat.message.MessageSupplier;

/**
 * Channel represents a set of message pipes that can be reached by a command or
 * alias. When a user tries to speak in a specified channel, for example by
 * using /g for global chat or /f for faction chat, the Channel checks their
 * permissions, location, faction, world, etc. and sends them to the correct
 * pipe.
 */
public abstract class Channel implements CommandExecutor {

	/**
	 * Gets the unique identifier for this channel.
	 * 
	 * @return The unique ID for this channel.
	 */
	public abstract String getId();

	/**
	 * Gets the aliases for this channel. Aliases are usually one or two characters
	 * long and abbreviate the channel's ID. If you need to modify a Channel's
	 * aliases, do so through Config, so that listeners and command aliases can be
	 * updated.
	 * 
	 * @return A set containing the aliases for this Channel.
	 */
	public abstract ImmutableSet<String> getAliases();

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
	public abstract FormatTemplate getTemplate();

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
	 * Prevents a sender from sending messages to this channel. Silencing is
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
	 * @return A set containing this channel's silenced senders.
	 */
	public abstract Set<CommandSender> getSilenced();

	/**
	 * Prevents a sender from viewing messages sent to this channel. Deafening is
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
	 * @return A set containing this channel's deafened senders.
	 */
	public abstract Set<CommandSender> getDeafened();

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
		
		MessageSupplier supplier = getTemplate().getSupplier(sender, message);
		outgoingPipe(sender).sendMessage(supplier);
	}

	/**
	 * If no chat message is included in the arguments, try to change this to the
	 * sender's active channel. If a chat message is included in the arguments, try
	 * to send that message.
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		//Check sender write permission
		if (!sender.hasPermission(getWritePerm())) {
			// TODO notify sender they don't have permission
			return true;
		}

		// TODO If the sender has this channel deafened, undeafen it.
		// TODO If the sender is silenced from this channel, notify them.

		// No additional arguments result in a channel change.
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
		return getId().equals(((FormatTemplate) obj).getId());
	}
}
