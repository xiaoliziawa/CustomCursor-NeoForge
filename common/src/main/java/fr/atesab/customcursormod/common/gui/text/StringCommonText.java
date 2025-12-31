package fr.atesab.customcursormod.common.gui.text;

import fr.atesab.customcursormod.common.handler.CommonSupplier;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class StringCommonText extends CommonTextAppendable {
	public static final CommonSupplier<String, StringCommonText> SUPPLIER = new CommonSupplier<>(false);

	private final MutableComponent handle;

	public StringCommonText(String text) {
        super(Component.literal(text));
        handle = Component.literal(text);
	}

	public StringCommonText(MutableComponent handle) {
        super(handle);
        this.handle = handle;
	}

	public static StringCommonText create(String text) {
		return SUPPLIER.fetch(text);
	}

	@Override
	public String getString() {
		return handle.getString();
	}

	@SuppressWarnings("unchecked")
	public <T> T getHandle() {
		return (T) handle;
	}

	@Override
	public CommonTextAppendable copy() {
		return new CommonTextAppendable(this.handle.copy());
	}

	public CommonTextAppendable append(CommonText text) {
		return new CommonTextAppendable(handle.append(text.<MutableComponent>getHandle()));
	}
}
