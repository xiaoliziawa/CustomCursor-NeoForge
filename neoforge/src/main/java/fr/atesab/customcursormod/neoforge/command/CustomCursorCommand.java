package fr.atesab.customcursormod.neoforge.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fr.atesab.customcursormod.common.gui.GuiConfigCursorMod;
import fr.atesab.customcursormod.common.handler.CommonScreen;
import fr.atesab.customcursormod.neoforge.NeoForgeBasicCommonScreen;
import fr.atesab.customcursormod.neoforge.NeoForgeCommonScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CustomCursorCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("customcursor")
                .executes(CustomCursorCommand::openConfigGui)
        );
    }

    private static int openConfigGui(CommandContext<CommandSourceStack> context) {
        
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.execute(() -> {
            CommonScreen parent = new NeoForgeBasicCommonScreen(minecraft.screen);
            CommonScreen configScreen = GuiConfigCursorMod.create(parent);
            if (configScreen instanceof NeoForgeCommonScreen neoForgeScreen) {
                minecraft.setScreen(neoForgeScreen.getHandle());
            }
        });

        return 1;
    }
}

