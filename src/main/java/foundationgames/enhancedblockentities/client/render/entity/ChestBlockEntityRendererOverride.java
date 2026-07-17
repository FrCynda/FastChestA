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
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

import java.util.function.Function;
import java.util.function.Supplier;

public class ChestBlockEntityRendererOverride extends BlockEntityRendererOverride {
    //? if <= 1.21.4 {
    /*private BakedModel[] models = null;
    private final Supplier<BakedModel[]> modelGetter;
    private final Function<BlockEntity, Integer> modelSelector;

    public ChestBlockEntityRendererOverride(Supplier<BakedModel[]> modelGetter, Function<BlockEntity, Integer> modelSelector) {
    *///?} else {
    private BlockStateModel[] models = null;
    private final Supplier<BlockStateModel[]> modelGetter;
    private final Function<BlockEntity, Integer> modelSelector;

    public ChestBlockEntityRendererOverride(Supplier<BlockStateModel[]> modelGetter, Function<BlockEntity, Integer> modelSelector) {
    //?}
        this.modelGetter = modelGetter;
        this.modelSelector = modelSelector;
    }

    @Override
    //? if <= 1.21.6 {
    /*public void render(BlockEntityRenderer<BlockEntity> renderer, BlockEntity blockEntity, float tickDelta, PoseStack matrices, MultiBufferSource output, int light, int overlay) {
    *///?} else {
    public void render(BlockEntityRenderer<BlockEntity, ?> renderer, BlockEntityRenderState renderState, BlockEntity blockEntity, float tickDelta, PoseStack matrices, SubmitNodeCollector output, int light, int overlay) {
    //?}
        var lids = resolveModels();
        if (lids == null) return;

        if (blockEntity instanceof LidBlockEntity) {
            matrices.pushPose();

            LidBlockEntity chest = getLidAnimationHolder(blockEntity, tickDelta);
            matrices.translate(0.5f, 0, 0.5f);
            Direction dir = blockEntity.getBlockState().getValue(ChestBlock.FACING);
            EBEUtil.rotate(matrices, Axis.YP.rotationDegrees(180 - EBEUtil.angle(dir)));
            matrices.translate(-0.5f, 0, -0.5f);
            float yPiv = 9f / 16;
            float zPiv = 15f / 16;
            matrices.translate(0, yPiv, zPiv);
            float rot = chest.getOpenNess(tickDelta);
            rot = 1f - rot;
            rot = 1f - (rot * rot * rot);
            EBEUtil.rotate(matrices, Axis.XP.rotationDegrees(rot * 90));
            matrices.translate(0, -yPiv, -zPiv);
            EBEUtil.renderBakedModel(output, blockEntity.getBlockState(), matrices, lids[modelSelector.apply(blockEntity)], light, overlay);

            matrices.popPose();
        }
    }

    //? if <= 1.21.4 {
    /*private BakedModel[] resolveModels() {
    *///?} else {
    private BlockStateModel[] resolveModels() {
    //?}
        if (models != null) return models;

        var fetched = modelGetter.get();
        if (fetched == null) return null;

        for (var model : fetched) {
            if (model == null) return fetched;
        }

        models = fetched;
        return fetched;
    }

    public static LidBlockEntity getLidAnimationHolder(BlockEntity blockEntity, float tickDelta) {
        LidBlockEntity chest = (LidBlockEntity)blockEntity;

        BlockState state = blockEntity.getBlockState();
        if (state.hasProperty(ChestBlock.TYPE) && state.getValue(ChestBlock.TYPE) != ChestType.SINGLE) {
            BlockEntity neighbor = null;
            BlockPos pos = blockEntity.getBlockPos();
            Direction facing = state.getValue(ChestBlock.FACING);
            switch (state.getValue(ChestBlock.TYPE)) {
                case LEFT -> neighbor = blockEntity.getLevel().getBlockEntity(pos.relative(facing.getClockWise()));
                case RIGHT -> neighbor = blockEntity.getLevel().getBlockEntity(pos.relative(facing.getCounterClockWise()));
            }
            if (neighbor instanceof LidBlockEntity) {
                float nAnim = ((LidBlockEntity)neighbor).getOpenNess(tickDelta);
                if (nAnim > chest.getOpenNess(tickDelta)) {
                    chest = ((LidBlockEntity)neighbor);
                }
            }
        }

        return chest;
    }

    @Override
    public void onModelsReload() {
        this.models = null;
    }
}
