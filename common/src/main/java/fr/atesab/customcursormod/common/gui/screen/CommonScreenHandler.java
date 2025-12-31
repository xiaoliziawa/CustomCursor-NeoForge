package fr.atesab.customcursormod.common.gui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.gui.GuiUtils;
import fr.atesab.customcursormod.common.gui.widget.CommonButton;
import fr.atesab.customcursormod.common.gui.widget.CommonButtonValue;
import fr.atesab.customcursormod.common.gui.widget.CommonElement;
import fr.atesab.customcursormod.common.gui.widget.CommonTextField;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.CharacterEvent;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class CommonScreenHandler extends Screen {
    private final CommonScreen.ScreenListener listener;
    private GuiGraphics currentGuiGraphics;

    public CommonScreenHandler(Component title, CommonScreen.ScreenListener listener) {
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

    public void setCurrentGuiGraphics(GuiGraphics guiGraphics) {
        this.currentGuiGraphics = guiGraphics;
    }

    @Override
    protected void init() {
        super.init();

        this.getCommonScreen().resize(width, height);

        this.getCommonScreen().init();

        CommonScreen commonScreen = this.getCommonScreen();

        for (CommonElement element : commonScreen.childrens) {
            if (element instanceof CommonButton button) {
                this.addRenderableWidget(button.handle);
            } else if (element instanceof CommonTextField textField) {
                this.addRenderableWidget(textField.handle);
            } else if (element instanceof CommonButtonValue<?> buttonValue) {
                CommonButton handle = buttonValue.getHandle();
                if (handle instanceof CommonButton button) {
                    this.addRenderableWidget(button.handle);
                }
            }
        }
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.setCurrentGuiGraphics(guiGraphics);

        GuiUtils.setCurrentGuiGraphics(guiGraphics);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        if (this.minecraft != null) {
            PoseStack poseStack = new PoseStack();
            CommonMatrixStack stack = new CommonMatrixStack(poseStack);
            this.getCommonScreen().listener.render(stack, mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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

    public Font getTextRenderer() {
        return font;
    }

    public void renderDefaultBackground(GuiGraphics guiGraphics) {
        this.renderMenuBackground(guiGraphics);
    }
}
