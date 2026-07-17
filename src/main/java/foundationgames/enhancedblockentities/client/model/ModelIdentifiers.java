package foundationgames.enhancedblockentities.client.model;

import foundationgames.enhancedblockentities.EnhancedBlockEntities;
import foundationgames.enhancedblockentities.config.EBEConfig;
import foundationgames.enhancedblockentities.util.EBEUtil;
//? if fabric {
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
//?}
//? if <= 1.21.4 {
/*import net.minecraft.client.resources.model.BakedModel;
*///?}
//? if neoforge && <= 1.21.4 {
/*import net.neoforged.neoforge.client.event.ModelEvent;
*///?}
//? if fabric && >= 1.21.5 {
import net.fabricmc.fabric.api.client.model.loading.v1.ExtraModelKey;
import net.fabricmc.fabric.api.client.model.loading.v1.SimpleUnbakedExtraModel;
//?}
//? if neoforge && >= 1.21.6 {
/*import net.minecraft.client.resources.model.ModelDebugName;
*///?}
//? if neoforge && >= 1.21.5 {
/*import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.model.standalone.StandaloneModelKey;
*///?}
//? if neoforge && >= 1.21.5 && <= 1.21.5 {
/*import net.neoforged.neoforge.client.model.standalone.StandaloneModelBaker;
*///?}
//? if neoforge && >= 1.21.6 {
/*import net.neoforged.neoforge.client.model.standalone.SimpleUnbakedStandaloneModel;
*///?}
//? if forge && <= 1.21.4 {
/*import net.minecraft.client.resources.model.ModelIdentifier;
*///?}
//? if forge {
/*import foundationgames.enhancedblockentities.client.resource.EBEPack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraftforge.client.event.ModelEvent;
*///?}
//? if >= 1.21.5 && <= 1.21.11 {
/*import net.minecraft.client.renderer.block.model.BlockStateModel;
*///?}
//? if >= 26.1 {
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
//?}
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

//? if fabric {
public final class ModelIdentifiers implements ModelLoadingPlugin {
//?} else {
/*public final class ModelIdentifiers {
*///?}
    private static final Map<Predicate<EBEConfig>, Set<Identifier>> modelLoaders = new HashMap<>();
    //? if neoforge && <= 1.21.4 {
    /*private static final Map<Identifier, BakedModel> extraModels = new HashMap<>();
    *///?}
    //? if fabric && >= 1.21.5 {
    private static final Map<Identifier, ExtraModelKey<BlockStateModel>> extraModelKeys = new HashMap<>();
    //?}
    //? if neoforge && >= 1.21.5 {
    /*private static final Map<Identifier, StandaloneModelKey<BlockStateModel>> extraModelKeys = new HashMap<>();
    *///?}
    //? if forge && <= 1.21.4 {
    /*private static final Map<Identifier, BakedModel> extraModels = new HashMap<>();
    *///?}
    //? if forge && >= 1.21.5 {
    /*private static final Map<Identifier, BlockState> extraModelStates = new HashMap<>();
    private static final Map<Identifier, BlockStateModel> extraModels = new HashMap<>();
    *///?}

    public static final Predicate<EBEConfig> CHEST_PREDICATE = c -> c.renderEnhancedChests;
    public static final Predicate<EBEConfig> BELL_PREDICATE = c -> c.renderEnhancedBells;
    public static final Predicate<EBEConfig> SHULKER_BOX_PREDICATE = c -> c.renderEnhancedShulkerBoxes;
    public static final Predicate<EBEConfig> DECORATED_POT_PREDICATE = c -> c.renderEnhancedDecoratedPots;

    public static final Identifier CHEST_CENTER = of("block/chest_center", CHEST_PREDICATE);
    public static final Identifier CHEST_CENTER_TRUNK = of("block/chest_center_trunk", CHEST_PREDICATE);
    public static final Identifier CHEST_CENTER_LID = of("block/chest_center_lid", CHEST_PREDICATE);
    public static final Identifier CHEST_LEFT = of("block/chest_left", CHEST_PREDICATE);
    public static final Identifier CHEST_LEFT_TRUNK = of("block/chest_left_trunk", CHEST_PREDICATE);
    public static final Identifier CHEST_LEFT_LID = of("block/chest_left_lid", CHEST_PREDICATE);
    public static final Identifier CHEST_RIGHT = of("block/chest_right", CHEST_PREDICATE);
    public static final Identifier CHEST_RIGHT_TRUNK = of("block/chest_right_trunk", CHEST_PREDICATE);
    public static final Identifier CHEST_RIGHT_LID = of("block/chest_right_lid", CHEST_PREDICATE);

