package foundationgames.enhancedblockentities.mixin;

import foundationgames.enhancedblockentities.util.WorldUtil;
import foundationgames.enhancedblockentities.util.duck.ChunkRebuildTaskAccess;
//? if <= 26.1 {
/*import net.minecraft.client.renderer.chunk.RenderRegionCache;
*///?} else {
import net.minecraft.client.renderer.chunk.RenderSectionRegion;
import net.minecraft.client.renderer.chunk.SectionMesh;
//?}
import net.minecraft.client.renderer.chunk.SectionRenderDispatcher;
import net.minecraft.core.SectionPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SectionRenderDispatcher.RenderSection.class)
public class BuiltChunkMixin implements ChunkRebuildTaskAccess {
    private @Unique
    @Nullable Runnable enhanced_bes$taskAfterRebuild = null;

    //? if <= 26.1 {
    /*@Inject(method = "createCompileTask", at = @At("HEAD"), require = 0)
    private void enhanced_bes$addPostRebuildTask(RenderRegionCache cache, CallbackInfoReturnable<SectionRenderDispatcher.RenderSection.CompileTask> cir) {
        this.enhanced_bes$claimPendingTasks();
    }
    *///?} else {
    @Inject(method = "createCompileTask(Lnet/minecraft/client/renderer/chunk/RenderSectionRegion;)Lnet/minecraft/client/renderer/chunk/SectionRenderDispatcher$RenderSection$SectionTask;", at = @At("HEAD"), require = 0)
    private void enhanced_bes$addPostRebuildTask(RenderSectionRegion region, CallbackInfoReturnable<?> cir) {
        this.enhanced_bes$claimPendingTasks();
    }
    //?}

    // Neoforge adds a second createCompileTask taking its AddSectionGeometryEvent renderers, and
    // which of the two the game calls varies between neoforge builds. Hook both, the claim is
    // idempotent so it does not matter if one delegates to the other
    //? if neoforge && >= 26.2 {
    /*@Inject(method = "createCompileTask(Lnet/minecraft/client/renderer/chunk/RenderSectionRegion;Ljava/util/List;)Lnet/minecraft/client/renderer/chunk/SectionRenderDispatcher$RenderSection$SectionTask;", at = @At("HEAD"), require = 0)
    private void enhanced_bes$addPostRebuildTaskWithRenderers(RenderSectionRegion region, java.util.List<?> renderers, CallbackInfoReturnable<?> cir) {
        this.enhanced_bes$claimPendingTasks();
    }
    *///?}

    @Unique
    private void enhanced_bes$claimPendingTasks() {
        if (WorldUtil.CHUNK_UPDATE_TASKS.isEmpty()) return;

        var self = (SectionRenderDispatcher.RenderSection) (Object) this;
        //? if <= 1.21.4 {
        /*var pos = SectionPos.of(self.getOrigin());
        *///?} else {
        var pos = SectionPos.of(self.getRenderOrigin());
        //?}

        if (WorldUtil.CHUNK_UPDATE_TASKS.containsKey(pos)) {
            this.enhanced_bes$setTaskAfterRebuild(WorldUtil.CHUNK_UPDATE_TASKS.remove(pos));
        }
    }

    //? if >= 26.2 {
    @Inject(method = "setSectionMesh", at = @At("HEAD"))
    private void enhanced_bes$runPostRebuildTask(SectionMesh mesh, CallbackInfoReturnable<SectionMesh> cir) {
        this.enhanced_bes$runAfterRebuildTask();
    }
    //?}

    @Override
    public Runnable enhanced_bes$getTaskAfterRebuild() {
        return enhanced_bes$taskAfterRebuild;
    }

    @Override
    public void enhanced_bes$setTaskAfterRebuild(Runnable task) {
        enhanced_bes$taskAfterRebuild = task;
    }
}
