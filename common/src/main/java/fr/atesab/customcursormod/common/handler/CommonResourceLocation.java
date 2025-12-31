package fr.atesab.customcursormod.common.handler;

import fr.atesab.customcursormod.common.gui.GuiUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class CommonResourceLocation {
	public static final CommonSupplier<String, CommonResourceLocation> SUPPLIER = new CommonSupplier<>(false);

	private final Identifier resource;

	public CommonResourceLocation(String link) {
		resource = Identifier.parse(link);
	}

	public CommonResourceLocation(Identifier resource) {
		this.resource = resource;
	}

	public static CommonResourceLocation create(String link) {
		return SUPPLIER.fetch(link);
	}

	/**
	 * bind the texture
	 */
	public void setShaderTexture() {
		// 保存纹理标识符，供 GuiGraphics.blit() 使用
		GuiUtils.setCurrentTexture(resource);
	}

	/**
	 * bind the texture
	 */
	public void bindForSetup() {
		// 保存纹理标识符，供 GuiGraphics.blit() 使用
		GuiUtils.setCurrentTexture(resource);
	}

	/**
	 * @return open the resource location as a stream
	 */
	public InputStream openStream() throws IOException {
		Optional<Resource> res = Minecraft.getInstance().getResourceManager().getResource(resource);
		if (res.isEmpty()) {
			return null;
		}
		return res.get().open();
	}
}
