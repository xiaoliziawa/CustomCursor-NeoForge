package fr.atesab.customcursormod.neoforge;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterRenderPipelinesEvent;

import java.util.function.Supplier;

@EventBusSubscriber(value = Dist.CLIENT, modid = "customcursor")
public class CursorRenderPipelineRegistry {

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
