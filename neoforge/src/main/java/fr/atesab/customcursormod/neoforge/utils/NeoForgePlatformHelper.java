package fr.atesab.customcursormod.neoforge.utils;

import com.google.auto.service.AutoService;
import fr.atesab.customcursormod.common.utils.PlatformHelper;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.IModInfo;

import java.util.Optional;

@AutoService(PlatformHelper.class)
public class NeoForgePlatformHelper implements PlatformHelper {
    @Override
    public String getModName(String modId) {
        return ModMetadata.getModMetadata(modId).getModInfo().getDisplayName();
    }

    @Override
    public String getModVersion(String modId) {
        return ModMetadata.getModMetadata(modId).getModInfo().getVersion().toString();
    }

    @Override
    public String getModAuthors(String modId) {
        Optional<String> authorsOptional = ModMetadata.getModMetadata(modId).getModInfo().getConfig().getConfigElement("authors")
                .map(String::valueOf);

        return authorsOptional.orElse("");
    }

    @Override
    public String getModLicense(String modId) {
        return ModMetadata.getModMetadata(modId).getModInfo().getOwningFile().getLicense();
    }

    private static class ModMetadata {
        private static ModContainer modContainer;
        private static IModInfo modInfo;

        private ModMetadata(String modId) {
            modContainer = ModList.get().getModContainerById(modId).orElseThrow();
            modInfo = modContainer.getModInfo();
        }

        public static ModMetadata getModMetadata(String modId) {
            return new ModMetadata(modId);
        }

        public ModContainer getModContainer() {
            return modContainer;
        }

        public IModInfo getModInfo() {
            return modInfo;
        }
    }
}
