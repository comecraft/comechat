package net.comecraft.comechat.format;

import java.util.List;

public class BaseChannelTemplate implements ChatFormatTemplate {

	private final String id;
	private List<ChatAffix> affixes;
	

	public BaseChannelTemplate(String id, List<ChatAffix> affixes) {
		this.id = id;
		this.affixes = affixes;
	}

	@Override
	public List<ChatAffix> getAffixes() {
		return affixes;
	}

	@Override
	public String getId() {
		return id;
	}

}
