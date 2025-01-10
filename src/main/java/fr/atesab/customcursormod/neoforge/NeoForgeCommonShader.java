package fr.atesab.customcursormod.neoforge;

import java.util.function.Supplier;

import fr.atesab.customcursormod.common.handler.BasicHandler;
import fr.atesab.customcursormod.common.handler.CommonShader;
import net.minecraft.client.renderer.ShaderProgram;

public class NeoForgeCommonShader extends BasicHandler<Supplier<ShaderProgram>> implements CommonShader {

    public NeoForgeCommonShader(Supplier<ShaderProgram> handle) {
        super(handle);
    }

}