    public static final Identifier TRAPPED_CHEST_CENTER = of("block/trapped_chest_center", CHEST_PREDICATE);
    public static final Identifier TRAPPED_CHEST_CENTER_TRUNK = of("block/trapped_chest_center_trunk", CHEST_PREDICATE);
    public static final Identifier TRAPPED_CHEST_CENTER_LID = of("block/trapped_chest_center_lid", CHEST_PREDICATE);
    public static final Identifier TRAPPED_CHEST_LEFT = of("block/trapped_chest_left", CHEST_PREDICATE);
    public static final Identifier TRAPPED_CHEST_LEFT_TRUNK = of("block/trapped_chest_left_trunk", CHEST_PREDICATE);
    public static final Identifier TRAPPED_CHEST_LEFT_LID = of("block/trapped_chest_left_lid", CHEST_PREDICATE);
    public static final Identifier TRAPPED_CHEST_RIGHT = of("block/trapped_chest_right", CHEST_PREDICATE);
    public static final Identifier TRAPPED_CHEST_RIGHT_TRUNK = of("block/trapped_chest_right_trunk", CHEST_PREDICATE);
    public static final Identifier TRAPPED_CHEST_RIGHT_LID = of("block/trapped_chest_right_lid", CHEST_PREDICATE);

    public static final Identifier CHRISTMAS_CHEST_CENTER = of("block/christmas_chest_center", CHEST_PREDICATE);
    public static final Identifier CHRISTMAS_CHEST_CENTER_TRUNK = of("block/christmas_chest_center_trunk", CHEST_PREDICATE);
    public static final Identifier CHRISTMAS_CHEST_CENTER_LID = of("block/christmas_chest_center_lid", CHEST_PREDICATE);
    public static final Identifier CHRISTMAS_CHEST_LEFT = of("block/christmas_chest_left", CHEST_PREDICATE);
    public static final Identifier CHRISTMAS_CHEST_LEFT_TRUNK = of("block/christmas_chest_left_trunk", CHEST_PREDICATE);
    public static final Identifier CHRISTMAS_CHEST_LEFT_LID = of("block/christmas_chest_left_lid", CHEST_PREDICATE);
    public static final Identifier CHRISTMAS_CHEST_RIGHT = of("block/christmas_chest_right", CHEST_PREDICATE);
    public static final Identifier CHRISTMAS_CHEST_RIGHT_TRUNK = of("block/christmas_chest_right_trunk", CHEST_PREDICATE);
    public static final Identifier CHRISTMAS_CHEST_RIGHT_LID = of("block/christmas_chest_right_lid", CHEST_PREDICATE);

    public static final Identifier ENDER_CHEST_CENTER = of("block/ender_chest_center", CHEST_PREDICATE);
    public static final Identifier ENDER_CHEST_CENTER_TRUNK = of("block/ender_chest_center_trunk", CHEST_PREDICATE);
    public static final Identifier ENDER_CHEST_CENTER_LID = of("block/ender_chest_center_lid", CHEST_PREDICATE);

    public static final Identifier BELL_BETWEEN_WALLS = of("block/bell_between_walls", BELL_PREDICATE);
    public static final Identifier BELL_CEILING = of("block/bell_ceiling", BELL_PREDICATE);
    public static final Identifier BELL_FLOOR = of("block/bell_floor", BELL_PREDICATE);
    public static final Identifier BELL_WALL = of("block/bell_wall", BELL_PREDICATE);
    public static final Identifier BELL_BETWEEN_WALLS_WITH_BELL = of("block/bell_between_walls_with_bell", BELL_PREDICATE);
    public static final Identifier BELL_CEILING_WITH_BELL = of("block/bell_ceiling_with_bell", BELL_PREDICATE);
    public static final Identifier BELL_FLOOR_WITH_BELL = of("block/bell_floor_with_bell", BELL_PREDICATE);
    public static final Identifier BELL_WALL_WITH_BELL = of("block/bell_wall_with_bell", BELL_PREDICATE);
    public static final Identifier BELL_BODY = of("block/bell_body", BELL_PREDICATE);

