package fr.atesab.customcursormod.neoforge;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import com.mojang.blaze3d.systems.RenderSystem;

import fr.atesab.customcursormod.common.handler.ResourceLocationCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;

public class NeoForgeResourceLocationCommon extends ResourceLocationCommon {

	private final ResourceLocation resource;

	public NeoForgeResourceLocationCommon(String link) {
		resource = ResourceLocation.parse(link);
	}

	public NeoForgeResourceLocationCommon(ResourceLocation resource) {
		this.resource = resource;
	}

	@Override
	public void setShaderTexture() {
		TextureManager textureManager = Minecraft.getInstance().getTextureManager();
		AbstractTexture texture = textureManager.getTexture(resource);
		RenderSystem.setShaderTexture(0, texture.getTexture());
	}

	@Override
	public void bindForSetup() {
		TextureManager textureManager = Minecraft.getInstance().getTextureManager();
		AbstractTexture texture = textureManager.getTexture(resource);
		RenderSystem.setShaderTexture(0, texture.getTexture());
	}

	@Override
	public InputStream openStream() throws IOException {
		Optional<Resource> res = Minecraft.getInstance().getResourceManager().getResource(resource);
		if (res.isEmpty()) {
			return null;
		}
		return res.get().open();
	}
}
