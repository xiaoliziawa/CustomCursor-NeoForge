package fr.atesab.customcursormod.common.gui.widget;

import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.handler.CommonSupplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;
import net.minecraft.network.chat.Component;

public class CommonTextField implements CommonElement {
	public static class CommonTextFieldObject {
		public final String value;
		public final int xPosition;
		public final int yPosition;
		public final int width;
		public final int height;

		private CommonTextFieldObject(String value, int xPosition, int yPosition, int width, int height) {
			this.value = value;
			this.xPosition = xPosition;
			this.yPosition = yPosition;
			this.width = width;
			this.height = height;
		}
	}

	public final EditBox handle;

	public static final CommonSupplier<CommonTextFieldObject, CommonTextField> SUPPLIER = new CommonSupplier<>(false);

	public CommonTextField(EditBox handle) {
		this.handle = handle;
	}

	public CommonTextField(CommonTextFieldObject obj) {
		this.handle = new EditBox(Minecraft.getInstance().font, obj.xPosition, obj.yPosition, obj.width, obj.height,
				Component.literal(""));
		this.handle.visible = true;
		this.handle.setEditable(true);
		this.handle.active = true;
		this.handle.setBordered(true);
	}

	public static CommonTextField create(String value, int xPosition, int yPosition, int width, int height) {
		return SUPPLIER.fetch(new CommonTextFieldObject(value, xPosition, yPosition, width, height));
	}

	public EditBox getHandle() {
		return handle;
	}

	@Override
	public int getXPosition() {
		return handle.getX();
	}

	@Override
	public int getYPosition() {
		return handle.getY();
	}

	@Override
	public int getWidth() {
		return handle.getWidth();
	}

	@Override
	public int getHeight() {
		return handle.getHeight();
	}

	@Override
	public boolean isEnable() {
		return handle.visible;
	}

	@Override
	public void setXPosition(int xPosition) {
		handle.setX(xPosition);
	}

	@Override
	public void setYPosition(int yPosition) {
		handle.setY(yPosition);
	}

	@Override
	public void setWidth(int width) {
		handle.setWidth(width);
	}

	@Override
	public void setHeight(int height) {
		handle.setHeight(height);
	}

	@Override
	public void setEnable(boolean enable) {
		handle.visible = enable;
		handle.setEditable(enable);
		handle.active = enable;
		handle.setBordered(enable);
	}

	public void setValue(String value) {
		handle.setValue(value);
	}

	public String getValue() {
		return handle.getValue();
	}

	public void setMaxLength(int length) {
		handle.setMaxLength(length);
	}

	public void setTextColor(int color) {
		handle.setTextColor(color);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		// 创建 MouseButtonEvent 对象来调用新版本 API
		MouseButtonInfo buttonInfo = new MouseButtonInfo(mouseButton, 0);
		MouseButtonEvent event = new MouseButtonEvent(mouseX, mouseY, buttonInfo);
		return handle.mouseClicked(event, false);
	}

	@Override
	public void render(CommonMatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		GuiGraphics guiGraphics = new GuiGraphics(Minecraft.getInstance(), new GuiRenderState(), mouseX, mouseY);
		guiGraphics.pose().pushMatrix();
		handle.render(guiGraphics, mouseX, mouseY, partialTicks);
		guiGraphics.pose().popMatrix();
	}

	@Override
	public boolean charTyped(char key, int modifier) {
		CharacterEvent event = new CharacterEvent((int) key, modifier);
		return handle.charTyped(event);
	}

	@Override
	public boolean keyPressed(int key, int scan, int modifier) {
		KeyEvent event = new KeyEvent(key, scan, modifier);
		return handle.keyPressed(event);
	}
}
