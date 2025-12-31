package fr.atesab.customcursormod.fabric;

import fr.atesab.customcursormod.common.CursorMod;
import fr.atesab.customcursormod.common.config.CursorConfig;
import fr.atesab.customcursormod.common.cursor.CursorClick;
import fr.atesab.customcursormod.common.cursor.CursorType;
import fr.atesab.customcursormod.common.cursor.SelectZone;
import fr.atesab.customcursormod.common.handler.*;
import fr.atesab.customcursormod.common.utils.I18nHelper;
import fr.atesab.customcursormod.fabric.command.CustomCursorCommand;
import fr.atesab.customcursormod.fabric.gui.FabricGuiSelectZone;
import fr.atesab.customcursormod.fabric.mixin.AbstractContainerScreenAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ActiveTextCollector;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FabricCursorMod implements ClientModInitializer {
    private final CursorMod mod = new CursorMod(GameType.FABRIC);

    static {
        SelectZone.SUPPLIER.forType(GameType.FABRIC,
                o -> new FabricGuiSelectZone(o.xPosition, o.yPosition, o.width, o.height));
        GuiUtils.SUPPLIER.forType(GameType.FABRIC, FabricGuiUtils::getFabric);
        TranslationCommonText.SUPPLIER.forType(GameType.FABRIC,
                obj -> new FabricTranslationCommonTextImpl(obj.format, obj.args));
        StringCommonText.SUPPLIER.forType(GameType.FABRIC, FabricStringCommonTextImpl::new);
        ResourceLocationCommon.SUPPLIER.forType(GameType.FABRIC, FabricResourceLocationCommon::new);
        CommonButton.SUPPLIER.forType(GameType.FABRIC, FabricCommonButton::new);
        CommonTextField.SUPPLIER.forType(GameType.FABRIC, FabricCommonTextField::new);
        CommonScreen.SUPPLIER.forType(GameType.FABRIC, FabricCommonScreen::new);
        CommonScreen.SUPPLIER_CURRENT.forType(GameType.FABRIC, v -> {
            Screen screen = Minecraft.getInstance().screen;
            if (screen instanceof FabricCommonScreen.FabricCommonScreenHandler handler) {
                return handler.getCommonScreen();
            }
            return CommonScreen.createNull();
        });
        I18nHelper.SUPPLIER.forType(GameType.FABRIC,
                obj -> I18n.get(obj.format, obj.args));
    }

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(CustomCursorCommand::register);

        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            mod.waiter.tick();
            CustomCursorCommand.tick();
        });

        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            this.setup();
        });

        ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            mod.forceNextCursor();

            ScreenEvents.afterRender(screen).register((screen1, guiGraphics, mouseX, mouseY, tickDelta) -> {
                onDrawScreen(client, screen1, guiGraphics, mouseX, mouseY, tickDelta);
            });

            ScreenEvents.afterTick(screen).register(screen1 -> {
                if (!mod.getCursorClicks().isEmpty() && client.gui == null) {
                    mod.getCursorClicks().clear();
                }
            });

            ScreenMouseEvents.afterMouseClick(screen).register((screen1, mouseButtonEvent, consumed) -> {
                if (mouseButtonEvent.button() == 0 && mod.getConfig().clickAnimation) {
                    mod.getCursorClicks().add(new CursorClick(mouseButtonEvent.x(), mouseButtonEvent.y()));
                    return true;
                }
                return false;
            });
        });
    }

