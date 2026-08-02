package foundationgames.enhancedblockentities.util;

import foundationgames.enhancedblockentities.EnhancedBlockEntities;
//? if >= 1.21.4 {
import net.minecraft.client.renderer.blockentity.ChestRenderer;
//?}

public enum DateUtil {;
    public static boolean isChristmas() {
        String config = EnhancedBlockEntities.CONFIG.christmasChests;
        if (config.equals("disabled")) return false;
        if (config.equals("forced")) return true;
        //? if >= 1.21.4 {
        return ChestRenderer.xmasTextures();
        //?} else {
        /*// ChestRenderer.xmasTextures() isn't public before 1.21.4, so replicate vanilla's own check.
        var now = java.util.Calendar.getInstance();
        return now.get(java.util.Calendar.MONTH) + 1 == 12
                && now.get(java.util.Calendar.DAY_OF_MONTH) >= 24
                && now.get(java.util.Calendar.DAY_OF_MONTH) <= 26;
        *///?}
    }
}
