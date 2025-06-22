package fr.atesab.customcursormod.neoforge;

import fr.atesab.customcursormod.common.handler.CommonButton;
import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.handler.CommonText;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;

public class NeoForgeCommonButton extends CommonButton {
	
	// 自定义Button类，覆盖render方法
	public static class CustomButton extends Button {
		private final NeoForgeCommonButton parent;
		
		public CustomButton(NeoForgeCommonButton parent, CommonButtonObject obj) {
			super(obj.xPosition, obj.yPosition, obj.width, obj.height, 
				  obj.message.getHandle(), b -> obj.action.accept(parent), DEFAULT_NARRATION);
			this.parent = parent;
		}
		
		@Override
		public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			if (!visible) return;
			
			// 检查鼠标是否悬停在按钮上
			boolean hovered = mouseX >= getX() && mouseY >= getY() && 
			                  mouseX < getX() + getWidth() && 
			                  mouseY < getY() + getHeight();
			
			// 自定义按钮背景
			NeoForgeGuiUtils.drawRoundedButton(guiGraphics, getX(), getY(), 
			                                  getWidth(), getHeight(), 
			                                  hovered, active);
			// 绘制按钮文本
			String text = getMessage().getString();
			Minecraft minecraft = Minecraft.getInstance();
			int textColor;
			
			if (!active) {
				// 禁用状态：暗灰色
				textColor = 0xFF404040;
			} else if (hovered) {
				// 悬停状态：亮蓝白色
				textColor = 0xFFe6e6ff;
			} else {
				// 正常状态：明亮的浅灰色
				textColor = 0xFFe0e0e0;
			}
			// 计算文本居中位置
			int textX = getX() + (getWidth() - minecraft.font.width(text)) / 2;
			int textY = getY() + (getHeight() - minecraft.font.lineHeight) / 2;
			// 绘制文本阴影
			if (active) {
				guiGraphics.drawString(minecraft.font, text, textX + 1, textY + 1, 0x80000000);
			}
			// 绘制文本
			guiGraphics.drawString(minecraft.font, text, textX, textY, textColor);
		}
	}
	
	public final CustomButton handle;

	public NeoForgeCommonButton(CommonButtonObject obj) {
		handle = new CustomButton(this, obj);
	}

	public NeoForgeCommonButton(Button handle) {
		// 这个构造函数不应该被使用，因为我们需要自定义Button
		throw new UnsupportedOperationException("Use NeoForgeCommonButton(CommonButtonObject) constructor instead");
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
		return new NeoForgeCommonText(handle.getMessage());
	}

	@Override
	public void setMessage(CommonText message) {
		handle.setMessage(message.getHandle());
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		return handle.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void render(CommonMatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		// 现在由CustomButton.renderWidget处理渲染，这个方法不再需要
	}

	@Override
	public boolean isVisible() {
		return handle.visible;
	}
}
