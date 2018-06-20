package net.comecraft.comechat.channel;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

/**
 * Represents the collection of all ChannelControllers. Keeps track
 * of senders' active controller. Manages controller aliases and makes sure
 * there are no conflicts.
 */
public final class Controllers {
	
	private final Map<Command, ChannelController> controllers;
	private final Map<CommandSender, ChannelController> activeControllers;
	private static Controllers controllersInstance;
	
	public Controllers() {
		controllers = new HashMap<Command, ChannelController>();
		activeControllers = new HashMap<CommandSender, ChannelController>();
		controllersInstance = this;
		// TODO get the config and add controllers from config
	}
	
	/**
	 * Gets the current controllers instance.
	 * @return The current Controllers instance.
	 */
	public static final Controllers get() {
		return controllersInstance;
	}
	
	/**
	 * Gets the set of channel controllers.
	 * @return A set containing all registered controllers.
	 */
	public ImmutableSet<ChannelController> getControllers() {
		return ImmutableSet.copyOf(controllers.values());
	}
	
	/**
	 * Register a new ChannelController in the master list.
	 * @param controller The controller to be registered.
	 * @param command The command to register the controller for.
	 * @return <tt>false</tt> if this command already mapped to a controller.
	 * @throws IllegalArgumentException If adding the controller would cause an alias conflict.
	 */
	public Optional<ChannelController> registerController(Command command, ChannelController controller) throws IllegalArgumentException {
		// TODO check for alias conflicts
		/* TODO
		 * Register new aliases as commands, remove old aliases.
		 * This will probably require a server restart, modifying the
		 * plugin.yml, or using reflection to obtain the server's commandmap.
		 */
		return null;
	}
	
	/**
	 * Set a sender's active controller. Chat inputs that don't specify channel will go to this controller.
	 * @param sender The sender to change the active controller for.
	 * @param controller The new active controller.
	 * @return <tt>false</tt> if the sender's active controller did not change.
	 */
	public boolean setActiveController(CommandSender sender, ChannelController controller) {
		// TODO
		return true;
	}
	
	/**
	 * Gets a sender's active controller.
	 * @param sender The sender to get the active controller for.
	 * @return The sender's active controller.
	 */
	public ChannelController getActiveController(CommandSender sender) {
		return activeControllers.get(sender);
	}
	
	
}
