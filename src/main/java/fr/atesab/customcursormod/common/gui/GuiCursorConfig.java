package fr.atesab.customcursormod.common.gui;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;

import javax.imageio.ImageIO;

import fr.atesab.customcursormod.common.config.CursorConfig;
import fr.atesab.customcursormod.common.cursor.CursorType;
import fr.atesab.customcursormod.common.cursor.SelectZone;
import fr.atesab.customcursormod.common.handler.CommonButton;
import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.handler.CommonScreen;
import fr.atesab.customcursormod.common.handler.CommonShaders;
import fr.atesab.customcursormod.common.handler.CommonScreen.ScreenListener;
import fr.atesab.customcursormod.common.handler.CommonTextField;
import fr.atesab.customcursormod.common.handler.GuiUtils;
import fr.atesab.customcursormod.common.handler.TranslationCommonText;
import fr.atesab.customcursormod.common.utils.Color;
import fr.atesab.customcursormod.common.utils.I18n;
import fr.atesab.customcursormod.common.utils.MathHelper;
import fr.atesab.customcursormod.neoforge.NeoForgeCommonTextField;

public class GuiCursorConfig extends ScreenListener {
	public static CommonScreen create(CommonScreen parent, CursorType type, CursorConfig cursorConfig,
			Consumer<CursorConfig> saveCallback) {
		return CommonScreen.create(parent, TranslationCommonText.create("cursormod.gui.cursorList"),
				new GuiCursorConfig(type, cursorConfig, saveCallback));
	}

	private SelectZone selectZone;
	private CommonTextField xhotspot;
	private CommonTextField yhotspot;
	private CommonTextField cursorLocation;
	private CommonTextField cursorSize;
	private CommonButton doneButton;
	private int imageWidth = 1;
	private int imageHeight = 1;
	private int numImage = 1;
	private final CursorType type;
	private CursorConfig cursorConfig;
	private Consumer<CursorConfig> callback;

	private GuiCursorConfig(CursorType type, CursorConfig cursorConfig, Consumer<CursorConfig> callback) {
		this.type = type;
		this.cursorConfig = cursorConfig.copy();
		this.callback = callback;
	}

	private void updateCursorValues(CursorConfig cursorConfig) {
		this.cursorConfig = cursorConfig;
		xhotspot.setValue(String.valueOf(cursorConfig.getxHotSpot()));
		yhotspot.setValue(String.valueOf(cursorConfig.getyHotSpot()));
		cursorLocation.setValue(cursorConfig.getLink());
		cursorSize.setValue(String.valueOf(cursorConfig.getSize()));
	}

