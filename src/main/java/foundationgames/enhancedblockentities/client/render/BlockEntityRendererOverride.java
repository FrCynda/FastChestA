package foundationgames.enhancedblockentities.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import foundationgames.enhancedblockentities.event.EBEEvents;
//? if <= 1.21.6 {
/*import net.minecraft.client.renderer.MultiBufferSource;
*///?} else {
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
//?}
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.level.block.entity.BlockEntity;

public abstract class BlockEntityRendererOverride {
    public static final BlockEntityRendererOverride NO_OP = new BlockEntityRendererOverride() {
        @Override
        //? if <= 1.21.6 {
        /*public void render(BlockEntityRenderer<BlockEntity> renderer, BlockEntity blockEntity, float tickDelta, PoseStack matrices, MultiBufferSource output, int light, int overlay) {}
        *///?} else {
        public void render(BlockEntityRenderer<BlockEntity, ?> renderer, BlockEntityRenderState renderState, BlockEntity blockEntity, float tickDelta, PoseStack matrices, SubmitNodeCollector output, int light, int overlay) {}
        //?}
    };

    public BlockEntityRendererOverride() {
        EBEEvents.RESOURCE_RELOAD.register(this::onModelsReload);
    }

    //? if <= 1.21.6 {
    /*public abstract void render(BlockEntityRenderer<BlockEntity> renderer, BlockEntity blockEntity, float tickDelta, PoseStack matrices, MultiBufferSource output, int light, int overlay);
    *///?} else {
    public abstract void render(BlockEntityRenderer<BlockEntity, ?> renderer, BlockEntityRenderState renderState, BlockEntity blockEntity, float tickDelta, PoseStack matrices, SubmitNodeCollector output, int light, int overlay);
    //?}

    public void onModelsReload() {}

}
