package fr.atesab.customcursormod.neoforge;

import fr.atesab.customcursormod.common.handler.CommonText;
import fr.atesab.customcursormod.common.handler.CommonTextAppendable;
import net.minecraft.network.chat.Component;

public class NeoForgeCommonText extends CommonText {
	private final Component handle;

	public NeoForgeCommonText(Component handle) {
		this.handle = handle;
	}

	@Override
	public String getString() {
		return handle.getString();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getHandle() {
		return (T) handle;
	}

	@Override
	public CommonTextAppendable copy() {
		return new NeoForgeCommonTextAppendable(handle.copy());
	}

}
