package fr.atesab.customcursormod.neoforge;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import fr.atesab.customcursormod.common.CursorMod;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterRenderPipelinesEvent;

import java.util.function.Supplier;

@EventBusSubscriber(value = Dist.CLIENT, modid = CursorMod.MOD_ID)
public class NeoForgeRenderPipelines {
    public static final RenderPipeline.Snippet CURSOR_SNIPPET = RenderPipeline.builder(RenderPipelines.GUI_TEXTURED_SNIPPET)
            .withVertexShader("core/position_tex_color")
            .withFragmentShader("core/position_tex_color")
            .withSampler("Sampler0")
            .withBlend(BlendFunction.TRANSLUCENT)
            .withVertexFormat(DefaultVertexFormat.POSITION_TEX_COLOR, VertexFormat.Mode.QUADS)
            .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
            .buildSnippet();

    public static Supplier<RenderPipeline> CURSOR;

    @SubscribeEvent
    public static void onRegisterRenderPipelines(RegisterRenderPipelinesEvent event) {
        RenderPipeline pipeline = RenderPipeline.builder(NeoForgeRenderPipelines.CURSOR_SNIPPET)
                .withLocation(ResourceLocation.fromNamespaceAndPath("customcursor", "pipeline/cursor"))
                .build();
        CURSOR = () -> pipeline;
        event.registerPipeline(pipeline);
    }
}
