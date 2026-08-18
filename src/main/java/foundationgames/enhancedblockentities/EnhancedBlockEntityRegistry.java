package foundationgames.enhancedblockentities;

import foundationgames.enhancedblockentities.client.render.BlockEntityRenderCondition;
import foundationgames.enhancedblockentities.client.render.BlockEntityRendererOverride;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;

public final class EnhancedBlockEntityRegistry {
    // Looked up once per block entity per frame, so it is keyed by Block (identity, open
    // addressing) and answers "is this ours" and "which override" in a single probe.
    public static final Reference2ObjectMap<Block, Entry> BLOCKS = new Reference2ObjectOpenHashMap<>();

    private EnhancedBlockEntityRegistry() {}

    public static void register(Block block, BlockEntityType<?> type, BlockEntityRenderCondition condition, BlockEntityRendererOverride renderer) {
        BLOCKS.put(block, new Entry(condition, renderer));
    }

    public static void clear() {
        BLOCKS.clear();
    }

    public record Entry(BlockEntityRenderCondition condition, BlockEntityRendererOverride renderer) {}
}
