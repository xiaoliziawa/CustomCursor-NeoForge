package fr.atesab.customcursormod.fabric;

import fr.atesab.customcursormod.common.handler.CommonButton;
import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.handler.CommonText;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.input.MouseButtonInfo;

public class FabricCommonButton extends CommonButton {

	public static class CustomButton extends Button {
		private final FabricCommonButton parent;

		public CustomButton(FabricCommonButton parent, CommonButtonObject obj) {
			super(obj.xPosition, obj.yPosition, obj.width, obj.height,
				  obj.message.getHandle(), b -> obj.action.accept(parent), DEFAULT_NARRATION);
			this.parent = parent;
		}

		@Override
		protected void renderContents(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			if (!visible) return;

			boolean hovered = mouseX >= getX() && mouseY >= getY() &&
			                  mouseX < getX() + getWidth() &&
			                  mouseY < getY() + getHeight();

			FabricGuiUtils.drawRoundedButton(guiGraphics, getX(), getY(),
			                                  getWidth(), getHeight(),
			                                  hovered, active);
			String text = getMessage().getString();
			Minecraft minecraft = Minecraft.getInstance();
			int textColor;

			if (!active) {
				textColor = 0xFF404040;
			} else if (hovered) {
				textColor = 0xFFe6e6ff;
			} else {
				textColor = 0xFFe0e0e0;
			}
			int textX = getX() + (getWidth() - minecraft.font.width(text)) / 2;
			int textY = getY() + (getHeight() - minecraft.font.lineHeight) / 2;
			if (active) {
				guiGraphics.drawString(minecraft.font, text, textX + 1, textY + 1, 0x80000000);
			}
			guiGraphics.drawString(minecraft.font, text, textX, textY, textColor);
		}
	}

	public final CustomButton handle;

	public FabricCommonButton(CommonButtonObject obj) {
		handle = new CustomButton(this, obj);
	}

	public FabricCommonButton(Button handle) {
		throw new UnsupportedOperationException("Use FabricCommonButton(CommonButtonObject) constructor instead");
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
		handle.active = enable;
	}

	@Override
	public void setVisible(boolean visible) {
		handle.visible = visible;
	}

	@Override
	public CommonText getMessage() {
		return new FabricCommonText(handle.getMessage());
	}

	@Override
	public void setMessage(CommonText message) {
		handle.setMessage(message.getHandle());
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		MouseButtonInfo buttonInfo = new MouseButtonInfo(mouseButton, 0);
		MouseButtonEvent event = new MouseButtonEvent(mouseX, mouseY, buttonInfo);
		return handle.mouseClicked(event, false);
	}

	@Override
	public void render(CommonMatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		// 现在由CustomButton.renderContents处理渲染，这个方法不再需要
	}

	@Override
	public boolean isVisible() {
		return handle.visible;
	}
}
