package fr.atesab.customcursormod.fabric;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;

import java.util.function.Supplier;


public class FabricRenderPipelines {
    public static final RenderPipeline.Snippet CURSOR_SNIPPET = RenderPipeline.builder(RenderPipelines.GUI_TEXTURED_SNIPPET)
            .withVertexShader("core/position_tex_color")
            .withFragmentShader("core/position_tex_color")
            .withSampler("Sampler0")
            .withBlend(BlendFunction.TRANSLUCENT)
            .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
            .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
            .buildSnippet();

    public static Supplier<RenderPipeline> CURSOR = () -> RenderPipelines.register(RenderPipeline.builder(FabricRenderPipelines.CURSOR_SNIPPET)
            .withLocation(Identifier.fromNamespaceAndPath("customcursor", "pipeline/cursor"))
            .build());
}
