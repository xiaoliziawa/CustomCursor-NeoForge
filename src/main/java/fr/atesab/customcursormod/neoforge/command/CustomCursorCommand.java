package fr.atesab.customcursormod.neoforge.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import fr.atesab.customcursormod.common.gui.GuiConfigCursorMod;
import fr.atesab.customcursormod.common.handler.CommonScreen;
import fr.atesab.customcursormod.neoforge.NeoForgeBasicCommonScreen;
import fr.atesab.customcursormod.neoforge.NeoForgeCommonScreen;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

/**
 * /customcursor 命令 - 打开自定义光标配置GUI
 */
public class CustomCursorCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("customcursor")
                .executes(CustomCursorCommand::openConfigGui)
        );
    }

    private static int openConfigGui(CommandContext<CommandSourceStack> context) {
        // 直接在客户端线程上打开GUI，不需要检查 level
        net.minecraft.client.Minecraft minecraft = net.minecraft.client.Minecraft.getInstance();
        minecraft.execute(() -> {
            // 使用当前屏幕创建一个包装器，避免 createNull() 的无限递归
            // 如果当前没有屏幕，传递 null 给 NeoForgeBasicCommonScreen
            CommonScreen parent = new NeoForgeBasicCommonScreen(minecraft.screen);
            CommonScreen configScreen = GuiConfigCursorMod.create(parent);
            // 将 CommonScreen 转换为 NeoForgeCommonScreen 以获取实际的 Screen
            if (configScreen instanceof NeoForgeCommonScreen neoForgeScreen) {
                minecraft.setScreen(neoForgeScreen.getHandle());
            }
        });

        return 1;
    }
}

