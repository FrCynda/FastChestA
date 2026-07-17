package foundationgames.enhancedblockentities.client.render.entity;

import com.google.common.collect.ImmutableMap;
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
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;

import java.util.Map;

public class DecoratedPotBlockEntityRendererOverride extends BlockEntityRendererOverride {
    public static final float WOBBLE_STRENGTH = 1f / 64;

    //? if <= 1.21.4 {
    /*private BakedModel baseModel = null;
    private Map<ResourceKey<DecoratedPotPattern>, BakedModel[]> potPatternModels = null;

    private void tryGetModels() {
        if (this.baseModel == null) {
            this.baseModel = ModelIdentifiers.getBakedModel(ModelIdentifiers.DECORATED_POT_BASE);
        }

        if (this.potPatternModels == null) {
            var builder = ImmutableMap.<ResourceKey<DecoratedPotPattern>, BakedModel[]>builder();

            EBEUtil.potPatternKeys().forEach(k -> {
                var patternModelIDs = ModelIdentifiers.POTTERY_PATTERNS.get(k);
                BakedModel[] patternPerFaceModels = new BakedModel[patternModelIDs.length];

                for (int i = 0; i < patternModelIDs.length; i++) {
                    patternPerFaceModels[i] = ModelIdentifiers.getBakedModel(patternModelIDs[i]);
                }

                builder.put(k, patternPerFaceModels);
            });

            this.potPatternModels = builder.build();
        }
    }
    *///?} else {
    private BlockStateModel baseModel = null;
    private Map<ResourceKey<DecoratedPotPattern>, BlockStateModel[]> potPatternModels = null;

    private void tryGetModels() {
        if (this.baseModel == null) {
            this.baseModel = ModelIdentifiers.getBakedModel(ModelIdentifiers.DECORATED_POT_BASE);
        }

        if (this.potPatternModels == null) {
            var builder = ImmutableMap.<ResourceKey<DecoratedPotPattern>, BlockStateModel[]>builder();

            EBEUtil.potPatternKeys().forEach(k -> {
                var patternModelIDs = ModelIdentifiers.POTTERY_PATTERNS.get(k);
                BlockStateModel[] patternPerFaceModels = new BlockStateModel[patternModelIDs.length];

                for (int i = 0; i < patternModelIDs.length; i++) {
                    patternPerFaceModels[i] = ModelIdentifiers.getBakedModel(patternModelIDs[i]);
                }

                builder.put(k, patternPerFaceModels);
            });

            this.potPatternModels = builder.build();
        }
    }
    //?}

    @Override
    //? if <= 1.21.6 {
    /*public void render(BlockEntityRenderer<BlockEntity> renderer, BlockEntity blockEntity, float tickDelta, PoseStack matrices, MultiBufferSource output, int light, int overlay) {
    *///?} else {
    public void render(BlockEntityRenderer<BlockEntity, ?> renderer, BlockEntityRenderState renderState, BlockEntity blockEntity, float tickDelta, PoseStack matrices, SubmitNodeCollector output, int light, int overlay) {
    //?}
        tryGetModels();

        if (blockEntity instanceof DecoratedPotBlockEntity pot) {
            matrices.pushPose();

            var dir = pot.getDirection();

            matrices.translate(0.5f, 0, 0.5f);
            EBEUtil.rotate(matrices, Axis.YP.rotationDegrees(180 - EBEUtil.angle(dir)));
            matrices.translate(-0.5f, 0, -0.5f);

            var wobbleType = pot.lastWobbleStyle;
            if (wobbleType != null && pot.getLevel() != null) {
                float tilt = ((float)(pot.getLevel().getGameTime() - pot.wobbleStartedAtTick) + tickDelta) / (float)wobbleType.duration;
                if (tilt >= 0.0F && tilt <= 1.0F) {
                    if (wobbleType == DecoratedPotBlockEntity.WobbleStyle.POSITIVE) {
                        float animPeriod = tilt * Mth.TWO_PI;

                        float tiltX = -1.5f * (Mth.cos(animPeriod) + 0.5f) * Mth.sin(animPeriod * 0.5f);
                        matrices.rotateAround(Axis.XP.rotation(tiltX * WOBBLE_STRENGTH), 0.5f, 0f, 0.5f);

                        float tiltZ = Mth.sin(animPeriod);
                        matrices.rotateAround(Axis.ZP.rotation(tiltZ * WOBBLE_STRENGTH), 0.5f, 0f, 0.5f);
                    } else {
                        float yaw = (1f - tilt) * Mth.sin(-tilt * 3 * Mth.PI) * 0.125f;
                        matrices.rotateAround(Axis.YP.rotation(yaw), 0.5f, 0f, 0.5f);
                    }
                }
            }

            var sherds = pot.getDecorations();
            EBEUtil.renderBakedModel(output, blockEntity.getBlockState(), matrices, this.baseModel, light, overlay);

            EBEUtil.renderBakedModel(output, blockEntity.getBlockState(), matrices,
                    this.potPatternModels.get(EBEUtil.potPatternFromSherd(sherds.back()))[0], light, overlay);
            EBEUtil.renderBakedModel(output, blockEntity.getBlockState(), matrices,
                    this.potPatternModels.get(EBEUtil.potPatternFromSherd(sherds.left()))[1], light, overlay);
            EBEUtil.renderBakedModel(output, blockEntity.getBlockState(), matrices,
                    this.potPatternModels.get(EBEUtil.potPatternFromSherd(sherds.right()))[2], light, overlay);
            EBEUtil.renderBakedModel(output, blockEntity.getBlockState(), matrices,
                    this.potPatternModels.get(EBEUtil.potPatternFromSherd(sherds.front()))[3], light, overlay);

            matrices.popPose();
        }
    }

    @Override
    public void onModelsReload() {
        this.baseModel = null;
        this.potPatternModels = null;
    }
}