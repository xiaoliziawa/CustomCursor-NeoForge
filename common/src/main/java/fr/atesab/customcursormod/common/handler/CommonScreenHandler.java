package fr.atesab.customcursormod.common.handler;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public abstract class CommonScreenHandler extends Screen {
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
}
