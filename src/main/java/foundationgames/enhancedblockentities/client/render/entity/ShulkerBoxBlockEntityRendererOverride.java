package foundationgames.enhancedblockentities.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
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
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ShulkerBoxBlockEntityRendererOverride extends BlockEntityRendererOverride {
    //? if <= 1.21.4 {
    /*private final Map<DyeColor, BakedModel> models = new HashMap<>();
    private final Consumer<Map<DyeColor, BakedModel>> modelMapFiller;

    public ShulkerBoxBlockEntityRendererOverride(Consumer<Map<DyeColor, BakedModel>> modelMapFiller) {
    *///?} else {
    private final Map<DyeColor, BlockStateModel> models = new HashMap<>();
    private final Consumer<Map<DyeColor, BlockStateModel>> modelMapFiller;

    public ShulkerBoxBlockEntityRendererOverride(Consumer<Map<DyeColor, BlockStateModel>> modelMapFiller) {
    //?}
        this.modelMapFiller = modelMapFiller;
    }

    @Override
    //? if <= 1.21.6 {
    /*public void render(BlockEntityRenderer<BlockEntity> renderer, BlockEntity blockEntity, float tickDelta, PoseStack matrices, MultiBufferSource output, int light, int overlay) {
    *///?} else {
    public void render(BlockEntityRenderer<BlockEntity, ?> renderer, BlockEntityRenderState renderState, BlockEntity blockEntity, float tickDelta, PoseStack matrices, SubmitNodeCollector output, int light, int overlay) {
    //?}
        if (models.isEmpty()) modelMapFiller.accept(models);
        if (blockEntity instanceof ShulkerBoxBlockEntity entity) {
            Direction dir = Direction.UP;
            BlockState state = entity.getLevel().getBlockState(entity.getBlockPos());
            if (state.getBlock() instanceof ShulkerBoxBlock) {
                dir = state.getValue(ShulkerBoxBlock.FACING);
            }
            matrices.pushPose();

            float animation = entity.getProgress(tickDelta);

            matrices.translate(0.5, 0.5, 0.5);
            EBEUtil.rotate(matrices, dir.getRotation());
            EBEUtil.rotate(matrices, Axis.YP.rotationDegrees(270 * animation));
            matrices.translate(-0.5, -0.5, -0.5);

            matrices.translate(0, animation * 0.5f, 0);

            var lidModel = models.get(entity.getColor());
            if (lidModel != null) {
                EBEUtil.renderBakedModel(output, blockEntity.getBlockState(), matrices, lidModel, light, overlay);
            }

            matrices.popPose();
        }
    }

    @Override
    public void onModelsReload() {
        this.models.clear();
    }
}