    public static final Identifier DECORATED_POT_BASE = of("block/decorated_pot_base", DECORATED_POT_PREDICATE);
    public static final Identifier DECORATED_POT_SHAKING = of("block/decorated_pot_shaking", DECORATED_POT_PREDICATE);

    //? if >= 1.21.9 {
    public static final String[][] COPPER_CHESTS = {
            {"copper_chest", "copper"},
            {"exposed_copper_chest", "copper_exposed"},
            {"weathered_copper_chest", "copper_weathered"},
            {"oxidized_copper_chest", "copper_oxidized"},
            {"waxed_copper_chest", "copper"},
            {"waxed_exposed_copper_chest", "copper_exposed"},
            {"waxed_weathered_copper_chest", "copper_weathered"},
            {"waxed_oxidized_copper_chest", "copper_oxidized"}
    };

    public static final int COPPER_CENTER = 0;
    public static final int COPPER_CENTER_TRUNK = 1;
    public static final int COPPER_CENTER_LID = 2;
    public static final int COPPER_LEFT = 3;
    public static final int COPPER_LEFT_TRUNK = 4;
    public static final int COPPER_LEFT_LID = 5;
    public static final int COPPER_RIGHT = 6;
    public static final int COPPER_RIGHT_TRUNK = 7;
    public static final int COPPER_RIGHT_LID = 8;

    public static final Map<String, Identifier[]> COPPER_CHEST_MODELS = new HashMap<>();
    //?}

    public static final Map<DyeColor, Identifier> SHULKER_BOXES = new HashMap<>();
    public static final Map<DyeColor, Identifier> SHULKER_BOX_BOTTOMS = new HashMap<>();
    public static final Map<DyeColor, Identifier> SHULKER_BOX_LIDS = new HashMap<>();

    public static final Map<ResourceKey<DecoratedPotPattern>, Identifier[]> POTTERY_PATTERNS = new HashMap<>();

    static {
        for (DyeColor color : EBEUtil.DEFAULTED_DYE_COLORS) {
            var id = color != null ? "block/"+color.getName()+"_shulker_box" : "block/shulker_box";
            SHULKER_BOXES.put(color, of(id, SHULKER_BOX_PREDICATE));
            SHULKER_BOX_BOTTOMS.put(color, of(id+"_bottom", SHULKER_BOX_PREDICATE));
            SHULKER_BOX_LIDS.put(color, of(id+"_lid", SHULKER_BOX_PREDICATE));
        }

        //? if >= 1.21.9 {
        for (String[] chest : COPPER_CHESTS) {
            String name = chest[0];

            COPPER_CHEST_MODELS.put(name, new Identifier[] {
                    of("block/" + name + "_center", CHEST_PREDICATE),
                    of("block/" + name + "_center_trunk", CHEST_PREDICATE),
                    of("block/" + name + "_center_lid", CHEST_PREDICATE),
                    of("block/" + name + "_left", CHEST_PREDICATE),
                    of("block/" + name + "_left_trunk", CHEST_PREDICATE),
                    of("block/" + name + "_left_lid", CHEST_PREDICATE),
                    of("block/" + name + "_right", CHEST_PREDICATE),
                    of("block/" + name + "_right_trunk", CHEST_PREDICATE),
                    of("block/" + name + "_right_lid", CHEST_PREDICATE)
            });
        }
        //?}

        refreshPotteryPatterns();
    }

    public static void init() {
        //? if fabric {
        ModelLoadingPlugin.register(new ModelIdentifiers());
        //?}
    }

