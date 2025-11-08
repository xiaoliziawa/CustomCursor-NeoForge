package fr.atesab.customcursormod.fabric;

import fr.atesab.customcursormod.common.handler.CommonText;
import fr.atesab.customcursormod.common.handler.CommonTextAppendable;
import fr.atesab.customcursormod.common.handler.TranslationCommonText;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;

public class FabricTranslationCommonTextImpl extends TranslationCommonText {

	private final MutableComponent handle;

	public FabricTranslationCommonTextImpl(String text, Object... args) {
		handle = Component.translatable(text, args);
	}

	public FabricTranslationCommonTextImpl(MutableComponent handle) {
		this.handle = handle;
	}

	@Override
	public String getString() {
		return handle.getString();
	}

	@Override
	public String getKey() {
		if (handle.getContents() instanceof TranslatableContents) {
			TranslatableContents content = (TranslatableContents) handle.getContents();
			return content.getKey();
		}
		throw new Error("Key element wasn't a translatable content");
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