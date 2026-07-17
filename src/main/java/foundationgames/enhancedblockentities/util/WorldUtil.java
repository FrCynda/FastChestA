package foundationgames.enhancedblockentities.util;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum WorldUtil {
    EVENT_LISTENER;

    public static final Map<SectionPos, ExecutableRunnableHashSet> CHUNK_UPDATE_TASKS = new ConcurrentHashMap<>();
    private static final Map<ResourceKey<Level>, Long2ObjectMap<ExecutableRunnableHashSet>> TIMED_TASKS = new ConcurrentHashMap<>();

    // 26.2 routes setBlocksDirty through ModelManager.requiresRender, which is false for two equal
    // states, so the section is never queued for a remesh and renderState never arrives
    public static void rebuildChunk(Level world, BlockPos pos) {
        //? if <= 26.1 {
        /*var state = world.getBlockState(pos);
        Minecraft.getInstance().levelRenderer.blockChanged(world, pos, state, state, 8);
        *///?} else {
        if (world instanceof ClientLevel client) {
            client.setSectionDirtyWithNeighbors(
                    SectionPos.blockToSectionCoord(pos.getX()),
                    SectionPos.blockToSectionCoord(pos.getY()),
                    SectionPos.blockToSectionCoord(pos.getZ()));
        }
        //?}
    }

    public static void rebuildChunkAndThen(Level world, BlockPos pos, Runnable action) {
        CHUNK_UPDATE_TASKS.computeIfAbsent(SectionPos.of(pos), k -> new ExecutableRunnableHashSet()).add(action);
        rebuildChunk(world, pos);
    }

    public static void scheduleTimed(Level world, long time, Runnable action) {
        TIMED_TASKS.computeIfAbsent(world.dimension(), k -> new Long2ObjectOpenHashMap<>())
                .computeIfAbsent(time, k -> new ExecutableRunnableHashSet()).add(action);
    }

    public void onEndTick(ClientLevel world) {
        var key = world.dimension();

        if (TIMED_TASKS.containsKey(key)) {
            TIMED_TASKS.get(key).long2ObjectEntrySet().removeIf(entry -> {
                if (world.getGameTime() >= entry.getLongKey()) {
                    entry.getValue().run();
                    return true;
                }

                return false;
            });
        }
    }
}
