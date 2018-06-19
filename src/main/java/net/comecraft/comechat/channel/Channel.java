package net.comecraft.comechat.channel;

import java.util.Set;

import org.bukkit.command.CommandSender;

import net.comecraft.comechat.message.ChatFormat;
import net.comecraft.comechat.message.ChatMessage;

/**
 * Represents a stream of Messages that can be read or posted to.
 */
public interface Channel {

	/**
	 * Gets a set containing all CommandSenders who will be shown messages
	 * from this channel.
	 * @return A set containing the members of this Channel.
	 */
	public Set<CommandSender> getMembers();
	
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
	 * @param sender The sender of the message.
	 * @param message The message to send.
	 */
	public void sendMessage(CommandSender sender, ChatMessage message);
	
	/**
	 * Get the formatter used to format messages in this Channel.
	 * @return The ChatFormat used by this Channel.
	 */
	public ChatFormat getFormat();
}
