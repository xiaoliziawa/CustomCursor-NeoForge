package fr.atesab.customcursormod.neoforge;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import fr.atesab.customcursormod.common.handler.ResourceLocationCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class NeoForgeResourceLocationCommon extends ResourceLocationCommon {

    private final ResourceLocation resource;

    private GpuTexture texture;

    public NeoForgeResourceLocationCommon(String link) {
        resource = ResourceLocation.parse(link);

    }

    public NeoForgeResourceLocationCommon(ResourceLocation resource) {
        this.resource = resource;
    }

    private void bindTexture() {
        texture = Minecraft.getInstance().getTextureManager().getTexture(resource).getTexture();
    }

    @Override
    public void setShaderTexture() {
        if (texture == null) {
            bindTexture();
        }
        RenderSystem.setShaderTexture(0, texture);
    }

    @Override
    public void bindForSetup() {
        if (texture == null) {
            bindTexture();
        }
        RenderSystem.setShaderTexture(0, texture);
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
