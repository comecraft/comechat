package net.comecraft.comechat.channel;

import org.bukkit.command.CommandSender;

import com.google.common.collect.ImmutableSet;

import net.comecraft.comechat.format.ChatFormatTemplate;

/**
 * Represents a stream of Messages that can be read or posted to.
 */
public interface Channel {

	/**
	 * Gets a set containing all CommandSenders who will be shown messages
	 * from this channel. The set should be immutable. If you want to add or
	 * remove members from this channel use addMember() or removeMember() respectively.
	 * @return A set containing the members of this Channel.
	 */
	public ImmutableSet<CommandSender> getMembers();
	
	/**
	 * Removes a member from the channel if it is present.
	 * @param member The member to remove from the Channel
	 * @return <tt>true</tt> if successful.
	 */
	public boolean removeMember(CommandSender member);
	
	/**
	 * Adds a member to the channel.
	 * @param member The member to add.
	 * @return <tt>true</tt> if the member was not already present.
	 */
	public boolean addMember(CommandSender member);
	
	/**
	 * Sends a message to all members of the channel.
	 * Should pipe the message text to all chat formatters and send the message
	 * to each receiver.
	 * @param sender The sender of the message.
	 * @param message The message to send.
	 */
	public void sendMessage(CommandSender sender, String message);
	
	/**
	 * Get the formatting template used to format messages in this Channel.
	 * @return The ChatFormat used by this Channel.
	 */
	public ChatFormatTemplate getFormat();
}
