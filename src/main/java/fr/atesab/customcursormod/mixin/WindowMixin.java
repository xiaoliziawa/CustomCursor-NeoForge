package fr.atesab.customcursormod.mixin;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.platform.cursor.CursorType;
import fr.atesab.customcursormod.common.CursorMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {
    @Shadow
    private boolean allowCursorChanges;

    @Shadow
    private CursorType currentCursor;

    @Inject(method = "selectCursor", at = @At("RETURN"))
    private void onSelectCursor(CursorType cursor, CallbackInfo ci) {
        CursorMod mod = CursorMod.getInstance();
        if (mod != null) {
            mod.applyCursor();
        }
    }
}

