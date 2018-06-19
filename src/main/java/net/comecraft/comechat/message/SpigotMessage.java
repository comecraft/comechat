package net.comecraft.comechat.message;

import org.bukkit.command.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;

/**
 * A ComponentMessage that sends through Spigot.
 */
public class SpigotMessage implements ComponentMessage {

	private final CommandSender sender;
	private final CommandSender receiver;
	private final ComponentBuilder builder;

	
	
	public SpigotMessage(CommandSender sender, CommandSender receiver, ComponentBuilder builder) {
		this.sender = sender;
		this.receiver = receiver;
		this.builder = builder;
	}

	@Override
	public CommandSender getSender() {
		return sender;
	}

	@Override
	public CommandSender getReceiver() {
		return receiver;
	}
	
	@Override
	public ComponentBuilder getBuilder() {
		return builder;
	}
	
	@Override
	public void send() {
		receiver.spigot().sendMessage(builder.create());
	}

	

}
