package fr.atesab.customcursormod.fabric;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.atesab.customcursormod.common.handler.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.render.state.GuiRenderState;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class FabricCommonScreen extends CommonScreen {

	public static class FabricCommonScreenHandler extends CommonScreenHandler {

		public FabricCommonScreenHandler(Component title, ScreenListener listener) {
			super(title, listener);
		}

		@Override
		protected void init() {
			super.init();
			
			this.getCommonScreen().resize(width, height);

            this.getCommonScreen().init();
			
			CommonScreen commonScreen = this.getCommonScreen();
			
			for (CommonElement element : commonScreen.childrens) {
				if (element instanceof FabricCommonButton button) {
					this.addRenderableWidget(button.handle);
				} else if (element instanceof FabricCommonTextField textField) {
					this.addRenderableWidget(textField.handle);
				} else if (element instanceof CommonButtonValue<?> buttonValue) {
					CommonButton handle = buttonValue.getHandle();
					if (handle instanceof FabricCommonButton neoForgeButton) {
						this.addRenderableWidget(neoForgeButton.handle);
					}
				}
			}
		}

		@Override
		public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			this.setCurrentGuiGraphics(guiGraphics);
			
			FabricGuiUtils.setCurrentGuiGraphics(guiGraphics);
			
			super.render(guiGraphics, mouseX, mouseY, partialTicks);
			
			if (this.minecraft != null) {
				PoseStack poseStack = new PoseStack();
				CommonMatrixStack stack = new FabricCommonMatrixStack(poseStack);
                this.getCommonScreen().listener.render(stack, mouseX, mouseY, partialTicks);
			}
		}

		@Override
		public void resize(@NotNull Minecraft minecraft, int width, int height) {
			super.resize(minecraft, width, height);
            this.getCommonScreen().listener.resize(width, height);
		}

		@Override
		public boolean charTyped(CharacterEvent event) {
			boolean listenerHandled = this.getCommonScreen().listener.charTyped((char) event.codepoint(), event.modifiers());
			boolean superHandled = super.charTyped(event);
			return listenerHandled || superHandled;
		}

		@Override
		public boolean keyPressed(KeyEvent event) {
			boolean listenerHandled = this.getCommonScreen().listener.keyPressed(event.key(), event.scancode(), event.modifiers());
			boolean superHandled = super.keyPressed(event);
			return listenerHandled || superHandled;
		}

		@Override
		public boolean mouseClicked(MouseButtonEvent event, boolean isDoubleClick) {
			boolean superHandled = super.mouseClicked(event, isDoubleClick);
			boolean listenerHandled = this.getCommonScreen().listener.mouseClicked(event.x(), event.y(), event.button());
			return superHandled || listenerHandled;
		}

		@Override
		public void tick() {
			super.tick();
            this.getCommonScreen().listener.tick();
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
	private final FabricCommonScreenHandler handle;

	public FabricCommonScreen(CommonScreen parent, ScreenListener listener) {
		super(parent, listener);
		this.parent = parent;
		this.listener = listener;
		this.handle = new FabricCommonScreenHandler(getTitle(), listener);
	}

	public FabricCommonScreen(CommonScreenObject obj) {
		super(obj.parent, obj.listener);
		this.parent = obj.parent;
		this.listener = obj.listener;
		this.handle = new FabricCommonScreenHandler(obj.title.getHandle(), obj.listener);
	}

    @Override
    public FabricCommonScreenHandler getHandle() {
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
