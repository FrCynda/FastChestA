package foundationgames.enhancedblockentities.client.model;

//? if fabric && <= 1.21.1 {
/*import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

// Same builtin:X blockstate-string bypass as chests (see EBEChestModelResolverPlugin).
public final class EBEDecoratedPotModelResolverPlugin implements ModelLoadingPlugin {
    public static void registerAll() {
        ModelLoadingPlugin.register(new EBEDecoratedPotModelResolverPlugin());
    }

    @Override
    public void onInitializeModelLoader(ModelLoadingPlugin.Context ctx) {
        ctx.registerBlockStateResolver(Blocks.DECORATED_POT, context -> {
            Supplier<DynamicUnbakedModel> supplier = DynamicModelProvidingPlugin.get(
                    Identifier.fromNamespaceAndPath("builtin", "decorated_pot"));
            UnbakedModel model = supplier != null ? supplier.get() : null;
            if (model == null) return;

            for (BlockState state : Blocks.DECORATED_POT.getStateDefinition().getPossibleStates()) {
                int y = switch (state.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
                    case EAST -> 90;
                    case SOUTH -> 180;
                    case WEST -> 270;
                    default -> 0;
                };

                context.setModel(state, new RotatedModel(model, BlockModelRotation.by(0, y)));
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
/*import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.BlockModelRotation;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;
import java.util.function.Supplier;

public final class EBEDecoratedPotModelResolverPlugin implements ModelLoadingPlugin {
    public static void registerAll() {
        ModelLoadingPlugin.register(new EBEDecoratedPotModelResolverPlugin());
    }

    @Override
    public void initialize(ModelLoadingPlugin.Context ctx) {
        ctx.registerBlockStateResolver(Blocks.DECORATED_POT, context -> {
            Supplier<DynamicUnbakedModel> supplier = DynamicModelProvidingPlugin.get(
                    Identifier.fromNamespaceAndPath("builtin", "decorated_pot"));
            UnbakedModel model = supplier != null ? supplier.get() : null;
            if (model == null) return;

            for (BlockState state : Blocks.DECORATED_POT.getStateDefinition().getPossibleStates()) {
                int y = switch (state.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
                    case EAST -> 90;
                    case SOUTH -> 180;
                    case WEST -> 270;
                    default -> 0;
                };

                context.setModel(state, new RotatedModel(model, BlockModelRotation.by(0, y)));
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
