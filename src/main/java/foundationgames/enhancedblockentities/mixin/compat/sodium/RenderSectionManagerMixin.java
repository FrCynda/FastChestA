package foundationgames.enhancedblockentities.mixin.compat.sodium;

//? if fabric || neoforge {
import foundationgames.enhancedblockentities.util.WorldUtil;
import foundationgames.enhancedblockentities.util.duck.ChunkRebuildTaskAccess;
import net.caffeinemc.mods.sodium.client.render.chunk.RenderSection;
import net.caffeinemc.mods.sodium.client.render.chunk.RenderSectionManager;
import net.minecraft.core.SectionPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
//? if <= 1.21.5 {
/*import net.caffeinemc.mods.sodium.client.render.chunk.compile.BuilderTaskOutput;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
*///?} else {
import net.caffeinemc.mods.sodium.client.render.chunk.compile.estimation.UploadResourceBudget;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.executor.ChunkJobCollector;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//?}

@Pseudo
@Mixin(value = RenderSectionManager.class, remap = false)
public class RenderSectionManagerMixin {
    //? if <= 1.21.5 {
    /*@ModifyVariable(method = "submitSectionTasks(Lnet/caffeinemc/mods/sodium/client/render/chunk/compile/executor/ChunkJobCollector;Lnet/caffeinemc/mods/sodium/client/render/chunk/ChunkUpdateType;Z)V",
            at = @At(value = "INVOKE", shift = At.Shift.BEFORE, ordinal = 0, target = "Lnet/caffeinemc/mods/sodium/client/render/chunk/RenderSection;isDisposed()Z"),
            index = 5, require = 0
    )
    private RenderSection enhanced_bes$compat_sodium$cacheUpdatingChunk(RenderSection section) {
        enhanced_bes$compat_sodium$cacheUpdatingChunk0(section);

        return section;
    }

    @ModifyVariable(method = "processChunkBuildResults",
            at = @At(value = "INVOKE_ASSIGN", shift = At.Shift.BEFORE, ordinal = 0, target = "Lnet/caffeinemc/mods/sodium/client/render/chunk/RenderSection;getTaskCancellationToken()Lnet/caffeinemc/mods/sodium/client/util/task/CancellationToken;"),
            index = 5, require = 0
    )
    private BuilderTaskOutput enhanced_bes$runPostRebuildTask(BuilderTaskOutput output) {
        ((ChunkRebuildTaskAccess) output.render).enhanced_bes$runAfterRebuildTask();

        return output;
    }
    *///?} else {
    @Inject(method = "submitSectionTask(Lnet/caffeinemc/mods/sodium/client/render/chunk/compile/executor/ChunkJobCollector;Lnet/caffeinemc/mods/sodium/client/render/chunk/RenderSection;ILnet/caffeinemc/mods/sodium/client/render/chunk/compile/estimation/UploadResourceBudget;Z)V",
            at = @At("HEAD"), require = 0)
    private void enhanced_bes$compat_sodium$cacheUpdatingChunk(ChunkJobCollector collector, RenderSection section,
            int updateType, UploadResourceBudget budget, boolean deferred, CallbackInfo ci) {
        enhanced_bes$compat_sodium$cacheUpdatingChunk0(section);
    }
    //?}

    @Unique
    private static void enhanced_bes$compat_sodium$cacheUpdatingChunk0(RenderSection section) {
        if (WorldUtil.CHUNK_UPDATE_TASKS.isEmpty()) return;

        var pos = SectionPos.of(section.getChunkX(), section.getChunkY(), section.getChunkZ());

        if (WorldUtil.CHUNK_UPDATE_TASKS.containsKey(pos)) {
            var task = WorldUtil.CHUNK_UPDATE_TASKS.remove(pos);
            ((ChunkRebuildTaskAccess) section).enhanced_bes$setTaskAfterRebuild(task);
        }
    }
}
//?}
