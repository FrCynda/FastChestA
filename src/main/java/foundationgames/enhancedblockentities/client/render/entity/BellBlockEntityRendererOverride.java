package foundationgames.enhancedblockentities.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import foundationgames.enhancedblockentities.client.model.ModelIdentifiers;
import foundationgames.enhancedblockentities.client.render.BlockEntityRendererOverride;
import foundationgames.enhancedblockentities.util.EBEUtil;
//? if <= 1.21.6 {
/*import net.minecraft.client.renderer.MultiBufferSource;
*///?} else {
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
//?}
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
//? if <= 1.21.4 {
/*import net.minecraft.client.resources.model.BakedModel;
*///?} else {
//? if <= 1.21.11 {
/*import net.minecraft.client.renderer.block.model.BlockStateModel;
*///?} else {
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
//?}
//?}
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BellBlockEntityRendererOverride extends BlockEntityRendererOverride {
    //? if <= 1.21.4 {
    /*private BakedModel bellModel = null;
    *///?} else {
    private BlockStateModel bellModel = null;
    //?}

    @Override
    //? if <= 1.21.6 {
    /*public void render(BlockEntityRenderer<BlockEntity> renderer, BlockEntity blockEntity, float tickDelta, PoseStack matrices, MultiBufferSource output, int light, int overlay) {
    *///?} else {
    public void render(BlockEntityRenderer<BlockEntity, ?> renderer, BlockEntityRenderState renderState, BlockEntity blockEntity, float tickDelta, PoseStack matrices, SubmitNodeCollector output, int light, int overlay) {
    //?}
        if (bellModel == null) bellModel = getBellModel();
        if (bellModel != null && blockEntity instanceof BellBlockEntity self) {
            float ringTicks = (float)self.ticks + tickDelta;
            float bellPitch = 0.0F;
            float bellRoll = 0.0F;
            if (self.shaking) {
                float swingAngle = Mth.sin(ringTicks / (float)Math.PI) / (4.0F + ringTicks / 3.0F);
                if (self.clickDirection == Direction.NORTH) {
                    bellPitch = -swingAngle;
                } else if (self.clickDirection == Direction.SOUTH) {
                    bellPitch = swingAngle;
                } else if (self.clickDirection == Direction.EAST) {
                    bellRoll = -swingAngle;
                } else if (self.clickDirection == Direction.WEST) {
                    bellRoll = swingAngle;
                }
            }
            matrices.pushPose();
            matrices.translate(8f/16, 12f/16, 8f/16);
            EBEUtil.rotate(matrices, Axis.XP.rotation(bellPitch));
            EBEUtil.rotate(matrices, Axis.ZP.rotation(bellRoll));
            matrices.translate(-8f/16, -12f/16, -8f/16);
            EBEUtil.renderBakedModel(output, blockEntity.getBlockState(), matrices, bellModel, light, overlay);

            matrices.popPose();
        }
    }

    //? if <= 1.21.4 {
    /*private BakedModel getBellModel() {
        return ModelIdentifiers.getBakedModel(ModelIdentifiers.BELL_BODY);
    }
    *///?} else {
    private BlockStateModel getBellModel() {
        return ModelIdentifiers.getBakedModel(ModelIdentifiers.BELL_BODY);
    }
    //?}

    @Override
    public void onModelsReload() {
        bellModel = null;
    }
}
