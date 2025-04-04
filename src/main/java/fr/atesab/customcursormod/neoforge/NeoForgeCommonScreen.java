package fr.atesab.customcursormod.neoforge;

import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.handler.CommonScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class NeoForgeCommonScreen extends CommonScreen {
	public class ForgeCommonScreenHandler extends Screen {
		NeoForgeCommonScreen cs;
		
		ForgeCommonScreenHandler(Component title) {
			super(title);
			cs = NeoForgeCommonScreen.this;
		}
		
		@Override
		protected void init() {
			NeoForgeCommonScreen.this.resize(width, height);
			NeoForgeCommonScreen.this.init();
			super.init();
		}
		
		@Override
		public void resize(@NotNull Minecraft client, int width, int height) {
			NeoForgeCommonScreen.this.resize(width, height);
			super.resize(client, width, height);
		}
		
		@Override
		public boolean charTyped(char chr, int modifiers) {
			return NeoForgeCommonScreen.this.charTyped(chr, modifiers) || super.charTyped(chr, modifiers);
		}
		
		@Override
		public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
			return NeoForgeCommonScreen.this.keyPressed(keyCode, scanCode, modifiers)
					|| super.keyPressed(keyCode, scanCode, modifiers);
		}
		
		@Override
		public boolean mouseClicked(double mouseX, double mouseY, int button) {
			return NeoForgeCommonScreen.this.mouseClicked(mouseX, mouseY, button)
					|| super.mouseClicked(mouseX, mouseY, button);
		}
		
		@Override
		public void tick() {
			NeoForgeCommonScreen.this.tick();
			super.tick();
		}
		
		@Override
		public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			if (this.minecraft != null && this.minecraft.level != null) {
				this.renderTransparentBackground(guiGraphics);
			} else {
				PANORAMA.render(guiGraphics, this.width, this.height, 1.0F, partialTicks);
				renderMenuBackgroundTexture(guiGraphics, MENU_BACKGROUND, 0, 0, 0.0F, 0.0F, this.width, this.height);
			}
			NeoForgeCommonScreen.this.render(new NeoForgeCommonMatrixStack(guiGraphics.pose()), mouseX, mouseY, partialTicks);
		}
		
		Font getTextRenderer() {
			return font;
		}
	}
	
	private final ForgeCommonScreenHandler handle;
	
	public NeoForgeCommonScreen(CommonScreen.CommonScreenObject obj) {
		super(obj.parent, obj.listener);
		handle = new ForgeCommonScreenHandler(obj.title.getHandle());
	}
	
	public ForgeCommonScreenHandler getHandle() {
		return handle;
	}
	
	@Override
	public void renderDefaultBackground(CommonMatrixStack stack) {
		if (Minecraft.getInstance().screen == handle) {
			handle.renderBackground(new GuiGraphics(Minecraft.getInstance(), Minecraft.getInstance().renderBuffers().bufferSource()), 0, 0, 0);
		}
	}
	
	@Override
	public void displayScreen() {
		Minecraft.getInstance().setScreen(handle);
	}
	
	@Override
	public int fontWidth(String text) {
		return handle.getTextRenderer().width(text);
	}
	
	@Override
	public void drawString(CommonMatrixStack stack, String text, float x, float y, int color) {
		GuiGraphics guiGraphics = new GuiGraphics(Minecraft.getInstance(), Minecraft.getInstance().renderBuffers().bufferSource());
		guiGraphics.drawString(handle.getTextRenderer(), text, (int)x, (int)y, color);
		Minecraft.getInstance().renderBuffers().bufferSource().endBatch();
	}
	
	@Override
	public float getBlitOffset() {
		return 0.0F;
	}
}
