package fr.atesab.customcursormod.fabric.gui;

import fr.atesab.customcursormod.common.cursor.SelectZone;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.navigation.FocusNavigationEvent;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;

import javax.annotation.Nullable;

public class FabricGuiSelectZone extends SelectZone implements GuiEventListener {
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	private boolean enable = true;
	private boolean focused = false;

	public FabricGuiSelectZone(int xPosition, int yPosition, int width, int height) {
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.width = width;
		this.height = height;
	}

	/**
	 * @return the xPosition
	 */
	@Override
	public int getXPosition() {
		return xPosition;
	}

	/**
	 * @return the yPosition
	 */
	@Override
	public int getYPosition() {
		return yPosition;
	}

	/**
	 * @return the height
	 */
	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public boolean isEnable() {
		return enable;
	}

	@Override
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	@Override
	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean isDoubleClick) {
		return super.mouseClicked(event.x(), event.y(), event.button());
	}

	@Override
	public boolean mouseReleased(MouseButtonEvent event) {
		return false;
	}

	@Override
	public boolean mouseDragged(MouseButtonEvent event, double mouseX, double mouseY) {
		return false;
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
		return false;
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		return super.keyPressed(event.key(), event.scancode(), event.modifiers());
	}

	@Override
	public boolean keyReleased(KeyEvent event) {
		return false;
	}

	@Override
	public boolean charTyped(CharacterEvent event) {
		// 调用父类的旧版本方法
		return super.charTyped((char) event.codepoint(), event.modifiers());
	}

	@Override
	public boolean isMouseOver(double mouseX, double mouseY) {
		return mouseX >= this.xPosition && mouseX < this.xPosition + this.width &&
			   mouseY >= this.yPosition && mouseY < this.yPosition + this.height;
	}

	@Override
	public void setFocused(boolean focused) {
		this.focused = focused;
	}

	@Override
	public boolean isFocused() {
		return this.focused;
	}

	@Nullable
	@Override
	public ComponentPath nextFocusPath(FocusNavigationEvent event) {
		return null;
	}

	@Override
	public ScreenRectangle getRectangle() {
		return new ScreenRectangle(this.xPosition, this.yPosition, this.width, this.height);
	}

	@Override
	public void mouseMoved(double mouseX, double mouseY) {
	}
}
