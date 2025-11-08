package fr.atesab.customcursormod.fabric;

import fr.atesab.customcursormod.common.handler.CommonText;
import fr.atesab.customcursormod.common.handler.CommonTextAppendable;
import fr.atesab.customcursormod.common.handler.StringCommonText;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class FabricStringCommonTextImpl extends StringCommonText {

	private final MutableComponent handle;

	public FabricStringCommonTextImpl(String text) {
		handle = Component.literal(text);
	}

	public FabricStringCommonTextImpl(MutableComponent handle) {
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
	public FabricCommonTextAppendable copy() {
		return new FabricCommonTextAppendable(this.handle.copy());
	}

	@Override
	public CommonTextAppendable append(CommonText text) {
		return new FabricCommonTextAppendable(handle.append(text.<MutableComponent>getHandle()));
	}
}