	@Override
	public void render(CommonMatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		CommonScreen screen = getScreen();
		GuiUtils gutils = GuiUtils.get();
		screen.renderDefaultBackground(stack);
		screen.drawCenterString(stack, type.getName(), width / 2 - 74, height / 2 - 41 - 21, Color.ORANGE, 2);
		screen.drawRightString(stack, I18n.get("cursormod.config.xhotspot") + " : ", xhotspot.getXPosition(),
				xhotspot.getYPosition() + xhotspot.getHeight() / 2 - gutils.fontHeight() / 2, Color.WHITE);
		screen.drawRightString(stack, I18n.get("cursormod.config.yhotspot") + " : ", yhotspot.getXPosition(),
				yhotspot.getYPosition() + yhotspot.getHeight() / 2 - gutils.fontHeight() / 2, Color.WHITE);
		screen.drawRightString(stack, I18n.get("cursormod.config.location") + " : ", cursorLocation.getXPosition(),
				cursorLocation.getYPosition() + cursorLocation.getHeight() / 2 - gutils.fontHeight() / 2, Color.WHITE);
		screen.drawRightString(stack, I18n.get("cursormod.config.size") + " : ", cursorSize.getXPosition(),
				cursorSize.getYPosition() + cursorSize.getHeight() / 2 - gutils.fontHeight() / 2, Color.WHITE);
		if (syncImageSize()) {
			gutils.drawGradientRect(stack, screen.getBlitOffset(), width / 2 + 36, height / 2 - 64, width / 2 + 164,
					height / 2 + 64, -1072689136, -804253680);
			gutils.setShader(CommonShaders.get().getPositionTexShader());
			cursorConfig.getResourceLocation().setShaderTexture();
			gutils.drawScaledCustomSizeModalRect(width / 2 + 36, height / 2 - 64, 0, 0, imageWidth, imageHeight, 128,
					128, imageWidth, imageHeight * numImage, 0xffffffff, true);
			if (cursorConfig.getxHotSpot() >= 0 && cursorConfig.getxHotSpot() < imageWidth
					&& cursorConfig.getyHotSpot() >= 0 && cursorConfig.getyHotSpot() < imageHeight)
				screen.drawCenterString(stack, "+",
						width / 2 + 36 + ((int) (((float) cursorConfig.getxHotSpot()) * 128F / (float) imageWidth)),
						height / 2 - 64 + ((int) (((float) cursorConfig.getyHotSpot()) * 128F / (float) imageHeight))
								- gutils.fontHeight() / 2,
						Color.WHITE);
			if (numImage > 1)
				screen.drawCenterString(stack, "(" + I18n.get("cursormod.gui.animate") + ")", width / 2 + 100,
						height / 2 + 64 + 1, Color.WHITE);
			selectZone.setEnable(true);
		} else {
			screen.drawCenterString(stack, I18n.get("cursormod.gui.error"), width / 2 + 100,
					height / 2 - gutils.fontHeight() / 2, 0xffff4444);
			selectZone.setEnable(false);
		}
		super.render(stack, mouseX, mouseY, partialTicks);
	}

