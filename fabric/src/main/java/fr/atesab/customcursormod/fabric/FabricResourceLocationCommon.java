package fr.atesab.customcursormod.fabric;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import fr.atesab.customcursormod.common.handler.ResourceLocationCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class FabricResourceLocationCommon extends ResourceLocationCommon {

    private final ResourceLocation resource;

    private GpuTextureView textureView;

    public FabricResourceLocationCommon(String link) {
        resource = ResourceLocation.parse(link);

    }

    public FabricResourceLocationCommon(ResourceLocation resource) {
        this.resource = resource;
    }

    private void bindTexture() {
        AbstractTexture abstractTexture = Minecraft.getInstance().getTextureManager().getTexture(resource);
        textureView = abstractTexture.getTextureView();
    }

    @Override
    public void setShaderTexture() {
        if (textureView == null) {
            bindTexture();
        }
        RenderSystem.setShaderTexture(0, textureView);
        FabricGuiUtils.setCurrentTexture(resource);
    }

    @Override
    public void bindForSetup() {
        if (textureView == null) {
            bindTexture();
        }
        RenderSystem.setShaderTexture(0, textureView);
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
