package fr.atesab.customcursormod.common.gui.text;

import fr.atesab.customcursormod.common.handler.BasicHandler;
import net.minecraft.network.chat.Component;

public class CommonText extends BasicHandler<Component> {
	private final Component handle;

	public CommonText(Component handle) {
        super(handle);
        this.handle = handle;
	}

	public String getString() {
		return handle.getString();
	}

	public CommonTextAppendable copy() {
		return new CommonTextAppendable(handle.copy());
	}
}
