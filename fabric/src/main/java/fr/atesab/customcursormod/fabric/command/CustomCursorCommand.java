package fr.atesab.customcursormod.fabric.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fr.atesab.customcursormod.common.gui.GuiConfig;
import fr.atesab.customcursormod.common.gui.screen.CommonScreen;
import fr.atesab.customcursormod.fabric.gui.FabricBasicCommonScreen;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class CustomCursorCommand {
    private static int scheduledTicks = 0;

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext registryAccess) {
        dispatcher.register(
            literal("customcursor")
                .executes(CustomCursorCommand::openConfigGui)
        );
    }

    private static int openConfigGui(CommandContext<FabricClientCommandSource> context) {
        scheduledTicks = 2;
        return 1;
    }

    public static void tick() {
        if (scheduledTicks > 0) {
            scheduledTicks--;

            if (scheduledTicks == 0) {
                Minecraft minecraft = Minecraft.getInstance();
                CommonScreen parent = new FabricBasicCommonScreen(minecraft.screen);
                CommonScreen configScreen = GuiConfig.create(parent);

                if (configScreen instanceof CommonScreen screen) {
                    minecraft.setScreen(screen.getHandle());
                }
            }
        }
    }
}
