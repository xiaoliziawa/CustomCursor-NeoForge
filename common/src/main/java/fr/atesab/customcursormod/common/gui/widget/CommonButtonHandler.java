package fr.atesab.customcursormod.common.gui.widget;

import fr.atesab.customcursormod.common.gui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;

public class CommonButtonHandler extends Button {
    private final CommonButton parent;
    private final CommonButton.CommonButtonObject parentObj;

    public CommonButtonHandler(CommonButton parent, CommonButton.CommonButtonObject obj) {
        super(obj.xPosition, obj.yPosition, obj.width, obj.height,
                obj.message.getHandle(), b -> obj.action.accept(parent), DEFAULT_NARRATION);
        this.parent = parent;
        this.parentObj = obj;
    }

    public CommonButton getParent() {
        return parent;
    }

    public CommonButton.CommonButtonObject getObject() {
        return parentObj;
    }

    @Override
    protected void renderContents(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (!visible) return;

        boolean hovered = mouseX >= getX() && mouseY >= getY() &&
                mouseX < getX() + getWidth() &&
                mouseY < getY() + getHeight();

        GuiUtils.drawRoundedButton(guiGraphics, getX(), getY(),
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
