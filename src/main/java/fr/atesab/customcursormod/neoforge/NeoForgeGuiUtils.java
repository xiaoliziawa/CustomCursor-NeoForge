package fr.atesab.customcursormod.neoforge;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.ByteBufferBuilder;

import fr.atesab.customcursormod.common.handler.CommonMatrixStack;
import fr.atesab.customcursormod.common.handler.CommonShader;
import fr.atesab.customcursormod.common.handler.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.CoreShaders;
import net.minecraft.client.renderer.ShaderProgram;
import java.util.function.Supplier;

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
	 * @param x          x location
	 * @param y          y location
	 * @param u          x uv location
	 * @param v          y uv location
	 * @param uWidth     uv width
	 * @param vHeight    uv height
	 * @param width      width
	 * @param height     height
	 * @param tileWidth  tile width
	 * @param tileHeight tile height
	 * @param color      tile color
	 * @param useAlpha   use the alpha of the color
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

		ByteBufferBuilder byteBuffer = new ByteBufferBuilder(256);
		BufferBuilder bufferbuilder = new BufferBuilder(byteBuffer, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);

		bufferbuilder.addVertex((float)x, (float)(y + height), 0.0F)
				.setUv(u * scaleX, (v + (float) vHeight) * scaleY)
				.setColor(red, green, blue, alpha);

		bufferbuilder.addVertex((float)(x + width), (float)(y + height), 0.0F)
				.setUv((u + (float) uWidth) * scaleX, (v + (float) vHeight) * scaleY)
				.setColor(red, green, blue, alpha);

		bufferbuilder.addVertex((float)(x + width), (float)y, 0.0F)
				.setUv((u + (float) uWidth) * scaleX, v * scaleY)
				.setColor(red, green, blue, alpha);

		bufferbuilder.addVertex((float)x, (float)y, 0.0F)
				.setUv(u * scaleX, v * scaleY)
				.setColor(red, green, blue, alpha);

		BufferUploader.drawWithShader(bufferbuilder.build());
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

		ByteBufferBuilder byteBuffer = new ByteBufferBuilder(256);
		BufferBuilder bufferbuilder = new BufferBuilder(byteBuffer, VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

		RenderSystem.enableBlend();
		ShaderProgram shader = CoreShaders.POSITION_COLOR;
		RenderSystem.setShader(shader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);

		var mat = stack.<PoseStack>getHandle().last().pose();

		bufferbuilder.addVertex((float)right, (float)top, zLevel)
				.setColor(redRightTop, greenRightTop, blueRightTop, alphaRightTop);

		bufferbuilder.addVertex((float)left, (float)top, zLevel)
				.setColor(redLeftTop, greenLeftTop, blueLeftTop, alphaLeftTop);

		bufferbuilder.addVertex((float)left, (float)bottom, zLevel)
				.setColor(redLeftBottom, greenLeftBottom, blueLeftBottom, alphaLeftBottom);

		bufferbuilder.addVertex((float)right, (float)bottom, zLevel)
				.setColor(redRightBottom, greenRightBottom, blueRightBottom, alphaRightBottom);

		BufferUploader.drawWithShader(bufferbuilder.build());
		RenderSystem.disableBlend();
	}

	@Override
	public int fontHeight() {
		return Minecraft.getInstance().font.lineHeight;
	}

	@Override
	public void setShaderColor(float r, float g, float b, float a) {
		RenderSystem.setShaderColor(r, g, b, a);
	}

	@Override
	public void setShader(CommonShader shader) {
		@SuppressWarnings("unchecked")
		Supplier<ShaderProgram> supplier = (Supplier<ShaderProgram>)shader.getHandle();
		RenderSystem.setShader(supplier.get());
	}
}
