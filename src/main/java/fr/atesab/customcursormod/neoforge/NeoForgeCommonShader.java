package fr.atesab.customcursormod.neoforge;

import java.util.function.Supplier;

import fr.atesab.customcursormod.common.handler.BasicHandler;
import fr.atesab.customcursormod.common.handler.CommonShader;
import com.mojang.blaze3d.pipeline.RenderPipeline;

public class NeoForgeCommonShader extends BasicHandler<Supplier<RenderPipeline>> implements CommonShader {

    public NeoForgeCommonShader(Supplier<RenderPipeline> handle) {
        super(handle);
    }

}
