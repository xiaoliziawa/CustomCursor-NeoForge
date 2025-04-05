package fr.atesab.customcursormod.neoforge;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.handler.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class NeoForgeGuiUtils extends GuiUtils {

    private NeoForgeGuiUtils() {
    }

    private static final NeoForgeGuiUtils instance = new NeoForgeGuiUtils();

    /**
     * @return the instance
     */
    public static NeoForgeGuiUtils getForge() {
        return instance;
    }

    /**
     * Draws a scaled, textured, tiled modal rect at z = 0. This method isn't used
     * anywhere in vanilla code.
     *
     * @param resourceLocation texture resourceLocation
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
        float scaleX = 1.0F / tileWidth;
        float scaleY = 1.0F / tileHeight;
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;
        int alpha = useAlpha ? (color >> 24) : 0xff;

        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        VertexConsumer vertexBuffer = bufferSource.getBuffer(NeoForgeRenderTypes.CURSOR);

        vertexBuffer.addVertex((float) x, (float) (y + height), 0.0F)
                .setUv(u * scaleX, (v + (float) vHeight) * scaleY)
                .setColor(red, green, blue, alpha);

        vertexBuffer.addVertex((float) (x + width), (float) (y + height), 0.0F)
                .setUv((u + (float) uWidth) * scaleX, (v + (float) vHeight) * scaleY)
                .setColor(red, green, blue, alpha);

        vertexBuffer.addVertex((float) (x + width), (float) y, 0.0F)
                .setUv((u + (float) uWidth) * scaleX, v * scaleY)
                .setColor(red, green, blue, alpha);

        vertexBuffer.addVertex((float) x, (float) y, 0.0F)
                .setUv(u * scaleX, v * scaleY)
                .setColor(red, green, blue, alpha);

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
        VertexConsumer vertexBuffer = bufferSource.getBuffer(RenderType.gui());


        vertexBuffer.addVertex((float) right, (float) top, zLevel)
                .setColor(redRightTop, greenRightTop, blueRightTop, alphaRightTop);

        vertexBuffer.addVertex((float) left, (float) top, zLevel)
                .setColor(redLeftTop, greenLeftTop, blueLeftTop, alphaLeftTop);

        vertexBuffer.addVertex((float) left, (float) bottom, zLevel)
                .setColor(redLeftBottom, greenLeftBottom, blueLeftBottom, alphaLeftBottom);

        vertexBuffer.addVertex((float) right, (float) bottom, zLevel)
                .setColor(redRightBottom, greenRightBottom, blueRightBottom, alphaRightBottom);

    }

    @Override
    public int fontHeight() {
        return Minecraft.getInstance().font.lineHeight;
    }

    @Override
    public void setShaderColor(float r, float g, float b, float a) {
        RenderSystem.setShaderColor(r, g, b, a);
    }
}