	@Override
	public void init() {
		CommonScreen screen = getScreen();
		syncImageSize();
		xhotspot = screen.addChildren(CommonTextField.create("", width / 2 - 99, height / 2 - 41, 124, 18));
		xhotspot.setEnable(true);
		yhotspot = screen.addChildren(CommonTextField.create("", width / 2 - 99, height / 2 - 20, 124, 18));
		yhotspot.setEnable(true);
		cursorLocation = screen.addChildren(CommonTextField.create("", width / 2 - 99, height / 2 + 1, 124, 18));
		cursorLocation.setEnable(true);
		cursorLocation.setMaxLength(Integer.MAX_VALUE);
		
		// 将大小输入框移到"默认"按钮的位置，并将按钮下移
		cursorSize = screen.addChildren(CommonTextField.create("32", width / 2 - 99, height / 2 + 22, 124, 18));
		cursorSize.setEnable(true);
		
		// Set initial focus to null
		((NeoForgeCommonTextField)xhotspot).handle.setFocused(false);
		((NeoForgeCommonTextField)yhotspot).handle.setFocused(false);
		((NeoForgeCommonTextField)cursorLocation).handle.setFocused(false);
		((NeoForgeCommonTextField)cursorSize).handle.setFocused(false);
		
		updateCursorValues(cursorConfig);
		selectZone = SelectZone.create(width / 2 + 36, height / 2 - 64, 128, 128);
		screen.addChildren(selectZone);
		
		// 调整按钮位置
		screen.addChildren(CommonButton.create(TranslationCommonText.create("cursormod.gui.default"), width / 2 - 174,
				height / 2 + 43, 200, 20, b -> updateCursorValues(type.getDefaultConfig())));

		doneButton = screen.addChildren(CommonButton.create(TranslationCommonText.create("gui.done"), width / 2 - 174,
				height / 2 + 64, 100, 20, b -> {
					saveConfig(cursorConfig);
					screen.getParent().displayScreen();
				}));
		screen.addChildren(CommonButton.create(TranslationCommonText.create("cursormod.gui.cancel"), width / 2 - 72,
				height / 2 + 64, 99, 20, b -> screen.getParent().displayScreen()));
		super.init();
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		boolean handled = false;
		
		// Reset all text fields focus
		((NeoForgeCommonTextField)xhotspot).handle.setFocused(false);
		((NeoForgeCommonTextField)yhotspot).handle.setFocused(false);
		((NeoForgeCommonTextField)cursorLocation).handle.setFocused(false);
		((NeoForgeCommonTextField)cursorSize).handle.setFocused(false);
		
		// Handle text field clicks
		if (xhotspot.mouseClicked(mouseX, mouseY, mouseButton)) {
			((NeoForgeCommonTextField)xhotspot).handle.setFocused(true);
			handled = true;
		}
		if (yhotspot.mouseClicked(mouseX, mouseY, mouseButton)) {
			((NeoForgeCommonTextField)yhotspot).handle.setFocused(true);
			handled = true;
		}
		if (cursorLocation.mouseClicked(mouseX, mouseY, mouseButton)) {
			((NeoForgeCommonTextField)cursorLocation).handle.setFocused(true);
			handled = true;
		}
		if (cursorSize.mouseClicked(mouseX, mouseY, mouseButton)) {
			((NeoForgeCommonTextField)cursorSize).handle.setFocused(true);
			handled = true;
		}
		
		// Handle image zone clicks
		if (!handled && mouseX >= width / 2 + 36 && mouseY >= height / 2 - 64 && mouseX <= width / 2 + 164
				&& mouseY <= height / 2 + 64) {
			cursorConfig.setxHotSpot(MathHelper
					.clamp((int) ((float) (mouseX - (width / 2 + 36)) * (float) imageWidth / 128F), 0, imageWidth - 1));
			cursorConfig.setyHotSpot(MathHelper.clamp(
					(int) ((float) (mouseY - (height / 2 - 64)) * (float) imageHeight / 128F), 0, imageWidth - 1));
			xhotspot.setValue(String.valueOf(cursorConfig.getxHotSpot()));
			yhotspot.setValue(String.valueOf(cursorConfig.getyHotSpot()));
			handled = true;
		}
		
		return handled || super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	protected void saveConfig(CursorConfig cursorConfig) {
		if (callback != null)
			callback.accept(cursorConfig);
	}

	private boolean syncImageSize() {
		try {
			BufferedImage image = ImageIO.read(cursorConfig.getResource());
			imageWidth = image.getWidth();
			imageHeight = image.getWidth();
			numImage = image.getHeight() / image.getWidth();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void tick() {
		verifyValue();
		super.tick();
	}

	private void verifyValue() {
		boolean canLeave = syncImageSize();
		try {
			cursorConfig.setxHotSpot(Integer.valueOf(xhotspot.getValue()));
			if (cursorConfig.getxHotSpot() >= 0 && cursorConfig.getxHotSpot() < imageWidth) {
				xhotspot.setTextColor(Color.WHITE);
			} else {
				xhotspot.setTextColor(Color.RED);
				canLeave = false;
			}
		} catch (Exception e) {
			xhotspot.setTextColor(Color.RED);
			canLeave = false;
		}
		try {
			cursorConfig.setyHotSpot(Integer.valueOf(yhotspot.getValue()));
			if (cursorConfig.getyHotSpot() >= 0 && cursorConfig.getyHotSpot() < imageHeight) {
				yhotspot.setTextColor(Color.WHITE);
			} else {
				yhotspot.setTextColor(Color.RED);
				canLeave = false;
			}
		} catch (Exception e) {
			yhotspot.setTextColor(Color.RED);
			canLeave = false;
		}
		try {
			int size = Integer.valueOf(cursorSize.getValue());
			if (size > 0 && size <= 128) {
				cursorSize.setTextColor(Color.WHITE);
				cursorConfig.setSize(size);
			} else {
				cursorSize.setTextColor(Color.RED);
				canLeave = false;
			}
		} catch (Exception e) {
			cursorSize.setTextColor(Color.RED);
			canLeave = false;
		}
		cursorConfig.setLink(cursorLocation.getValue());
		doneButton.setEnable(canLeave);
	}
}
