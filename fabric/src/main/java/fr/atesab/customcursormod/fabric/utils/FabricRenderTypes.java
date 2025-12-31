package fr.atesab.customcursormod.fabric.utils;

import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

public class FabricRenderTypes {
    private static RenderType cursor;
    private static Function<Identifier, RenderType> cursorTextured;

    public static RenderType CURSOR() {
        if (cursor == null) {
            RenderSetup setup = RenderSetup.builder(FabricRenderPipelines.CURSOR.get())
                    .bufferSize(2048)
                    .createRenderSetup();
            cursor = RenderType.create("cursor", setup);
        }
        return cursor;
    }

    public static Function<Identifier, RenderType> CURSOR_TEXTURED() {
        if (cursorTextured == null) {
            cursorTextured = Util.memoize(
                    identifier -> {
                        RenderSetup setup = RenderSetup.builder(FabricRenderPipelines.CURSOR.get())
                                .withTexture("Sampler0", identifier)
                                .bufferSize(2048)
                                .createRenderSetup();
                        return RenderType.create("cursor_textured", setup);
                    }
            );
        }
        return cursorTextured;
    }
}
