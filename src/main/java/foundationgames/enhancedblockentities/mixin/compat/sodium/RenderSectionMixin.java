package foundationgames.enhancedblockentities.mixin.compat.sodium;

//? if fabric || neoforge {
import foundationgames.enhancedblockentities.util.duck.ChunkRebuildTaskAccess;
import net.caffeinemc.mods.sodium.client.render.chunk.RenderSection;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
//? if >= 1.21.6 {
import net.caffeinemc.mods.sodium.client.render.chunk.data.BuiltSectionInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//?}

@Pseudo
@Mixin(value = RenderSection.class, remap = false)
public class RenderSectionMixin implements ChunkRebuildTaskAccess {
    private @Unique @Nullable Runnable enhanced_bes$taskAfterRebuild = null;

    //? if >= 1.21.6 {
    @Inject(method = "setInfo", at = @At("HEAD"), require = 0)
    private void enhanced_bes$compat_sodium$runPostRebuildTask(BuiltSectionInfo info, CallbackInfoReturnable<?> cir) {
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
//?}
