package net.comecraft.comechat.channel;

import org.bukkit.command.CommandSender;

import com.google.common.collect.ImmutableSet;

import net.comecraft.comechat.format.ChatFormatTemplate;

/**
 * Represents a stream of Messages that can be read or posted to.
 */
public interface MessagePipe {

	/**
	 * Gets a set containing all CommandSenders who will be shown messages
	 * from this channel. The set should be immutable. If you want to add or
	 * remove viewers from this pipe use addViewer() or removeViewer() respectively.
	 * @return A set containing the viewers of this pipe.
	 */
	public ImmutableSet<CommandSender> getViewers();
	
	/**
	 * Removes a viewer from the pipe if it is present.
	 * @param viewer The viewer to remove from the pipe
	 * @return <tt>true</tt> if successful.
	 */
	public boolean removeViewer(CommandSender viewer);
	
	/**
	 * Adds a viewer to the pipe.
	 * @param viewer The viewer to add.
	 * @return <tt>true</tt> if the viewer was not already present.
	 */
	public boolean addViewer(CommandSender viewer);
	
	/**
	 * Sends a message to all viewers of the pipe.
	 * Should pipe the message text to all chat formatters and send the message
	 * to each receiver.
	 * @param sender The sender of the message.
	 * @param message The message to send.
	 */
	public void sendMessage(CommandSender sender, String message);
	
	/**
	 * Get the formatting template used to format messages in this pipe.
	 * @return The ChatFormat used by this pipe.
	 */
	public ChatFormatTemplate getFormat();
}
