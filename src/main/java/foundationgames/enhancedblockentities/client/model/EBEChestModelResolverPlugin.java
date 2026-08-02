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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Supplier;

// On <= 1.21.3 the blockstate JSON points at "model": "builtin:chest_left" as a plain string,
// which Fabric's modifyModelOnLoad hook never sees (it only fires with topLevelId, not
// resourceId, for this path) - vanilla just fails to find that file and falls back to the
// missing-model texture. registerBlockStateResolver replaces the whole lookup instead.
public final class EBEChestModelResolverPlugin implements ModelLoadingPlugin {
    public static void registerAll() {
        ModelLoadingPlugin.register(new EBEChestModelResolverPlugin(Blocks.CHEST, "chest"));
        ModelLoadingPlugin.register(new EBEChestModelResolverPlugin(Blocks.TRAPPED_CHEST, "trapped_chest"));
        ModelLoadingPlugin.register(new EBEChestModelResolverPlugin(Blocks.ENDER_CHEST, "ender_chest"));
    }

    private final Block block;
    private final String name;

    private EBEChestModelResolverPlugin(Block block, String name) {
        this.block = block;
        this.name = name;
    }

    @Override
    public void onInitializeModelLoader(ModelLoadingPlugin.Context ctx) {
        ctx.registerBlockStateResolver(block, context -> {
            for (BlockState state : block.getStateDefinition().getPossibleStates()) {
                String suffix = "_center";
                if (state.hasProperty(ChestBlock.TYPE)) {
                    ChestType type = state.getValue(ChestBlock.TYPE);
                    suffix = type == ChestType.LEFT ? "_left" : type == ChestType.RIGHT ? "_right" : "_center";
                }

                Supplier<DynamicUnbakedModel> supplier = DynamicModelProvidingPlugin.get(
                        Identifier.fromNamespaceAndPath("builtin", name + suffix));
                UnbakedModel model = supplier != null ? supplier.get() : null;
                if (model == null) continue;

                int y = switch (state.getValue(HorizontalDirectionalBlock.FACING)) {
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

import java.util.function.Function;
import java.util.function.Supplier;

public final class EBEChestModelResolverPlugin implements ModelLoadingPlugin {
    public static void registerAll() {
        ModelLoadingPlugin.register(new EBEChestModelResolverPlugin(Blocks.CHEST, "chest"));
        ModelLoadingPlugin.register(new EBEChestModelResolverPlugin(Blocks.TRAPPED_CHEST, "trapped_chest"));
        ModelLoadingPlugin.register(new EBEChestModelResolverPlugin(Blocks.ENDER_CHEST, "ender_chest"));
    }

    private final Block block;
    private final String name;

    private EBEChestModelResolverPlugin(Block block, String name) {
        this.block = block;
        this.name = name;
    }

    @Override
    public void initialize(ModelLoadingPlugin.Context ctx) {
        ctx.registerBlockStateResolver(block, context -> {
            for (BlockState state : block.getStateDefinition().getPossibleStates()) {
                String suffix = "_center";
                if (state.hasProperty(ChestBlock.TYPE)) {
                    ChestType type = state.getValue(ChestBlock.TYPE);
                    suffix = type == ChestType.LEFT ? "_left" : type == ChestType.RIGHT ? "_right" : "_center";
                }

                Supplier<DynamicUnbakedModel> supplier = DynamicModelProvidingPlugin.get(
                        Identifier.fromNamespaceAndPath("builtin", name + suffix));
                UnbakedModel model = supplier != null ? supplier.get() : null;
                if (model == null) continue;

                int y = switch (state.getValue(HorizontalDirectionalBlock.FACING)) {
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
