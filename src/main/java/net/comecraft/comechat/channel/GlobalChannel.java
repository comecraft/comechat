package net.comecraft.comechat.channel;

import org.bukkit.command.CommandSender;

import net.comecraft.comechat.config.ChannelConfiguration;
import net.comecraft.comechat.message.GlobalMessagePipe;
import net.comecraft.comechat.message.MessagePipe;

/**
 * A global chat channel where all players on the server will receive messages.
 */
public class GlobalChannel extends Channel {

	/**
	 * @see Channel#Channel()
	 */
	GlobalChannel(ChannelConfiguration config) {
		super(config);
	}

	@Override
	public MessagePipe outgoingPipe(CommandSender sender) {
		return GlobalMessagePipe.get();
	}

}
