package fr.atesab.customcursormod.neoforge;

import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.TriState;

import java.util.function.Function;

public class NeoForgeRenderTypes {
    public static final RenderType CURSOR = RenderType.create(
            "cursor",
            2048,
            NeoForgeRenderPipelines.CURSOR,
            RenderType.CompositeState.builder()
                    .createCompositeState(false)
    );

    public static final Function<ResourceLocation, RenderType> CURSOR_TEXTURED = Util.memoize(
            resourceLocation -> RenderType.create(
                    "cursor",
                    2048,
                    NeoForgeRenderPipelines.CURSOR,
                    RenderType.CompositeState.builder()
                            .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, TriState.FALSE, false))
                            .createCompositeState(false)
            )
    );
}