//    private void checkModList(Screen screen) {
//        // enabling the config button
//        if (screen instanceof ModListScreen) {
//            ModListWidget.ModEntry entry = getFirstFieldOfTypeInto(ModListWidget.ModEntry.class, screen);
//            if (entry != null) {
//                var info = entry.getInfo();
//                if (info != null) {
//                    Optional<? extends ModContainer> op = ModList.get().getModContainerById(info.getModId());
//                    if (op.isPresent()) {
//                        boolean value = op.get().getCustomExtension(IConfigScreenFactory.class).isPresent();
//                        String configText = I18nHelper.get("fml.menu.mods.config");
//                        for (var b : screen.children())
//                            if (b instanceof Button && ((Button) b).getMessage().getString().equals(configText))
//                                ((Button) b).active = value;
//                    }
//                }
//            }
//        }
//    }

    private List<Field[]> getDeclaredField(Class<?> cls) {
        List<Field[]> l = new ArrayList<>();
        l.add(cls.getDeclaredFields());
        while (!cls.equals(Object.class)) {
            cls = cls.getSuperclass();
            l.add(cls.getDeclaredFields());
        }
        return l;
    }

    @SuppressWarnings("unchecked")
    private <T> T getFirstFieldOfTypeInto(Class<T> cls, Object obj) {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.getType() == cls)
                try {
                    return (T) f.get(obj);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    return null;
                }
        }
        return null;
    }

    private boolean isHover(int mouseX, int mouseY, int x, int y, int width, int height) {
        x = Math.min(x + width, x);
        y = Math.min(y + height, y);
        width = Math.abs(width);
        height = Math.abs(height);
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    private boolean isHoverButton(int mouseX, int mouseY, AbstractButton button) {
        return button != null && button.visible && button.active
                && isHover(mouseX, mouseY, button.getX(), button.getY(), button.getWidth(), button.getHeight());
    }

    private boolean isHoverTextField(int mouseX, int mouseY, EditBox textField) {
        return textField != null && textField.isVisible()
                && isHover(mouseX, mouseY, textField.getX(), textField.getY(), textField.getWidth(), textField.getHeight());
    }

    public void onDrawScreen(Minecraft minecraft, Screen gui, GuiGraphics guiGraphics, int mouseX, int mouseY, float tickDelta) {
        MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
        bufferSource.getBuffer(FabricRenderTypes.CURSOR());

        CursorType newCursorType = CursorType.POINTER;
        if (mod.getConfig().dynamicCursor) {
            if (gui instanceof FabricCommonScreen.FabricCommonScreenHandler handle) { // Our menu
                CommonScreen cs = handle.getCommonScreen();
                for (CommonElement o : cs.childrens) {
                    if (!o.isEnable())
                        continue;
                    if (o.isHover(mouseX, mouseY)) {
                        if (o instanceof CommonTextField) {
                            newCursorType = CursorType.BEAM;
                        } else if (o instanceof CommonButton) {
                            newCursorType = CursorType.HAND;
                        } else if (o instanceof SelectZone) {
                            newCursorType = CursorType.CROSS;
                        }
                    }
                }
            } else
                for (Field[] fa : getDeclaredField(gui.getClass()))
                    for (Field f : fa) {
                        try {
                            f.setAccessible(true);
                            Object o = f.get(gui);
                            if (o == null) {
                                continue;
                            }

                            if (o instanceof EditBox) {
                                if (isHoverTextField(mouseX, mouseY, (EditBox) o))
                                    newCursorType = CursorType.BEAM;
                            } else if (o instanceof AbstractButton b) {
                                if (isHoverButton(mouseX, mouseY, b))
                                    newCursorType = CursorType.HAND;
                            } else if (o instanceof SelectZone selectZone) {
                                if (isHover(mouseX, mouseY, selectZone.getXPosition(),
                                        selectZone.getYPosition(), selectZone.getWidth(), selectZone.getHeight())
                                        && selectZone.isEnable())
                                    newCursorType = CursorType.CROSS;
                            } else if (o instanceof Iterable) {
                                for (Object e : (Iterable<?>) o)
                                    if (e instanceof AbstractButton b) {
                                        if (isHoverButton(mouseX, mouseY, b))
                                            newCursorType = CursorType.HAND;
                                    } else if (e instanceof EditBox b) {
                                        if (isHoverTextField(mouseX, mouseY, b))
                                            newCursorType = CursorType.BEAM;
                                    } else if (e instanceof SelectZone selectZone) {
                                        if (selectZone.isHover(mouseX, mouseY) && selectZone.isEnable())
                                            newCursorType = CursorType.CROSS;
                                    } else
                                        break;
                            }
                        } catch (Exception e) {
                            // ignore
                        }
                    }
            if (gui instanceof AbstractContainerScreen<?> container) {
                LocalPlayer player = minecraft.player;
                if (player != null && !player.containerMenu.getCarried().getItem().equals(Items.AIR))
                    newCursorType = CursorType.HAND_GRAB;
                else if (((AbstractContainerScreenAccessor) container).getHoveredSlot() != null && ((AbstractContainerScreenAccessor) container).getHoveredSlot().hasItem())
                    newCursorType = CursorType.HAND;
            } else if (gui instanceof ChatScreen) {
                // 1.21.11 API: 使用 ActiveTextCollector.ClickableStyleFinder 获取聊天中的可点击样式
                Minecraft mc = minecraft;
                int mx = (int) (mc.mouseHandler.xpos() * (double) mc.getWindow().getGuiScaledWidth()
                        / (double) mc.getWindow().getScreenWidth());
                int my = (int) (mc.mouseHandler.ypos() * (double) mc.getWindow().getGuiScaledHeight()
                        / (double) mc.getWindow().getScreenHeight());
                ActiveTextCollector.ClickableStyleFinder finder = new ActiveTextCollector.ClickableStyleFinder(mc.font, mx, my);
                mc.gui.getChat().captureClickableText(finder, mc.getWindow().getGuiScaledHeight(), mc.gui.getGuiTicks(), true);
                Style style = finder.result();
                if (style != null && style.getClickEvent() != null)
                    newCursorType = CursorType.HAND;
            }

            CommonScreen commonScreen;
            if (gui instanceof FabricCommonScreen.FabricCommonScreenHandler handler) {
                commonScreen = handler.getCommonScreen();
            } else {
                commonScreen = new FabricBasicCommonScreen(gui);
            }

            for (CursorType cursorType : mod.getCursors().keySet())
                if (cursorType.getCursorTester() != null && cursorType.getCursorTester().testCursor(newCursorType,
                        commonScreen, mouseX, mouseY, tickDelta)) {
                    newCursorType = cursorType;
                    break;
                }
        }
        mod.changeCursor(newCursorType);


        if (mod.getConfig().clickAnimation) {
            Iterator<CursorClick> iterator = mod.getCursorClicks().iterator();
            while (iterator.hasNext()) {
                CursorClick cursorClick = iterator.next();
                int posX = (int) cursorClick.getPosX();
                int posY = (int) cursorClick.getPosY();
                
                try {
                    Identifier texture = Identifier.withDefaultNamespace("textures/gui/click_" + cursorClick.getImage() + ".png");
                    guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture, posX - 8, posY - 8, 0, 0, 16, 16, 16, 16);
                } catch (Exception e) {
                    // 静默处理异常
                }

                cursorClick.descreaseTime(tickDelta);
                if (cursorClick.getTime() <= 0) {
                    iterator.remove();
                }
            }
        }
    }

    private void setup() {
        File saveDir = new File(Minecraft.getInstance().gameDirectory, "config");
        try {
            Files.createDirectories(saveDir.toPath());
        } catch (IOException e) {
            throw new RuntimeException("can't create directories: " + saveDir, e);
        }
        File save = new File(saveDir, CursorMod.MOD_ID + ".json");
        mod.getConfig().sync(save);
        mod.getConfig().sync(save);
        mod.getCursors().values().forEach(CursorConfig::getCursor); // force allocation
        mod.loadData(Minecraft.getInstance().getWindow().handle());
    }
}
