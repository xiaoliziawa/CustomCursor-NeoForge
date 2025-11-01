package fr.atesab.customcursormod.neoforge;

import fr.atesab.customcursormod.common.handler.CommonText;
import fr.atesab.customcursormod.common.handler.CommonTextAppendable;
import fr.atesab.customcursormod.common.handler.StringCommonText;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class NeoForgeStringCommonTextImpl extends StringCommonText {

	private final MutableComponent handle;

	public NeoForgeStringCommonTextImpl(String text) {
		handle = Component.literal(text);
	}

	public NeoForgeStringCommonTextImpl(MutableComponent handle) {
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
	public NeoForgeCommonTextAppendable copy() {
		return new NeoForgeCommonTextAppendable(this.handle.copy());
	}

	@Override
	public CommonTextAppendable append(CommonText text) {
		return new NeoForgeCommonTextAppendable(handle.append(text.<MutableComponent>getHandle()));
	}
}