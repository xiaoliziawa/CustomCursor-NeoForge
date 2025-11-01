package fr.atesab.customcursormod.neoforge;

import fr.atesab.customcursormod.common.handler.CommonText;
import fr.atesab.customcursormod.common.handler.CommonTextAppendable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class NeoForgeCommonTextAppendable extends CommonTextAppendable {

	private final MutableComponent handle;

	public NeoForgeCommonTextAppendable(MutableComponent handle) {
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
		return new NeoForgeCommonTextAppendable(handle.append(text.<Component>getHandle()));
	}

}
