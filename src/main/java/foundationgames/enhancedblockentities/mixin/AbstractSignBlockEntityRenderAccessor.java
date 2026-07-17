package foundationgames.enhancedblockentities.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.blockentity.AbstractSignRenderer;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import net.minecraft.world.level.block.entity.SignText;
//? if <= 1.21.6 {
/*import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
*///?} else {
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.state.SignRenderState;
//?}

@Mixin(AbstractSignRenderer.class)
public interface AbstractSignBlockEntityRenderAccessor {
    //? if <= 1.21.11 {
    /*@Invoker("translateSign")
    void enhanced_bes$applyTransforms(PoseStack matrices, float rotationDegrees, BlockState state);
    *///?}

    //? if <= 1.21.6 {
    /*@Invoker("renderSignText")
    void enhanced_bes$renderText(BlockPos pos, SignText signText, PoseStack matrices, MultiBufferSource output, int light, int lineHeight, int lineWidth, boolean front);
    *///?} else {
    //? if <= 1.21.11 {
    /*@Invoker("submitSignText")
    void enhanced_bes$submitSignText(SignRenderState renderState, PoseStack matrices, SubmitNodeCollector output, boolean front);
    *///?} else {
    @Invoker("submitSignText")
    void enhanced_bes$submitSignText(SignRenderState renderState, PoseStack matrices, SubmitNodeCollector output, SignText text);
    //?}
    //?}

    @Accessor("OUTLINE_RENDER_DISTANCE")
    static int enhanced_bes$getRenderDistance() {
        throw new AssertionError();
    }
}
