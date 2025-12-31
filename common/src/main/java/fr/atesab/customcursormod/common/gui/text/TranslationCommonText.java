package fr.atesab.customcursormod.common.gui.text;

import fr.atesab.customcursormod.common.handler.CommonSupplier;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;

public class TranslationCommonText extends CommonTextAppendable {
	public static class TranslationObject {
		public final String format;
		public final Object[] args;
		public TranslationObject(String format, Object[] args) {
			this.format = format;
			this.args = args;
		}
	}
	public static final CommonSupplier<TranslationObject, TranslationCommonText> SUPPLIER = new CommonSupplier<>(false);

	private final MutableComponent handle;

	public TranslationCommonText(String text, Object... args) {
		super(Component.translatable(text, args));
		this.handle = Component.translatable(text, args);

	}

	public TranslationCommonText(MutableComponent handle) {
		super(handle);
		this.handle = handle;
	}

	public static TranslationCommonText create(String format, Object... args) {
		return SUPPLIER.fetch(new TranslationObject(format, args));
	}

	public String getString() {
		return handle.getString();
	}

	public String getKey() {
		if (handle.getContents() instanceof TranslatableContents) {
			TranslatableContents content = (TranslatableContents) handle.getContents();
			return content.getKey();
		}
		throw new Error("Key element wasn't a translatable content");
	}

	@SuppressWarnings("unchecked")
	public <T> T getHandle() {
		return (T) handle;
	}

	@Override
	public CommonTextAppendable copy() {
		return new CommonTextAppendable(this.handle.copy());
	}

	@Override
	public CommonTextAppendable append(CommonText text) {
		return new CommonTextAppendable(handle.append(text.<MutableComponent>getHandle()));
	}
}
