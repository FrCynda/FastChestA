package foundationgames.enhancedblockentities.mixin;

import foundationgames.enhancedblockentities.EnhancedBlockEntities;
import foundationgames.enhancedblockentities.client.render.entity.ChestBlockEntityRendererOverride;
import foundationgames.enhancedblockentities.util.WorldUtil;
import foundationgames.enhancedblockentities.util.duck.AppearanceStateHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderChestBlockEntity.class)
public abstract class EnderChestBlockEntityMixin extends BlockEntity implements AppearanceStateHolder {
    @Unique private int enhanced_bes$modelState = 0;
    @Unique private int enhanced_bes$renderState = 0;

    private EnderChestBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "lidAnimateTick", at = @At(value = "TAIL"))
    private static void enhanced_bes$listenForOpenClose(Level world, BlockPos pos, BlockState state, EnderChestBlockEntity blockEntity, CallbackInfo ci) {
        if (!EnhancedBlockEntities.CONFIG.renderEnhancedChests) return;

        var lid = ChestBlockEntityRendererOverride.getLidAnimationHolder(blockEntity, 0.5f);
        // Each frame the lid is drawn lerped between last tick's openness and this tick's, so
        // it is only really shut once both ends are zero. Handing over to the static model at
        // this tick's value instead would cut the closing animation a frame short, and stopping
        // the ticker there would leave the lerp sweeping between the two with no tick left to
        // settle it -- the lid flutters.
        boolean shut = lid.getOpenNess(1f) <= 0 && lid.getOpenNess(0f) <= 0;
        int mState = shut ? 0 : 1;

        if (((AppearanceStateHolder)blockEntity).getModelState() != mState) {
            ((AppearanceStateHolder)blockEntity).updateAppearanceState(mState, world, pos);
        }

        // See ChestBlockEntityMixin: closed chests stop ticking until a block event wakes them.
        if (shut) {
            WorldUtil.setTicking(world, pos, blockEntity, false);
        }
    }

    @Inject(method = "triggerEvent", at = @At("RETURN"))
    private void enhanced_bes$wakeOnBlockEvent(int id, int type, CallbackInfoReturnable<Boolean> cir) {
        var self = (EnderChestBlockEntity)(Object)this;
        var world = self.getLevel();
        if (world == null || !world.isClientSide()) return;
        WorldUtil.setTicking(world, self.getBlockPos(), self, true);
    }

    @Override
    public int getModelState() {
        return enhanced_bes$modelState;
    }

    @Override
    public void setModelState(int state) {
        this.enhanced_bes$modelState = state;
    }

    @Override
    public int getRenderState() {
        return enhanced_bes$renderState;
    }

    @Override
    public void setRenderState(int state) {
        this.enhanced_bes$renderState = state;
    }
}
