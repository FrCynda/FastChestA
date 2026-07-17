package foundationgames.enhancedblockentities.platform;

//? if fabric {
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
//?} else if neoforge {
/*import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLPaths;
*///?} else if forge {
/*import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;
*///?}

import java.nio.file.Path;
import java.util.List;
import java.util.function.BiConsumer;

public enum Platform {;
    public static Path getGameDir() {
        //? if fabric {
        return FabricLoader.getInstance().getGameDir();
        //?} else {
        /*return FMLPaths.GAMEDIR.get();
        *///?}
    }

    public static Path getConfigDir() {
        //? if fabric {
        return FabricLoader.getInstance().getConfigDir();
        //?} else {
        /*return FMLPaths.CONFIGDIR.get();
        *///?}
    }

    public static List<Path> getModRootPaths(String modId) {
        //? if fabric {
        return FabricLoader.getInstance().getModContainer(modId)
                .map(ModContainer::getRootPaths).orElse(List.of());
        //?} else if neoforge && <= 1.21.6 {
        /*var info = ModList.get().getModFileById(modId);
        if (info == null) return List.of();
        return List.of(info.getFile().getSecureJar().getRootPath());
        *///?} else if neoforge && >= 1.21.9 {
        /*var info = ModList.get().getModFileById(modId);
        if (info == null) return List.of();
        return List.copyOf(info.getFile().getContents().getContentRoots());
        *///?} else if forge && >= 26.1 {
        /*var info = ModList.getModFileById(modId);
        if (info == null) return List.of();
        return List.of(info.getFile().getSecureJar().getRootPath());
        *///?} else {
        /*var info = ModList.get().getModFileById(modId);
        if (info == null) return List.of();
        return List.of(info.getFile().getSecureJar().getRootPath());
        *///?}
    }

    public static boolean isModLoaded(String modId) {
        //? if fabric {
        return FabricLoader.getInstance().isModLoaded(modId);
        //?} else if forge && >= 26.1 {
        /*return ModList.isLoaded(modId);
        *///?} else {
        /*return ModList.get().isLoaded(modId);
        *///?}
    }

    @SuppressWarnings("unchecked")
    public static <T> void forEachApiEntrypoint(String key, Class<T> type, BiConsumer<String, T> action) {
        //? if fabric {
        for (var container : FabricLoader.getInstance().getEntrypointContainers(key, type)) {
            action.accept(container.getProvider().getMetadata().getId(), container.getEntrypoint());
        }
        //?} else {
        
        //?}
    }
}
