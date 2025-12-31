package fr.atesab.customcursormod.common.gui.widget;

import fr.atesab.customcursormod.common.gui.text.CommonText;
import fr.atesab.customcursormod.common.handler.CommonSupplier;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;

import java.util.function.Consumer;

public class CommonButton implements CommonElement {
	public static class CommonButtonObject {
		public final CommonText message;
		public final int xPosition;
		public final int yPosition;
		public final int width;
		public final int height;
		public final Consumer<CommonButton> action;

		private CommonButtonObject(CommonText message, int xPosition, int yPosition, int width, int height, Consumer<CommonButton> action) {
			this.message = message;
			this.xPosition = xPosition;
			this.yPosition = yPosition;
			this.width = width;
			this.height = height;
			this.action = action;
		}
	}

	public static final CommonSupplier<CommonButtonObject, CommonButton> SUPPLIER = new CommonSupplier<>(false);

	public final CommonButtonHandler handle;

	public CommonButton(CommonButtonObject obj) {
		handle = new CommonButtonHandler(this, obj);
	}

	public static CommonButton create(CommonText message, int xPosition, int yPosition, int width, int height, Consumer<CommonButton> action) {
		return SUPPLIER.fetch(new CommonButtonObject(message, xPosition, yPosition, width, height, action));
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
		return handle.active;
	}

	public boolean isVisible() {
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

	public void setEnable(boolean enable) {
		handle.active = enable;
	}

	public void setVisible(boolean visible) {
		handle.visible = visible;
	}

	public CommonText getMessage() {
		return new CommonText(handle.getMessage());
	}

	public void setMessage(CommonText message) {
		handle.setMessage(message.getHandle());
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		MouseButtonInfo buttonInfo = new MouseButtonInfo(mouseButton, 0);
		MouseButtonEvent event = new MouseButtonEvent(mouseX, mouseY, buttonInfo);
		return handle.mouseClicked(event, false);
	}
}
