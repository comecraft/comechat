package net.comecraft.comechat.format;

import java.util.Optional;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import net.comecraft.comechat.message.MessageReceiver;
import net.comecraft.comechat.message.PlayerReceiver;
import net.comecraft.comechat.placeholder.Placeholders;
import net.md_5.bungee.api.chat.BaseComponent;

/**
 * Represents a single affix or section of a chat message, for example player name,
 * player clan tag, player rank, or whether the player is in jail. Serializes to JSON
 * format.
 */
public abstract class ChatAffix {
	
	@Expose private String id;
	@Expose private boolean includeInLogs;
	
	@Expose private JsonObject content;
	@Expose private String loggerContent;
	

	/**
	 * Parse all placeholders and generate a ready-to-attach BaseComponent.
	 * @param sender The sender to parse this affix for.
	 * @param receiver The receiver to parse this affix for.
	 * @return A fully parsed and formatted BaseComponent.
	 */
	public Optional<BaseComponent> parse(CommandSender sender, PlayerReceiver receiver) {
		
		if (receiver instanceof PlayerReceiver) {
			return Optional.of(Placeholders.parse(content, (Player) sender, receiver.player()));
		}
		
		return Optional.empty();
	}
	
	/**
	 * Gets this ChatAffix's unique identifier.
	 * @return A string that uniquely identifies this ChatAffix.
	 */
	public abstract String getId();
}
