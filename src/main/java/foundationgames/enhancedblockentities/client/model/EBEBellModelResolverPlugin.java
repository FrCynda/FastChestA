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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

// Same builtin:X blockstate-string bypass as chests (see EBEChestModelResolverPlugin). Rotation
// mirrors ResourceUtil#addBellBlockState.
public final class EBEBellModelResolverPlugin implements ModelLoadingPlugin {
    public static void registerAll() {
        ModelLoadingPlugin.register(new EBEBellModelResolverPlugin());
    }

    private static Supplier<DynamicUnbakedModel> supplierFor(BellAttachType attachment) {
        String id = switch (attachment) {
            case DOUBLE_WALL -> "bell_between_walls";
            case CEILING -> "bell_ceiling";
            case FLOOR -> "bell_floor";
            case SINGLE_WALL -> "bell_wall";
        };
        return DynamicModelProvidingPlugin.get(Identifier.fromNamespaceAndPath("builtin", id));
    }

    @Override
    public void onInitializeModelLoader(ModelLoadingPlugin.Context ctx) {
        ctx.registerBlockStateResolver(Blocks.BELL, context -> {
            for (BlockState state : Blocks.BELL.getStateDefinition().getPossibleStates()) {
                Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                BellAttachType attachment = state.getValue(BlockStateProperties.BELL_ATTACHMENT);

                Supplier<DynamicUnbakedModel> supplier = supplierFor(attachment);
                UnbakedModel model = supplier != null ? supplier.get() : null;
                if (model == null) continue;

                int rot = EBEUtil.angle(facing) + 90;
                int y = (attachment == BellAttachType.CEILING || attachment == BellAttachType.FLOOR) ? rot + 90 : rot;

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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BellAttachType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.function.Function;
import java.util.function.Supplier;

public final class EBEBellModelResolverPlugin implements ModelLoadingPlugin {
    public static void registerAll() {
        ModelLoadingPlugin.register(new EBEBellModelResolverPlugin());
    }

    private static Supplier<DynamicUnbakedModel> supplierFor(BellAttachType attachment) {
        String id = switch (attachment) {
            case DOUBLE_WALL -> "bell_between_walls";
            case CEILING -> "bell_ceiling";
            case FLOOR -> "bell_floor";
            case SINGLE_WALL -> "bell_wall";
        };
        return DynamicModelProvidingPlugin.get(Identifier.fromNamespaceAndPath("builtin", id));
    }

    @Override
    public void initialize(ModelLoadingPlugin.Context ctx) {
        ctx.registerBlockStateResolver(Blocks.BELL, context -> {
            for (BlockState state : Blocks.BELL.getStateDefinition().getPossibleStates()) {
                Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                BellAttachType attachment = state.getValue(BlockStateProperties.BELL_ATTACHMENT);

                Supplier<DynamicUnbakedModel> supplier = supplierFor(attachment);
                UnbakedModel model = supplier != null ? supplier.get() : null;
                if (model == null) continue;

                int rot = EBEUtil.angle(facing) + 90;
                int y = (attachment == BellAttachType.CEILING || attachment == BellAttachType.FLOOR) ? rot + 90 : rot;

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
