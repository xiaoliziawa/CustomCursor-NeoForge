package fr.atesab.customcursormod.neoforge;

import java.util.function.Supplier;

import fr.atesab.customcursormod.common.handler.BasicHandler;
import fr.atesab.customcursormod.common.handler.CommonShader;
import net.minecraft.client.renderer.ShaderInstance;

public class NeoForgeCommonShader extends BasicHandler<Supplier<ShaderInstance>> implements CommonShader {

    public NeoForgeCommonShader(Supplier<ShaderInstance> handle) {
        super(handle);
    }

}