    public static void refreshPotteryPatterns() {
        POTTERY_PATTERNS.clear();

        // The order decorated pots store patterns per face
        Direction[] orderedHorizontalDirs = new Direction[] {Direction.NORTH, Direction.WEST, Direction.EAST, Direction.SOUTH};

        for (var patternKey : EBEUtil.potPatternKeys()) {
            var pattern = patternKey.identifier().getPath();
            var ids = new Identifier[orderedHorizontalDirs.length];;

            for (int i = 0; i < 4; i++) {
                ids[i] = of("block/" + pattern + "_" + orderedHorizontalDirs[i].getName(),
                        DECORATED_POT_PREDICATE);
            }

            POTTERY_PATTERNS.put(patternKey, ids);
        }
    }

    private static Identifier of(String id, Predicate<EBEConfig> condition) {
        Identifier idf = Identifier.parse(id);
        modelLoaders.computeIfAbsent(condition, k -> new HashSet<>()).add(idf);
        //? if fabric && >= 1.21.5 {
        extraModelKeys.computeIfAbsent(idf, key -> ExtraModelKey.create(key::toString));
        //?}
        //? if neoforge && >= 1.21.5 && <= 1.21.5 {
        /*extraModelKeys.computeIfAbsent(idf, key -> new StandaloneModelKey<>(key));
        *///?}
        //? if neoforge && >= 1.21.6 {
        /*extraModelKeys.computeIfAbsent(idf, key -> new StandaloneModelKey<>((ModelDebugName) key::toString));
        *///?}
        return idf;
    }

    //? if fabric && <= 1.21.4 {
    /*public static @Nullable BakedModel getBakedModel(Identifier id) {
        return Minecraft.getInstance().getModelManager().getModel(id);
    }
    *///?}
    //? if neoforge && <= 1.21.4 {
    /*public static @Nullable BakedModel getBakedModel(Identifier id) {
        return extraModels.get(id);
    }

    public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
        var config = EnhancedBlockEntities.CONFIG;

        for (var entry : modelLoaders.entrySet()) {
            if (entry.getKey().test(config)) {
                for (var id : entry.getValue()) {
                    event.register(id);
                }
            }
        }
    }

    public static void captureBakedModels(ModelEvent.BakingCompleted event) {
        extraModels.clear();
        extraModels.putAll(event.getBakingResult().standaloneModels());
    }
    *///?}
    //? if forge && <= 1.21.4 {
    /*public static @Nullable BakedModel getBakedModel(Identifier id) {
        return extraModels.get(id);
    }

    private static Identifier extraModelStateId(Identifier id) {
        return EBEUtil.id("extra/" + id.getNamespace() + "/" + id.getPath());
    }

    public static void emitExtraModelBlockStates(EBEPack pack) {
        var config = EnhancedBlockEntities.CONFIG;

        for (var entry : modelLoaders.entrySet()) {
            if (!entry.getKey().test(config)) continue;

            for (var id : entry.getValue()) {
                var stateId = extraModelStateId(id);

                pack.addPlainTextResource(
                        Identifier.fromNamespaceAndPath(stateId.getNamespace(), "blockstates/" + stateId.getPath() + ".json"),
                        "{\"variants\":{\"\":{\"model\":\"" + id + "\"}}}");
            }
        }
    }

    public static void registerModelStateDefinitions(ModelEvent.RegisterModelStateDefinitions event) {
        var config = EnhancedBlockEntities.CONFIG;

        for (var entry : modelLoaders.entrySet()) {
            if (!entry.getKey().test(config)) continue;

            for (var id : entry.getValue()) {
                event.register(extraModelStateId(id),
                        new StateDefinition.Builder<Block, BlockState>(Blocks.AIR)
                                .create(Block::defaultBlockState, BlockState::new));
            }
        }
    }

    public static void captureExtraModels(ModelEvent.ModifyBakingResult event) {
        extraModels.clear();

        var config = EnhancedBlockEntities.CONFIG;
        var models = event.getResults().blockStateModels();

        for (var entry : modelLoaders.entrySet()) {
            if (!entry.getKey().test(config)) continue;

            for (var id : entry.getValue()) {
                var model = models.get(new ModelIdentifier(extraModelStateId(id), ""));
                if (model != null) extraModels.put(id, model);
            }
        }
    }
    *///?} else if forge && >= 1.21.5 {
    /*public static @Nullable BlockStateModel getBakedModel(Identifier id) {
        return extraModels.get(id);
    }

    private static Identifier extraModelStateId(Identifier id) {
        return EBEUtil.id("extra/" + id.getNamespace() + "/" + id.getPath());
    }

    public static void emitExtraModelBlockStates(EBEPack pack) {
        var config = EnhancedBlockEntities.CONFIG;

        for (var entry : modelLoaders.entrySet()) {
            if (!entry.getKey().test(config)) continue;

            for (var id : entry.getValue()) {
                var stateId = extraModelStateId(id);

                pack.addPlainTextResource(
                        Identifier.fromNamespaceAndPath(stateId.getNamespace(), "blockstates/" + stateId.getPath() + ".json"),
                        "{\"variants\":{\"\":{\"model\":\"" + id + "\"}}}");
            }
        }
    }

    public static void registerModelStateDefinitions(ModelEvent.RegisterModelStateDefinitions event) {
        var config = EnhancedBlockEntities.CONFIG;

        extraModelStates.clear();

        for (var entry : modelLoaders.entrySet()) {
            if (!entry.getKey().test(config)) continue;

            for (var id : entry.getValue()) {
                var definition = new StateDefinition.Builder<Block, BlockState>(Blocks.AIR)
                        .create(Block::defaultBlockState, BlockState::new);

                event.register(extraModelStateId(id), definition);
                extraModelStates.put(id, definition.any());
            }
        }
    }

    public static void captureExtraModels(ModelEvent.ModifyBakingResult event) {
        extraModels.clear();

        var models = event.getResults().blockStateModels();
        for (var entry : extraModelStates.entrySet()) {
            var model = models.get(entry.getValue());
            if (model != null) extraModels.put(entry.getKey(), model);
        }
    }
    *///?} else if fabric && >= 1.21.5 {
    public static @Nullable BlockStateModel getBakedModel(Identifier id) {
        var key = extraModelKeys.get(id);
        return key != null ? Minecraft.getInstance().getModelManager().getModel(key) : null;
    }
    //?} else if neoforge && >= 1.21.5 {
    /*public static @Nullable BlockStateModel getBakedModel(Identifier id) {
        var key = extraModelKeys.get(id);
        return key != null ? Minecraft.getInstance().getModelManager().getStandaloneModel(key) : null;
    }
    *///?}

