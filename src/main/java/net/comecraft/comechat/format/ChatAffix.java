package net.comecraft.comechat.format;

import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.chat.BaseComponent;

/**
 * Represents a single affix or section of a chat message, for example player name,
 * player clan tag, player rank, or whether the player is in jail. Serializes to JSON
 * format.
 */
public interface ChatAffix {

	/**
	 * Parse all placeholders and generate a ready-to-attach BaseComponent.
	 * @param sender The sender to parse this affix for.
	 * @param receiver The receiver to parse this affix for.
	 * @return A fully parsed and formatted BaseComponent.
	 */
	public BaseComponent parse(CommandSender sender, CommandSender receiver);
	
	/**
	 * Gets this ChatAffix's unique identifier.
	 * @return A string that uniquely identifies this ChatAffix.
	 */
	public String getId();
}
