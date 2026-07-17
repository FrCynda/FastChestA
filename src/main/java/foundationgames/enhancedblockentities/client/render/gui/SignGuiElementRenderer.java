package foundationgames.enhancedblockentities.client.render.gui;

//? if >= 1.21.6 {
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.vertex.PoseStack;
import foundationgames.enhancedblockentities.util.EBEUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.client.gui.render.pip.PictureInPictureRenderer;
//? if <= 1.21.11 {
/*import net.minecraft.client.gui.render.state.pip.PictureInPictureRenderState;
import net.minecraft.client.renderer.block.model.BlockStateModel;
*///?} else {
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.state.gui.pip.PictureInPictureRenderState;
//?}
//? if <= 26.1 {
/*import net.minecraft.client.renderer.MultiBufferSource;
*///?} else {
import net.minecraft.client.renderer.SubmitNodeCollector;
//?}
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SignGuiElementRenderer extends PictureInPictureRenderer<SignGuiElementRenderer.State> {
    public static final int FULL_BRIGHT = 15728880;

    //? if <= 26.1 {
    /*public SignGuiElementRenderer(MultiBufferSource.BufferSource bufferSource) {
        super(bufferSource);
    }
    *///?} else {
    public SignGuiElementRenderer() {
    }
    //?}

    @Override
    public Class<State> getRenderStateClass() {
        return State.class;
    }

    @Override
    protected String getTextureLabel() {
        return "ebe sign";
    }

    //? if <= 26.1 {
    /*@Override
    protected void renderToTexture(State state, PoseStack matrices) {
        Minecraft.getInstance().gameRenderer.getLighting().setupFor(Lighting.Entry.ITEMS_3D);

        matrices.scale(1, -1, 1);

        float areaHeight = (state.y1() - state.y0()) / state.scale();
        matrices.translate(-0.5f, (areaHeight * 0.5f) - 0.5f + state.up(), 0);

        EBEUtil.renderBakedModel(this.bufferSource, state.blockState(), matrices, state.model(),
                FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
    }
    *///?} else {
    @Override
    protected void renderToTexture(State state, PoseStack matrices, SubmitNodeCollector output) {
        Minecraft.getInstance().gameRenderer.lighting().setupFor(Lighting.Entry.ITEMS_3D);

        matrices.scale(1, -1, 1);

        float areaHeight = (state.y1() - state.y0()) / state.scale();
        matrices.translate(-0.5f, (areaHeight * 0.5f) - 0.5f + state.up(), 0);

        EBEUtil.renderBakedModel(output, state.blockState(), matrices, state.model(),
                FULL_BRIGHT, OverlayTexture.NO_OVERLAY);
    }
    //?}

    public record State(BlockStateModel model, BlockState blockState, float up, int x0, int y0, int x1, int y1,
                        float scale, @Nullable ScreenRectangle scissorArea, ScreenRectangle bounds)
            implements PictureInPictureRenderState {
        public State(BlockStateModel model, BlockState blockState, float up, int x0, int y0, int x1, int y1,
                     float scale, @Nullable ScreenRectangle scissorArea) {
            this(model, blockState, up, x0, y0, x1, y1, scale, scissorArea,
                    PictureInPictureRenderState.getBounds(x0, y0, x1, y1, scissorArea));
        }
    }
}
//?}
