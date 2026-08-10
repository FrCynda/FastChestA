package foundationgames.enhancedblockentities.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LevelChunk.class)
public interface LevelChunkAccessor {
    @Invoker("removeBlockEntityTicker")
    void enhanced_bes$removeTicker(BlockPos pos);

    @Invoker("updateBlockEntityTicker")
    <T extends BlockEntity> void enhanced_bes$updateTicker(T blockEntity);
}
