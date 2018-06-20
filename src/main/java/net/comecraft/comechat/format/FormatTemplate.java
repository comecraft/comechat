package net.comecraft.comechat.format;

import java.util.List;

import org.bukkit.command.CommandSender;

import net.comecraft.comechat.message.MessageSupplier;

/**
 * Represents a template that organizes ChatAffixes in a ChatMessage.
 */
public interface FormatTemplate {

	/**
	 * Given a raw message string, gets a supplier that appropriately formats the
	 * raw message..
	 * 
	 * @param rawMessage
	 *            The message to format.
	 * @param sender
	 *            The sender of the message
	 * @return A MessageSupplier that formats the raw message.
	 */
	public MessageSupplier getSupplier(CommandSender sender, String rawMessage);

	/**
	 * Gets the list of affixes that make up this template.
	 * 
	 * @return A list of ChatAffixes.
	 */
	public List<ChatAffix> getAffixes();

	/**
	 * Gets the unique identifier for this template.
	 * 
	 * @return The ID for this template.
	 */
	public String getId();
}
