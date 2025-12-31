package fr.atesab.customcursormod.neoforge;

import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.atesab.customcursormod.common.CursorMod;
import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.handler.CommonScreen;
import fr.atesab.customcursormod.common.handler.GuiUtils;
import fr.atesab.customcursormod.common.utils.Color;
import fr.atesab.customcursormod.common.utils.I18nHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;

public class NeoForgeGuiUtils extends GuiUtils {

    private NeoForgeGuiUtils() {
    }

    private static final NeoForgeGuiUtils instance = new NeoForgeGuiUtils();
    
    private static Identifier currentTexture;
    private static GuiGraphics currentGuiGraphics;

    /**
     * @return the instance
     */
    public static NeoForgeGuiUtils getForge() {
        return instance;
    }
    
    /**
     * 设置当前的纹理资源位置
     */
    public static void setCurrentTexture(Identifier texture) {
        currentTexture = texture;
    }
    
    /**
     * 设置当前的GuiGraphics实例
     */
    public static void setCurrentGuiGraphics(GuiGraphics guiGraphics) {
        currentGuiGraphics = guiGraphics;
    }

    /**
     * Draws a scaled, textured, tiled modal rect at z = 0. This method isn't used
     * anywhere in vanilla code.
     *
     * @param x                x location
     * @param y                y location
     * @param u                x uv location
     * @param v                y uv location
     * @param uWidth           uv width
     * @param vHeight          uv height
     * @param width            width
     * @param height           height
     * @param tileWidth        tile width
     * @param tileHeight       tile height
     * @param color            tile color
     * @param useAlpha         use the alpha of the color
     */
    @Override
    public void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width,
                                              int height, float tileWidth, float tileHeight, int color, boolean useAlpha) {
        if (currentGuiGraphics != null && currentTexture != null) {
            currentGuiGraphics.blit(RenderPipelines.GUI_TEXTURED,
                currentTexture, x, y, u, v, width, height, uWidth, vHeight, (int)tileWidth, (int)tileHeight, color);
        }
    }

    @Override
    public void drawGradientRect(CommonMatrixStack stack, int left, int top, int right, int bottom, int rightTopColor,
                                 int leftTopColor, int leftBottomColor, int rightBottomColor, float zLevel) {
        float alphaRightTop = (float) (rightTopColor >> 24 & 255) / 255.0F;
        float redRightTop = (float) (rightTopColor >> 16 & 255) / 255.0F;
        float greenRightTop = (float) (rightTopColor >> 8 & 255) / 255.0F;
        float blueRightTop = (float) (rightTopColor & 255) / 255.0F;
        float alphaLeftTop = (float) (leftTopColor >> 24 & 255) / 255.0F;
        float redLeftTop = (float) (leftTopColor >> 16 & 255) / 255.0F;
        float greenLeftTop = (float) (leftTopColor >> 8 & 255) / 255.0F;
        float blueLeftTop = (float) (leftTopColor & 255) / 255.0F;
        float alphaLeftBottom = (float) (leftBottomColor >> 24 & 255) / 255.0F;
        float redLeftBottom = (float) (leftBottomColor >> 16 & 255) / 255.0F;
        float greenLeftBottom = (float) (leftBottomColor >> 8 & 255) / 255.0F;
        float blueLeftBottom = (float) (leftBottomColor & 255) / 255.0F;
        float alphaRightBottom = (float) (rightBottomColor >> 24 & 255) / 255.0F;
        float redRightBottom = (float) (rightBottomColor >> 16 & 255) / 255.0F;
        float greenRightBottom = (float) (rightBottomColor >> 8 & 255) / 255.0F;
        float blueRightBottom = (float) (rightBottomColor & 255) / 255.0F;

        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexBuffer = bufferSource.getBuffer(RenderTypes.debugQuads());


        vertexBuffer.addVertex((float) right, (float) top, zLevel)
                .setColor(redRightTop, greenRightTop, blueRightTop, alphaRightTop);

        vertexBuffer.addVertex((float) left, (float) top, zLevel)
                .setColor(redLeftTop, greenLeftTop, blueLeftTop, alphaLeftTop);

        vertexBuffer.addVertex((float) left, (float) bottom, zLevel)
                .setColor(redLeftBottom, greenLeftBottom, blueLeftBottom, alphaLeftBottom);

        vertexBuffer.addVertex((float) right, (float) bottom, zLevel)
                .setColor(redRightBottom, greenRightBottom, blueRightBottom, alphaRightBottom);

        bufferSource.endBatch();
    }

    @Override
    public int fontHeight() {
        return Minecraft.getInstance().font.lineHeight;
    }

    @Override
    public void setShaderColor(float r, float g, float b, float a) {
        // Color is handled directly through vertex data in MC 1.21.6
    }
    
    /**
     * 绘制预览区域及边框
     * 
     * @param stack 矩阵堆栈
     * @param screen 屏幕实例
     * @param x 预览区域X坐标
     * @param y 预览区域Y坐标
     * @param width 预览区域宽度
     * @param height 预览区域高度
     */
    @Override
    public void drawPreviewArea(CommonMatrixStack stack, CommonScreen screen, int x, int y, int width, int height) {
        // 绘制预览区域背景
        drawGradientRect(stack, screen.getBlitOffset(), x, y, x + width, y + height, 
                -1072689136, -804253680);
        
        // 在预览区域周围绘制一个方框
        if (screen instanceof NeoForgeCommonScreen commonScreen) {
            GuiGraphics guiGraphics = commonScreen.getHandle().getCurrentGuiGraphics();
            if (guiGraphics != null) {
                // 绘制黑色透明填充背景
                int bgColor = 0x80000000; // 半透明黑色
                guiGraphics.fill(x - 2, y - 2, x + width + 2, y + height + 2, bgColor);
            }
        }
    }
    
    /**
     * 绘制游标热点
     * 
     * @param stack 矩阵堆栈
     * @param screen 屏幕实例
     * @param x 热点X坐标
     * @param y 热点Y坐标
     */
    @Override
    public void drawCursorHotspot(CommonMatrixStack stack, CommonScreen screen, int x, int y) {
        screen.drawCenterString(stack, "+", x, y - fontHeight() / 2, Color.WHITE);
    }
    
    /**
     * 绘制配置界面标题和信息
     * 
     * @param stack 矩阵堆栈
     * @param screen 屏幕实例
     * @param width 屏幕宽度
     * @param height 屏幕高度
     */
    @Override
    public void drawConfigTitle(CommonMatrixStack stack, CommonScreen screen, int width, int height) {
        screen.drawCenterString(stack, CursorMod.MOD_NAME, width / 2f, height / 2f - 60f, Color.ORANGE, 2.5F);

        screen.drawRightString(stack, CursorMod.getInstance().getType().toString() + " - " + CursorMod.MOD_VERSION, width - 5,
                height - fontHeight() * 3 - 9, 0xffffffff);
        screen.drawRightString(stack, I18nHelper.get("cursormod.licence", CursorMod.MOD_LICENCE), width - 5,
                height - fontHeight() * 2 - 7, 0xffffffff);
        screen.drawRightString(stack, I18nHelper.get("cursormod.authors", CursorMod.MOD_AUTHORS), width - 5,
                height - fontHeight() - 5, 0xffffffff);
    }
    
    /**
     * 绘制动画提示文字
     * 
     * @param stack 矩阵堆栈
     * @param screen 屏幕实例
     * @param x 文字X中心坐标
     * @param y 文字Y坐标
     */
    @Override
    public void drawAnimationText(CommonMatrixStack stack, CommonScreen screen, int x, int y) {
        screen.drawCenterString(stack, "(" + I18nHelper.get("cursormod.gui.animate") + ")", x, y, Color.WHITE);
    }
    
    /**
     *
     * @param guiGraphics GuiGraphics实例
     * @param x 左上角X坐标
     * @param y 左上角Y坐标
     * @param width 宽度
     * @param height 高度
     * @param radius 圆角半径
     * @param color 颜色 (ARGB格式)
     */

    public static void drawRoundedRect(GuiGraphics guiGraphics, int x, int y, int width, int height, int radius, int color) {
        if (radius <= 0) {
            guiGraphics.fill(x, y, x + width, y + height, color);
            return;
        }
        radius = Math.min(radius, Math.min(width / 2, height / 2));
        guiGraphics.fill(x + radius, y, x + width - radius, y + height, color);
        guiGraphics.fill(x, y + radius, x + radius, y + height - radius, color);
        guiGraphics.fill(x + width - radius, y + radius, x + width, y + height - radius, color);
        drawQuarterCircle(guiGraphics, x + radius, y + radius, radius, color, 0); // 左上
        drawQuarterCircle(guiGraphics, x + width - radius, y + radius, radius, color, 1); // 右上
        drawQuarterCircle(guiGraphics, x + radius, y + height - radius, radius, color, 2); // 左下
        drawQuarterCircle(guiGraphics, x + width - radius, y + height - radius, radius, color, 3); // 右下
    }
    
    /**
     *
     * @param guiGraphics GuiGraphics实例
     * @param centerX 圆心X坐标
     * @param centerY 圆心Y坐标
     * @param radius 半径
     * @param color 颜色
     * @param corner 角落 (0=左上, 1=右上, 2=左下, 3=右下)
     */
    private static void drawQuarterCircle(GuiGraphics guiGraphics, int centerX, int centerY, int radius, int color, int corner) {
        for (int i = 0; i <= radius; i++) {
            for (int j = 0; j <= radius; j++) {
                if (i * i + j * j <= radius * radius) {
                    int pixelX, pixelY;
                    switch (corner) {
                        case 0: // 左上
                            pixelX = centerX - i;
                            pixelY = centerY - j;
                            break;
                        case 1: // 右上
                            pixelX = centerX + i;
                            pixelY = centerY - j;
                            break;
                        case 2: // 左下
                            pixelX = centerX - i;
                            pixelY = centerY + j;
                            break;
                        case 3: // 右下
                            pixelX = centerX + i;
                            pixelY = centerY + j;
                            break;
                        default:
                            continue;
                    }
                    guiGraphics.fill(pixelX, pixelY, pixelX + 1, pixelY + 1, color);
                }
            }
        }
    }
    
    /**
     *
     * @param guiGraphics GuiGraphics实例
     * @param x 按钮X坐标
     * @param y 按钮Y坐标
     * @param width 按钮宽度
     * @param height 按钮高度
     * @param hovered 是否悬停
     * @param enabled 是否启用
     */
    public static void drawRoundedButton(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean hovered, boolean enabled) {
        int radius = Math.min(10, Math.min(width / 4, height / 4)); // 增大圆角半径到10
        
        int backgroundColor, borderColor, shadowColor;
        
        if (!enabled) {
            // 禁用状态：更暗的灰色
            backgroundColor = 0x50202020;
            borderColor = 0x80404040;
            shadowColor = 0x00000000;
        } else if (hovered) {
            // 悬停状态：带蓝色调的深色半透明+发光边框
            backgroundColor = 0x90404050;
            borderColor = 0xFFa0a0ff;
            shadowColor = 0x40a0a0ff;
        } else {
            // 正常状态深色半透明
            backgroundColor = 0x80252525;
            borderColor = 0xB0555555;
            shadowColor = 0x30000000; // 轻微阴影
        }
        
        // 绘制阴影（如果有）
        if (shadowColor != 0x00000000) {
            drawSmoothRoundedRect(guiGraphics, x + 1, y + 1, width, height, radius, shadowColor);
        }
        
        // 绘制主背景
        drawSmoothRoundedRect(guiGraphics, x, y, width, height, radius, backgroundColor);
        
        // 绘制边框
        drawSmoothRoundedRectBorder(guiGraphics, x, y, width, height, radius, 1.5f, borderColor);
        
        // 绘制内部发光（悬停时）
        if (hovered && enabled) {
            int innerGlowColor = 0x20ffffff;
            drawSmoothRoundedRect(guiGraphics, x + 2, y + 2, width - 4, height - 4, radius - 2, innerGlowColor);
        }
    }

    private static void drawRoundedRectBorder(GuiGraphics guiGraphics, int x, int y, int width, int height, int radius, int color) {
        guiGraphics.fill(x + radius, y, x + width - radius, y + 1, color);
        guiGraphics.fill(x + radius, y + height - 1, x + width - radius, y + height, color);
        guiGraphics.fill(x, y + radius, x + 1, y + height - radius, color);
        guiGraphics.fill(x + width - 1, y + radius, x + width, y + height - radius, color);
        drawCornerBorder(guiGraphics, x + radius, y + radius, radius, color, 0);
        drawCornerBorder(guiGraphics, x + width - radius, y + radius, radius, color, 1);
        drawCornerBorder(guiGraphics, x + radius, y + height - radius, radius, color, 2);
        drawCornerBorder(guiGraphics, x + width - radius, y + height - radius, radius, color, 3);
    }
    
    private static void drawCornerBorder(GuiGraphics guiGraphics, int centerX, int centerY, int radius, int color, int corner) {
        for (int angle = 0; angle <= 90; angle += 2) {
            double rad = Math.toRadians(angle);
            int offsetX = (int) (Math.cos(rad) * radius);
            int offsetY = (int) (Math.sin(rad) * radius);
            
            int pixelX, pixelY;
            switch (corner) {
                case 0: // 左上
                    pixelX = centerX - offsetX;
                    pixelY = centerY - offsetY;
                    break;
                case 1: // 右上
                    pixelX = centerX + offsetX;
                    pixelY = centerY - offsetY;
                    break;
                case 2: // 左下
                    pixelX = centerX - offsetX;
                    pixelY = centerY + offsetY;
                    break;
                case 3: // 右下
                    pixelX = centerX + offsetX;
                    pixelY = centerY + offsetY;
                    break;
                default:
                    continue;
            }
            guiGraphics.fill(pixelX, pixelY, pixelX + 1, pixelY + 1, color);
        }
    }
    
    /**
     * 绘制平滑的圆角矩形（使用抗锯齿）
     * 
     * @param guiGraphics GuiGraphics实例
     * @param x 左上角X坐标
     * @param y 左上角Y坐标
     * @param width 宽度
     * @param height 高度
     * @param radius 圆角半径
     * @param color 颜色 (ARGB格式)
     */
    private static void drawSmoothRoundedRect(GuiGraphics guiGraphics, int x, int y, int width, int height, int radius, int color) {
        if (radius <= 0) {
            guiGraphics.fill(x, y, x + width, y + height, color);
            return;
        }
        
        radius = Math.min(radius, Math.min(width / 2, height / 2));
        
        // 绘制中心矩形区域
        guiGraphics.fill(x + radius, y, x + width - radius, y + height, color);
        guiGraphics.fill(x, y + radius, x + radius, y + height - radius, color);
        guiGraphics.fill(x + width - radius, y + radius, x + width, y + height - radius, color);
        
        // 绘制四个角的平滑圆角
        drawSmoothQuarterCircle(guiGraphics, x + radius, y + radius, radius, color, 0); // 左上
        drawSmoothQuarterCircle(guiGraphics, x + width - radius, y + radius, radius, color, 1); // 右上
        drawSmoothQuarterCircle(guiGraphics, x + radius, y + height - radius, radius, color, 2); // 左下
        drawSmoothQuarterCircle(guiGraphics, x + width - radius, y + height - radius, radius, color, 3); // 右下
    }
    
    /**
     * 绘制平滑的四分之一圆（使用抗锯齿）
     * 
     * @param guiGraphics GuiGraphics实例
     * @param centerX 圆心X坐标
     * @param centerY 圆心Y坐标
     * @param radius 半径
     * @param color 颜色
     * @param corner 角落 (0=左上, 1=右上, 2=左下, 3=右下)
     */
    private static void drawSmoothQuarterCircle(GuiGraphics guiGraphics, int centerX, int centerY, int radius, int color, int corner) {
        int alpha = (color >> 24) & 0xFF;
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;
        
        // 遍历可能包含圆的区域
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                // 计算当前点到圆心的距离
                float distance = (float) Math.sqrt(i * i + j * j);
                
                // 如果在圆内，计算抗锯齿alpha值
                if (distance <= radius + 0.5f) {
                    float antialiasAlpha = 1.0f;
                    
                    // 边缘抗锯齿
                    if (distance > radius - 0.5f) {
                        antialiasAlpha = radius + 0.5f - distance;
                    }
                    
                    int pixelX, pixelY;
                    switch (corner) {
                        case 0: // 左上
                            if (i > 0 || j > 0) continue;
                            pixelX = centerX + i;
                            pixelY = centerY + j;
                            break;
                        case 1: // 右上
                            if (i < 0 || j > 0) continue;
                            pixelX = centerX + i;
                            pixelY = centerY + j;
                            break;
                        case 2: // 左下
                            if (i > 0 || j < 0) continue;
                            pixelX = centerX + i;
                            pixelY = centerY + j;
                            break;
                        case 3: // 右下
                            if (i < 0 || j < 0) continue;
                            pixelX = centerX + i;
                            pixelY = centerY + j;
                            break;
                        default:
                            continue;
                    }
                    
                    // 应用抗锯齿alpha
                    int finalAlpha = (int) (alpha * antialiasAlpha);
                    int finalColor = (finalAlpha << 24) | (red << 16) | (green << 8) | blue;
                    
                    guiGraphics.fill(pixelX, pixelY, pixelX + 1, pixelY + 1, finalColor);
                }
            }
        }
    }
    
    /**
     * 绘制平滑的圆角矩形边框
     * 
     * @param guiGraphics GuiGraphics实例
     * @param x 左上角X坐标
     * @param y 左上角Y坐标
     * @param width 宽度
     * @param height 高度
     * @param radius 圆角半径
     * @param thickness 边框厚度
     * @param color 颜色
     */
    private static void drawSmoothRoundedRectBorder(GuiGraphics guiGraphics, int x, int y, int width, int height, int radius, float thickness, int color) {
        if (radius <= 0) {
            // 绘制普通矩形边框
            guiGraphics.fill(x, y, x + width, y + (int)thickness, color);
            guiGraphics.fill(x, y + height - (int)thickness, x + width, y + height, color);
            guiGraphics.fill(x, y, x + (int)thickness, y + height, color);
            guiGraphics.fill(x + width - (int)thickness, y, x + width, y + height, color);
            return;
        }
        
        radius = Math.min(radius, Math.min(width / 2, height / 2));
        int halfThick = (int)(thickness / 2);
        
        // 绘制四条边（直线部分）
        guiGraphics.fill(x + radius, y, x + width - radius, y + (int)thickness, color); // 上
        guiGraphics.fill(x + radius, y + height - (int)thickness, x + width - radius, y + height, color); // 下
        guiGraphics.fill(x, y + radius, x + (int)thickness, y + height - radius, color); // 左
        guiGraphics.fill(x + width - (int)thickness, y + radius, x + width, y + height - radius, color); // 右
        
        // 绘制四个角的平滑圆弧边框
        drawSmoothQuarterCircleBorder(guiGraphics, x + radius, y + radius, radius, thickness, color, 0); // 左上
        drawSmoothQuarterCircleBorder(guiGraphics, x + width - radius, y + radius, radius, thickness, color, 1); // 右上
        drawSmoothQuarterCircleBorder(guiGraphics, x + radius, y + height - radius, radius, thickness, color, 2); // 左下
        drawSmoothQuarterCircleBorder(guiGraphics, x + width - radius, y + height - radius, radius, thickness, color, 3); // 右下
    }
    
    /**
     * 绘制平滑的四分之一圆边框
     * 
     * @param guiGraphics GuiGraphics实例
     * @param centerX 圆心X坐标
     * @param centerY 圆心Y坐标
     * @param radius 半径
     * @param thickness 边框厚度
     * @param color 颜色
     * @param corner 角落 (0=左上, 1=右上, 2=左下, 3=右下)
     */
    private static void drawSmoothQuarterCircleBorder(GuiGraphics guiGraphics, int centerX, int centerY, int radius, float thickness, int color, int corner) {
        int alpha = (color >> 24) & 0xFF;
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;
        
        float innerRadius = radius - thickness;
        
        // 遍历可能包含圆环的区域
        for (int i = -radius - 1; i <= radius + 1; i++) {
            for (int j = -radius - 1; j <= radius + 1; j++) {
                float distance = (float) Math.sqrt(i * i + j * j);
                
                // 如果在圆环内
                if (distance >= innerRadius - 0.5f && distance <= radius + 0.5f) {
                    float antialiasAlpha = 1.0f;
                    
                    // 外边缘抗锯齿
                    if (distance > radius - 0.5f) {
                        antialiasAlpha = Math.min(antialiasAlpha, radius + 0.5f - distance);
                    }
                    
                    // 内边缘抗锯齿
                    if (distance < innerRadius + 0.5f) {
                        antialiasAlpha = Math.min(antialiasAlpha, distance - (innerRadius - 0.5f));
                    }
                    
                    if (antialiasAlpha <= 0) continue;
                    
                    int pixelX, pixelY;
                    switch (corner) {
                        case 0: // 左上
                            if (i > 0 || j > 0) continue;
                            pixelX = centerX + i;
                            pixelY = centerY + j;
                            break;
                        case 1: // 右上
                            if (i < 0 || j > 0) continue;
                            pixelX = centerX + i;
                            pixelY = centerY + j;
                            break;
                        case 2: // 左下
                            if (i > 0 || j < 0) continue;
                            pixelX = centerX + i;
                            pixelY = centerY + j;
                            break;
                        case 3: // 右下
                            if (i < 0 || j < 0) continue;
                            pixelX = centerX + i;
                            pixelY = centerY + j;
                            break;
                        default:
                            continue;
                    }
                    
                    // 应用抗锯齿alpha
                    int finalAlpha = (int) (alpha * antialiasAlpha);
                    int finalColor = (finalAlpha << 24) | (red << 16) | (green << 8) | blue;
                    
                    guiGraphics.fill(pixelX, pixelY, pixelX + 1, pixelY + 1, finalColor);
                }
            }
        }
    }
}
