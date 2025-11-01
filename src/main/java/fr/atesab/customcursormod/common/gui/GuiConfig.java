package fr.atesab.customcursormod.common.gui;

import fr.atesab.customcursormod.common.CursorMod;
import fr.atesab.customcursormod.common.config.Configuration;
import fr.atesab.customcursormod.common.config.CursorConfig;
import fr.atesab.customcursormod.common.cursor.CursorType;
import fr.atesab.customcursormod.common.handler.CommonButton;
import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.handler.CommonScreen;
import fr.atesab.customcursormod.common.handler.GuiUtils;
import fr.atesab.customcursormod.common.handler.TranslationCommonText;
import fr.atesab.customcursormod.common.handler.CommonScreen.ScreenListener;
import fr.atesab.customcursormod.common.utils.Color;
import fr.atesab.customcursormod.common.utils.I18n;
import fr.atesab.customcursormod.neoforge.NeoForgeGuiUtils;

public class GuiConfig extends ScreenListener {
	public static CommonScreen create(CommonScreen parent) {
		return CommonScreen.create(parent, TranslationCommonText.create("cursormod.gui.config"), new GuiConfig());
	}

	private GuiConfig() {
	}

	@Override
	public void init() {
		CursorMod mod = CursorMod.getInstance();
		Configuration cfg = mod.getConfig();
		CommonScreen screen = getScreen();
		screen.addChildren(CommonButton.create(TranslationCommonText.create("menu.options"), width / 2 - 100,
				height / 2 + 24, 200, 20, b -> {
					if (cfg.dynamicCursor) {
						GuiConfigCursorMod.create(screen).displayScreen();
					} else {
						CursorConfig ccfg = mod.getCursors().get(CursorType.POINTER);

						GuiCursorConfig
								.create(screen, CursorType.POINTER, ccfg,
										cursorConfig -> mod.replaceCursor(CursorType.POINTER, cursorConfig))
								.displayScreen();
					}
				}));
		screen.addChildren(
				GuiUtils.get().createBooleanButton(TranslationCommonText.create("cursormod.config.dynCursor"),
						width / 2 - 100, height / 2 - 24, 200, 20, cfg::isDynamicCursor, cfg::setDynamicCursor));
		screen.addChildren(
				GuiUtils.get().createBooleanButton(TranslationCommonText.create("cursormod.config.clickAnim"),
						width / 2 - 100, height / 2, 200, 20, cfg::isClickAnimation, cfg::setClickAnimation));

		screen.addChildren(CommonButton.create(TranslationCommonText.create("gui.done"), width / 2 - 100,
				height / 2 + 48, 200, 20, b -> {
					mod.saveConfig();
					screen.getParent().displayScreen();
				}));
		super.init();
	}

	@Override
	public void render(CommonMatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		getScreen().renderDefaultBackground(stack);
		((NeoForgeGuiUtils)GuiUtils.get()).drawConfigTitle(stack, getScreen(), width, height);
		super.render(stack, mouseX, mouseY, partialTicks);
	}
}
