package fr.atesab.customcursormod.common.gui.text;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class CommonTextAppendable extends CommonText {
	private final MutableComponent handle;

	public CommonTextAppendable(MutableComponent handle) {
        super(handle);
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
		return new CommonTextAppendable(this.handle.copy());
	}

	public CommonTextAppendable append(CommonText text) {
		return new CommonTextAppendable(handle.append(text.<Component>getHandle()));
	}

	public CommonTextAppendable append(String text) {
		return append(StringCommonText.create(text));
	}
}
