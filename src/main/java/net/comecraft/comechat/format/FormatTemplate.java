package net.comecraft.comechat.format;

import org.bukkit.command.CommandSender;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import net.comecraft.comechat.message.ChatMessage;
import net.comecraft.comechat.message.MessageReceiver;
import net.comecraft.comechat.message.MessageSupplier;
import net.comecraft.comechat.placeholder.Placeholders;

/**
 * Represents a template that organizes ChatAffixes in a ChatMessage.
 */
public class FormatTemplate {

	@Expose private String id;
	@Expose private boolean includeInLogs;
	
	@Expose private JsonObject content;
	@Expose private String loggerContent;
	
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
	public MessageSupplier getSupplier(CommandSender sender, String rawMessage) {
		
		// Parse placeholders for the sender once
		JsonObject senderParsedContent = Placeholders.parseSender(content, sender);
		return new MessageSupplier() {

			@Override
			public ChatMessage get(MessageReceiver receiver) {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	/**
	 * Gets the unique identifier for this template.
	 * 
	 * @return The ID for this template.
	 */
	public String getId() {
		return id;
	}
}
