package net.comecraft.comechat.format;

import java.io.Serializable;
import java.util.List;

/**
 * Represents a template that organizes ChatAffixes in a ChatMessage.
 */
public interface ChatFormatTemplate extends Serializable {

	/**
	 * Gets the list of affixes that make up this template.
	 * @return A list of ChatAffixes.
	 */
	public List<ChatAffix> getAffixes();
	
	/**
	 * Gets the unique identifier for this template.
	 * @return The ID for this template.
	 */
	public String getId();
}
