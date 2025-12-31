package fr.atesab.customcursormod.fabric.utils;

import com.google.auto.service.AutoService;
import fr.atesab.customcursormod.common.utils.PlatformHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.fabricmc.loader.api.metadata.Person;

import java.util.stream.Collectors;


@AutoService(PlatformHelper.class)
public class FabricPlatformHelper implements PlatformHelper {
    @Override
    public String getModName(String modId) {
        return ModInfo.getModInfo(modId).getModMetadata().getName();
    }

    @Override
    public String getModVersion(String modId) {
        return ModInfo.getModInfo(modId).getModMetadata().getVersion().getFriendlyString();
    }

    @Override
    public String getModAuthors(String modId) {
        return ModInfo.getModInfo(modId).getModMetadata().getAuthors().stream().map(Person::getName).collect(Collectors.joining());
    }

    @Override
    public String getModLicense(String modId) {
        return ModInfo.getModInfo(modId).getModMetadata().getLicense().stream().collect(Collectors.joining());
    }

    private static class ModInfo {
        private static ModContainer modContainer;
        private static ModMetadata modMetadata;

        private ModInfo(String modId) {
            modContainer = FabricLoader.getInstance().getModContainer(modId).orElseThrow();
            modMetadata = modContainer.getMetadata();
        }

        public static ModInfo getModInfo(String modId) {
            return new ModInfo(modId);
        }

        public ModContainer getModContainer() {
            return modContainer;
        }

        public ModMetadata getModMetadata() {
            return modMetadata;
        }
    }
}
