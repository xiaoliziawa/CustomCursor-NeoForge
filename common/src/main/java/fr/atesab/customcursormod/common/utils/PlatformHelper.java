package fr.atesab.customcursormod.common.utils;

public interface PlatformHelper {
    static PlatformHelper getInstance() {
        return ServiceHelper.loadService(PlatformHelper.class);
    }

    String getModName(String modId);

    String getModVersion(String modId);

    String getModAuthors(String modId);

    String getModLicense(String modId);
}
