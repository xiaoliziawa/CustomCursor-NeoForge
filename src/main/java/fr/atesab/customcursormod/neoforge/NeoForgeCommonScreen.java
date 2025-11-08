package fr.atesab.customcursormod.neoforge;

import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.handler.CommonScreen;
import fr.atesab.customcursormod.common.handler.CommonElement;
import fr.atesab.customcursormod.common.handler.CommonButton;
import fr.atesab.customcursormod.common.handler.CommonButtonValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import com.mojang.blaze3d.vertex.PoseStack;
import org.jetbrains.annotations.NotNull;

public class NeoForgeCommonScreen extends CommonScreen {

	public static class ForgeCommonScreenHandler extends Screen {

		private final ScreenListener listener;
		private GuiGraphics currentGuiGraphics;

		public ForgeCommonScreenHandler(Component title, ScreenListener listener) {
			super(title);
			this.listener = listener;
		}

		/**
		 * Get the CommonScreen instance associated with this handler
		 * @return the CommonScreen instance
		 */
		public CommonScreen getCommonScreen() {
			return listener.getScreen();
		}

		/**
		 * Get the current GuiGraphics instance for rendering
		 * @return current GuiGraphics
		 */
		public GuiGraphics getCurrentGuiGraphics() {
			return currentGuiGraphics;
		}

		@Override
		protected void init() {
			super.init();
			
			listener.getScreen().resize(width, height);
			
			listener.getScreen().init();
			
			CommonScreen commonScreen = listener.getScreen();
			
			for (CommonElement element : commonScreen.childrens) {
				if (element instanceof NeoForgeCommonButton button) {
					this.addRenderableWidget(button.handle);
				} else if (element instanceof NeoForgeCommonTextField textField) {
					this.addRenderableWidget(textField.handle);
				} else if (element instanceof CommonButtonValue<?> buttonValue) {
					CommonButton handle = buttonValue.getHandle();
					if (handle instanceof NeoForgeCommonButton neoForgeButton) {
						this.addRenderableWidget(neoForgeButton.handle);
					}
				}
			}
		}

		@Override
		public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			this.currentGuiGraphics = guiGraphics;
			
			NeoForgeGuiUtils.setCurrentGuiGraphics(guiGraphics);
			
			super.render(guiGraphics, mouseX, mouseY, partialTicks);
			
			if (this.minecraft != null) {
				PoseStack poseStack = new PoseStack();
				CommonMatrixStack stack = new NeoForgeCommonMatrixStack(poseStack);
				listener.render(stack, mouseX, mouseY, partialTicks);
			}
		}

		@Override
		public void resize(@NotNull Minecraft minecraft, int width, int height) {
			super.resize(minecraft, width, height);
			listener.resize(width, height);
		}

		@Override
		public boolean charTyped(CharacterEvent event) {
			boolean listenerHandled = listener.charTyped((char) event.codepoint(), event.modifiers());
			boolean superHandled = super.charTyped(event);
			return listenerHandled || superHandled;
		}

		@Override
		public boolean keyPressed(KeyEvent event) {
			boolean listenerHandled = listener.keyPressed(event.key(), event.scancode(), event.modifiers());
			boolean superHandled = super.keyPressed(event);
			return listenerHandled || superHandled;
		}

		@Override
		public boolean mouseClicked(MouseButtonEvent event, boolean isDoubleClick) {
			boolean superHandled = super.mouseClicked(event, isDoubleClick);
			boolean listenerHandled = listener.mouseClicked(event.x(), event.y(), event.button());
			return superHandled || listenerHandled;
		}

		@Override
		public void tick() {
			super.tick();
			listener.tick();
		}

		Font getTextRenderer() {
			return font;
		}

		public void renderDefaultBackground(GuiGraphics guiGraphics) {
			this.renderMenuBackground(guiGraphics);
		}
	}

	private CommonScreen parent;
	private ScreenListener listener;
	private final ForgeCommonScreenHandler handle;

	public NeoForgeCommonScreen(CommonScreen parent, ScreenListener listener) {
		super(parent, listener);
		this.parent = parent;
		this.listener = listener;
		this.handle = new ForgeCommonScreenHandler(getTitle(), listener);
	}

	public NeoForgeCommonScreen(CommonScreenObject obj) {
		super(obj.parent, obj.listener);
		this.parent = obj.parent;
		this.listener = obj.listener;
		this.handle = new ForgeCommonScreenHandler(obj.title.getHandle(), obj.listener);
	}

	public ForgeCommonScreenHandler getHandle() {
		return handle;
	}

	@Override
	public void displayScreen() {
		Minecraft.getInstance().setScreen(handle);
	}

	@Override
	public CommonScreen getParent() {
		return parent;
	}

	public Component getTitle() {
		return Component.literal("Config Screen");
	}

	@Override
	public void renderDefaultBackground(CommonMatrixStack stack) {
		if (handle.getCurrentGuiGraphics() != null) {
			handle.renderDefaultBackground(handle.getCurrentGuiGraphics());
		}
	}

	@Override
	public int fontWidth(String text) {
		return handle.getTextRenderer().width(text);
	}

	@Override
	public void drawString(CommonMatrixStack stack, String text, float x, float y, int color) {
		GuiGraphics guiGraphics = handle.getCurrentGuiGraphics();
		if (guiGraphics != null) {
			guiGraphics.drawString(handle.getTextRenderer(), text, (int)x, (int)y, color);
		} else {
			GuiGraphics fallbackGraphics = new GuiGraphics(Minecraft.getInstance(), 
				new GuiRenderState());
			fallbackGraphics.drawString(handle.getTextRenderer(), text, (int)x, (int)y, color);
		}
	}

	@Override
	public float getBlitOffset() {
		return 0.0F;
	}
}
