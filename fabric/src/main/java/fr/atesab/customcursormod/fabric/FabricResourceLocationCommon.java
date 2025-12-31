package fr.atesab.customcursormod.fabric;

import fr.atesab.customcursormod.common.handler.ResourceLocationCommon;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class FabricResourceLocationCommon extends ResourceLocationCommon {

    private final Identifier resource;

    public FabricResourceLocationCommon(String link) {
        resource = Identifier.parse(link);
    }

    public FabricResourceLocationCommon(Identifier resource) {
        this.resource = resource;
    }

    @Override
    public void setShaderTexture() {
        // 保存纹理标识符，供 GuiGraphics.blit() 使用
        FabricGuiUtils.setCurrentTexture(resource);
    }

    @Override
    public void bindForSetup() {
        // 保存纹理标识符，供 GuiGraphics.blit() 使用
        FabricGuiUtils.setCurrentTexture(resource);
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
