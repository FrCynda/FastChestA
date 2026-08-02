package foundationgames.enhancedblockentities.client.model;

//? if fabric && <= 1.21.1 {
/*import foundationgames.enhancedblockentities.util.EBEUtil;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

// Same builtin:X blockstate-string bypass as chests (see EBEChestModelResolverPlugin).
public final class EBEShulkerBoxModelResolverPlugin implements ModelLoadingPlugin {
    public static void registerAll() {
        for (DyeColor color : EBEUtil.DEFAULTED_DYE_COLORS) {
            Block block = ShulkerBoxBlock.getBlockByColor(color);
            String id = color != null ? color.getName() + "_shulker_box" : "shulker_box";
            ModelLoadingPlugin.register(new EBEShulkerBoxModelResolverPlugin(block, id));
        }
    }

    private final Block block;
    private final String id;

    private EBEShulkerBoxModelResolverPlugin(Block block, String id) {
        this.block = block;
        this.id = id;
    }

    @Override
    public void onInitializeModelLoader(ModelLoadingPlugin.Context ctx) {
        ctx.registerBlockStateResolver(block, context -> {
            Supplier<DynamicUnbakedModel> supplier = DynamicModelProvidingPlugin.get(
                    Identifier.fromNamespaceAndPath("builtin", id));
            UnbakedModel model = supplier != null ? supplier.get() : null;
            if (model == null) return;

            for (BlockState state : block.getStateDefinition().getPossibleStates()) {
                Direction facing = state.getValue(ShulkerBoxBlock.FACING);
                int x = facing == Direction.DOWN ? 180 : facing.getAxis().isHorizontal() ? 90 : 0;
                int y = facing.getAxis().isHorizontal() ? EBEUtil.angle(facing) + 180 : 0;

                context.setModel(state, new RotatedModel(model, BlockModelRotation.by(x, y)));
            }
        });
    }

    private record RotatedModel(UnbakedModel inner, ModelState rotation) implements UnbakedModel {
        @Override
        public Collection<Identifier> getDependencies() {
            return inner.getDependencies();
        }

        @Override
        public void resolveParents(Function<Identifier, UnbakedModel> modelGetter) {
            inner.resolveParents(modelGetter);
        }

        @Override
        public BakedModel bake(ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState settings) {
            return inner.bake(baker, spriteGetter, rotation);
        }
    }
}
*///?} else if fabric && <= 1.21.3 {
/*import foundationgames.enhancedblockentities.util.EBEUtil;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;
import java.util.function.Supplier;

public final class EBEShulkerBoxModelResolverPlugin implements ModelLoadingPlugin {
    public static void registerAll() {
        for (DyeColor color : EBEUtil.DEFAULTED_DYE_COLORS) {
            Block block = ShulkerBoxBlock.getBlockByColor(color);
            String id = color != null ? color.getName() + "_shulker_box" : "shulker_box";
            ModelLoadingPlugin.register(new EBEShulkerBoxModelResolverPlugin(block, id));
        }
    }

    private final Block block;
    private final String id;

    private EBEShulkerBoxModelResolverPlugin(Block block, String id) {
        this.block = block;
        this.id = id;
    }

    @Override
    public void initialize(ModelLoadingPlugin.Context ctx) {
        ctx.registerBlockStateResolver(block, context -> {
            Supplier<DynamicUnbakedModel> supplier = DynamicModelProvidingPlugin.get(
                    Identifier.fromNamespaceAndPath("builtin", id));
            UnbakedModel model = supplier != null ? supplier.get() : null;
            if (model == null) return;

            for (BlockState state : block.getStateDefinition().getPossibleStates()) {
                Direction facing = state.getValue(ShulkerBoxBlock.FACING);
                int x = facing == Direction.DOWN ? 180 : facing.getAxis().isHorizontal() ? 90 : 0;
                int y = facing.getAxis().isHorizontal() ? EBEUtil.angle(facing) + 180 : 0;

                context.setModel(state, new RotatedModel(model, BlockModelRotation.by(x, y)));
            }
        });
    }

    private record RotatedModel(UnbakedModel inner, ModelState rotation) implements UnbakedModel {
        @Override
        public void resolveDependencies(Resolver resolver) {
            inner.resolveDependencies(resolver);
        }

        @Override
        public BakedModel bake(ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState settings) {
            return inner.bake(baker, spriteGetter, rotation);
        }
    }
}
*///?}