    //? if fabric && <= 1.21.4 {
    /*@Override
    public void initialize(Context ctx) {
        var config = EnhancedBlockEntities.CONFIG;

        for (var entry : modelLoaders.entrySet()) {
            if (entry.getKey().test(config)) {
                ctx.addModels(entry.getValue());
            }
        }
    }
    *///?}
    //? if fabric && >= 1.21.5 {
    @Override
    public void initialize(Context ctx) {
        var config = EnhancedBlockEntities.CONFIG;

        for (var entry : modelLoaders.entrySet()) {
            if (entry.getKey().test(config)) {
                for (var id : entry.getValue()) {
                    ctx.addModel(extraModelKeys.get(id), SimpleUnbakedExtraModel.blockStateModel(id));
                }
            }
        }
    }
    //?}
    //? if neoforge && >= 1.21.5 && <= 1.21.5 {
    /*public static void registerStandaloneModels(ModelEvent.RegisterStandalone event) {
        var config = EnhancedBlockEntities.CONFIG;

        for (var entry : modelLoaders.entrySet()) {
            if (entry.getKey().test(config)) {
                for (var id : entry.getValue()) {
                    event.register(extraModelKeys.get(id), StandaloneModelBaker.blockStateModel());
                }
            }
        }
    }
    *///?}
    //? if neoforge && >= 1.21.6 {
    /*public static void registerStandaloneModels(ModelEvent.RegisterStandalone event) {
        var config = EnhancedBlockEntities.CONFIG;

        for (var entry : modelLoaders.entrySet()) {
            if (entry.getKey().test(config)) {
                for (var id : entry.getValue()) {
                    event.register(extraModelKeys.get(id), SimpleUnbakedStandaloneModel.blockStateModel(id));
                }
            }
        }
    }
    *///?}
}
