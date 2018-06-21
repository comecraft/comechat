package net.comecraft.comechat.message;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.bukkit.command.CommandSender;

/**
 * Represents a stream of Messages that can be read or posted to.
 */
public interface MessagePipe {

	/**
	 * Gets the stream of receivers for this pipe.
	 * 
	 * @return The stream of receivers for this pipe.
	 */
	public Stream<CommandSender> receivers();

	/**
	 * Sends a message to all receivers of the pipe.
	 * 
	 * @param messageSupplier
	 *            The supplier to provide messages to the pipe.
	 */
	public default void sendMessage(MessageSupplier supplier) {
		receivers().map(supplier::get) // Get a ChatMessage for each receiver.
				.forEach(ChatMessage::send); // Send each message.
	}

	/**
	 * Intersect this message pipe with another pipe. The intersected pipe will only
	 * send messages to receivers of both pipes.
	 * 
	 * @param otherPipe
	 *            The pipe to intersect this with.
	 * @return A MessagePipe that only sends messages to receivers of this and the
	 *         other pipe.
	 */
	public default MessagePipe intersect(MessagePipe otherPipe) {
		return new MessagePipe() {

			@Override
			public Stream<CommandSender> receivers() {
				return MessagePipe.this.receivers().filter(s -> {
					// Check if this receiver is also present in the other pipe.
					return otherPipe.receivers().anyMatch(s::equals);
				});
			}
		};
	}

	/**
	 * Union this MessagePipe with another MessagePipe. The unioned pipe will send
	 * messages to receivers of either pipe.
	 * 
	 * @param otherPipe
	 *            The pipe to union with this one.
	 * @return A MessagePipe that sends messages to receivers of this pipe and
	 *         receivers of the other pipe.
	 */
	public default MessagePipe union(MessagePipe otherPipe) {
		return new MessagePipe() {

			@Override
			public Stream<CommandSender> receivers() {
				// Get all distinct elements of the addition of both pipes.
				return Stream.concat(MessagePipe.this.receivers(), otherPipe.receivers()).distinct();
			}
		};
	}

	/**
	 * Returns a MessagePipe that will send to receivers of this pipe that match the
	 * given predicate.
	 * 
	 * @param predicate
	 *            A predicate to apply to each receiver to determine if they should
	 *            be included.
	 * @return The new pipe.
	 */
	public default MessagePipe filter(Predicate<? super CommandSender> predicate) {
		return new MessagePipe() {

			@Override
			public Stream<CommandSender> receivers() {
				return MessagePipe.this.receivers().filter(predicate);
			}
		};
	}
}
