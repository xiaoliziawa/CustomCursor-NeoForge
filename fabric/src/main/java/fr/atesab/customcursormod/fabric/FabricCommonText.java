package fr.atesab.customcursormod.fabric;

import fr.atesab.customcursormod.common.handler.CommonText;
import fr.atesab.customcursormod.common.handler.CommonTextAppendable;
import net.minecraft.network.chat.Component;

public class FabricCommonText extends CommonText {
	private final Component handle;

	public FabricCommonText(Component handle) {
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
		return new FabricCommonTextAppendable(handle.copy());
	}

}
