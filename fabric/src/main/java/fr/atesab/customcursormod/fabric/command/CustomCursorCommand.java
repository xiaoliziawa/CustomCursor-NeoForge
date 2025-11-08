package fr.atesab.customcursormod.fabric.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fr.atesab.customcursormod.common.gui.GuiConfigCursorMod;
import fr.atesab.customcursormod.common.handler.CommonScreen;
import fr.atesab.customcursormod.fabric.FabricBasicCommonScreen;
import fr.atesab.customcursormod.fabric.FabricCommonScreen;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;

public class CustomCursorCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
            ClientCommandManager.literal("customcursor")
                .executes(CustomCursorCommand::openConfigGui)
        );
    }

    private static int openConfigGui(CommandContext<FabricClientCommandSource> context) {
        
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            CommonScreen parent = new FabricBasicCommonScreen(minecraft.screen);
            CommonScreen configScreen = GuiConfigCursorMod.create(parent);
            if (configScreen instanceof FabricCommonScreen fabricScreen) {
                minecraft.setScreen(fabricScreen.getHandle());
            }
        });

        return 1;
    }
}
