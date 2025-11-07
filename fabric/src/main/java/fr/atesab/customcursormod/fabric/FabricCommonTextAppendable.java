package fr.atesab.customcursormod.fabric;

import fr.atesab.customcursormod.common.handler.CommonText;
import fr.atesab.customcursormod.common.handler.CommonTextAppendable;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class FabricCommonTextAppendable extends CommonTextAppendable {

	private final MutableComponent handle;

	public FabricCommonTextAppendable(MutableComponent handle) {
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
		return new FabricCommonTextAppendable(handle.append(text.<Component>getHandle()));
	}

}
