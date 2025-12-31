package fr.atesab.customcursormod.fabric.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import fr.atesab.customcursormod.common.gui.GuiConfig;
import fr.atesab.customcursormod.fabric.gui.FabricBasicCommonScreen;

public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> GuiConfig.create(new FabricBasicCommonScreen(screen)).getHandle();
    }
}
