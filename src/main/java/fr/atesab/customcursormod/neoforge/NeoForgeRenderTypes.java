package fr.atesab.customcursormod.neoforge;

import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public class NeoForgeRenderTypes {
    private static RenderType cursor;
    private static Function<ResourceLocation, RenderType> cursorTextured;

    public static RenderType CURSOR() {
        if (cursor == null) {
            cursor = RenderType.create(
                    "cursor",
                    2048,
                    NeoForgeRenderPipelines.CURSOR.get(),
                    RenderType.CompositeState.builder()
                            .createCompositeState(false)
            );
        }
        return cursor;
    }

    public static Function<ResourceLocation, RenderType> CURSOR_TEXTURED() {
        if (cursorTextured == null) {
            cursorTextured = Util.memoize(
                    resourceLocation -> RenderType.create(
                            "cursor",
                            2048,
                            NeoForgeRenderPipelines.CURSOR.get(),
                            RenderType.CompositeState.builder()
                                    .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false))
                                    .createCompositeState(false)
                    )
            );
        }
        return cursorTextured;
    }
}
