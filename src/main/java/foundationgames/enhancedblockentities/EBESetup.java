package foundationgames.enhancedblockentities;

import foundationgames.enhancedblockentities.client.model.*;
import foundationgames.enhancedblockentities.client.model.misc.DecoratedPotModelSelector;
import foundationgames.enhancedblockentities.client.render.BlockEntityRenderCondition;
import foundationgames.enhancedblockentities.client.render.BlockEntityRendererOverride;
import foundationgames.enhancedblockentities.client.render.entity.*;
import foundationgames.enhancedblockentities.client.resource.EBEPack;
import foundationgames.enhancedblockentities.util.DateUtil;
import foundationgames.enhancedblockentities.util.EBEUtil;
import foundationgames.enhancedblockentities.util.ResourceUtil;
//? if fabric && <= 1.21.5 {
/*import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
*///?} else if fabric && >= 1.21.6 && <= 1.21.11 {
/*import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
*///?} else if <= 1.21.11 {
/*import net.minecraft.client.renderer.ItemBlockRenderTypes;
*///?}
//? if <= 1.21.5 {
/*import net.minecraft.client.renderer.RenderType;
*///?}
//? if >= 1.21.6 && <= 1.21.11 {
/*import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
*///?}
//? if <= 1.21.4 {
/*import net.minecraft.client.resources.model.BakedModel;
*///?} else {
//? if <= 1.21.11 {
/*import net.minecraft.client.renderer.block.model.BlockStateModel;
*///?} else {
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
//?}
//?}
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
//? if >= 26.2 {
import net.minecraft.world.level.block.entity.BlockEntityTypes;
//?}
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public enum EBESetup {;
    private static final Set<Block> RENDER_LAYERS = new HashSet<>();
    //? if >= 1.21.9 {
    private static final java.util.Map<Block, Integer> COPPER_CHEST_INDICES = new java.util.HashMap<>();
    //?}

    public static void setupRRPChests() {
        EBEPack p = ResourceUtil.getPackForCompat();

        ResourceUtil.addChestBlockStates("chest", p);
        ResourceUtil.addChestBlockStates("trapped_chest", p);
        ResourceUtil.addChestBlockStates("christmas_chest", p);
        ResourceUtil.addSingleChestOnlyBlockStates("ender_chest", p);

        //? if >= 1.21.9 {
        for (String[] chest : ModelIdentifiers.COPPER_CHESTS) {
            ResourceUtil.addChestBlockStates(chest[0], p);
        }
        //?}

        p = ResourceUtil.getBasePack();

        ResourceUtil.addSingleChestModels("normal", "chest", p);
        ResourceUtil.addDoubleChestModels("normal_left", "normal_right","chest", p);
        ResourceUtil.addSingleChestModels("trapped", "trapped_chest", p);
        ResourceUtil.addDoubleChestModels("trapped_left", "trapped_right","trapped_chest", p);
        ResourceUtil.addSingleChestModels("christmas", "christmas_chest", p);
        ResourceUtil.addDoubleChestModels("christmas_left", "christmas_right","christmas_chest", p);
        ResourceUtil.addSingleChestModels("ender", "ender_chest", p);

        ResourceUtil.addChestItemDefinition("chest", "chest_center", true, p);
        ResourceUtil.addChestItemDefinition("trapped_chest", "trapped_chest_center", true, p);
        ResourceUtil.addChestItemDefinition("ender_chest", "ender_chest_center", false, p);

        //? if >= 1.21.9 {
        for (String[] chest : ModelIdentifiers.COPPER_CHESTS) {
            ResourceUtil.addSingleChestModels(chest[1], chest[0], p);
            ResourceUtil.addDoubleChestModels(chest[1] + "_left", chest[1] + "_right", chest[0], p);
            ResourceUtil.addChestItemDefinition(chest[0], chest[0] + "_center", false, p);
        }
        //?}

        p.addDirBlockSprites("entity/chest", "entity/chest/");
    }

    public static void setupRRPSigns() {
        //? if <= 26.1 {
        /*EBEPack p = ResourceUtil.getPackForCompat();

        ResourceUtil.addSignBlockStates("oak_sign", "oak_wall_sign", p);
        ResourceUtil.addSignBlockStates("birch_sign", "birch_wall_sign", p);
        ResourceUtil.addSignBlockStates("spruce_sign", "spruce_wall_sign", p);
        ResourceUtil.addSignBlockStates("jungle_sign", "jungle_wall_sign", p);
        ResourceUtil.addSignBlockStates("acacia_sign", "acacia_wall_sign", p);
        ResourceUtil.addSignBlockStates("dark_oak_sign", "dark_oak_wall_sign", p);
        ResourceUtil.addSignBlockStates("mangrove_sign", "mangrove_wall_sign", p);
        ResourceUtil.addSignBlockStates("cherry_sign", "cherry_wall_sign", p);
        ResourceUtil.addSignBlockStates("crimson_sign", "crimson_wall_sign", p);
        ResourceUtil.addSignBlockStates("warped_sign", "warped_wall_sign", p);
        ResourceUtil.addSignBlockStates("bamboo_sign", "bamboo_wall_sign", p);
        ResourceUtil.addSignBlockStates("pale_oak_sign", "pale_oak_wall_sign", p);

        ResourceUtil.addHangingSignBlockStates("oak_hanging_sign", "oak_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("birch_hanging_sign", "birch_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("spruce_hanging_sign", "spruce_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("jungle_hanging_sign", "jungle_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("acacia_hanging_sign", "acacia_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("dark_oak_hanging_sign", "dark_oak_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("mangrove_hanging_sign", "mangrove_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("cherry_hanging_sign", "cherry_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("crimson_hanging_sign", "crimson_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("warped_hanging_sign", "warped_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("bamboo_hanging_sign", "bamboo_wall_hanging_sign", p);
        ResourceUtil.addHangingSignBlockStates("pale_oak_hanging_sign", "pale_oak_wall_hanging_sign", p);

        p = ResourceUtil.getBasePack();

        ResourceUtil.addSignTypeModels("oak", p);
        ResourceUtil.addSignTypeModels("birch", p);
        ResourceUtil.addSignTypeModels("spruce", p);
        ResourceUtil.addSignTypeModels("jungle", p);
        ResourceUtil.addSignTypeModels("acacia", p);
        ResourceUtil.addSignTypeModels("dark_oak", p);
        ResourceUtil.addSignTypeModels("mangrove", p);
        ResourceUtil.addSignTypeModels("cherry", p);
        ResourceUtil.addSignTypeModels("crimson", p);
        ResourceUtil.addSignTypeModels("warped", p);
        ResourceUtil.addSignTypeModels("bamboo", p);
        ResourceUtil.addSignTypeModels("pale_oak", p);

        p.addDirBlockSprites("entity/signs", "entity/signs/");
        p.addDirBlockSprites("entity/signs/hanging", "entity/signs/hanging/");
        p.addDirBlockSprites("gui/hanging_signs", "block/particle_hanging_sign_");
        *///?}
    }

    public static void setupRRPBells() {
        ResourceUtil.addBellBlockState(ResourceUtil.getPackForCompat());

        ResourceUtil.getBasePack().addSingleBlockSprite(Identifier.parse("entity/bell/bell_body"));
    }

    public static void setupRRPBeds() {
        //? if <= 26.1 {
        /*EBEPack p = ResourceUtil.getBasePack();
        EBEPack pCompat = ResourceUtil.getPackForCompat();

        for (DyeColor color : DyeColor.values()) {
            ResourceUtil.addBedBlockState(color, pCompat);
            ResourceUtil.addBedModels(color, p);
        }

        p.addDirBlockSprites("entity/bed", "entity/bed/");
        *///?}
    }

    public static void setupRRPShulkerBoxes() {
        EBEPack p = ResourceUtil.getBasePack();
        EBEPack pCompat = ResourceUtil.getPackForCompat();

        for (DyeColor color : EBEUtil.DEFAULTED_DYE_COLORS) {
            var id = color != null ? color.getName()+"_shulker_box" : "shulker_box";
            ResourceUtil.addShulkerBoxBlockStates(color, pCompat);
            ResourceUtil.addShulkerBoxModels(color, p);
            ResourceUtil.addParentModel("block/"+id, Identifier.parse("item/"+id), p);
        }

        p.addDirBlockSprites("entity/shulker", "entity/shulker/");
    }

    public static void setupRRPDecoratedPots() {
        EBEPack p = ResourceUtil.getBasePack();
        EBEPack pCompat = ResourceUtil.getPackForCompat();

        ResourceUtil.addDecoratedPotBlockState(pCompat);
        for (var patternKey : EBEUtil.potPatternKeys()) {
            ResourceUtil.addDecoratedPotPatternModels(patternKey, p);
        }

        p.addDirBlockSprites("entity/decorated_pot", "entity/decorated_pot/");
    }

    public static void setupResourceProviders() {
        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "chest_center"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.CHEST_CENTER,
                                ModelIdentifiers.CHEST_CENTER_TRUNK,
                                ModelIdentifiers.CHRISTMAS_CHEST_CENTER,
                                ModelIdentifiers.CHRISTMAS_CHEST_CENTER_TRUNK
                        },
                        ModelSelector.CHEST_WITH_CHRISTMAS,
                        DynamicModelEffects.CHEST
                )
        );
        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "chest_left"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.CHEST_LEFT,
                                ModelIdentifiers.CHEST_LEFT_TRUNK,
                                ModelIdentifiers.CHRISTMAS_CHEST_LEFT,
                                ModelIdentifiers.CHRISTMAS_CHEST_LEFT_TRUNK
                        },
                        ModelSelector.CHEST_WITH_CHRISTMAS,
                        DynamicModelEffects.CHEST
                )
        );

        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "chest_right"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.CHEST_RIGHT,
                                ModelIdentifiers.CHEST_RIGHT_TRUNK,
                                ModelIdentifiers.CHRISTMAS_CHEST_RIGHT,
                                ModelIdentifiers.CHRISTMAS_CHEST_RIGHT_TRUNK
                        },
                        ModelSelector.CHEST_WITH_CHRISTMAS,
                        DynamicModelEffects.CHEST
                )
        );
        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "trapped_chest_center"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.TRAPPED_CHEST_CENTER,
                                ModelIdentifiers.TRAPPED_CHEST_CENTER_TRUNK,
                                ModelIdentifiers.CHRISTMAS_CHEST_CENTER,
                                ModelIdentifiers.CHRISTMAS_CHEST_CENTER_TRUNK
                        },
                        ModelSelector.CHEST_WITH_CHRISTMAS,
                        DynamicModelEffects.CHEST
                )
        );
        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "trapped_chest_left"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.TRAPPED_CHEST_LEFT,
                                ModelIdentifiers.TRAPPED_CHEST_LEFT_TRUNK,
                                ModelIdentifiers.CHRISTMAS_CHEST_LEFT,
                                ModelIdentifiers.CHRISTMAS_CHEST_LEFT_TRUNK
                        },
                        ModelSelector.CHEST_WITH_CHRISTMAS,
                        DynamicModelEffects.CHEST
                )
        );
        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "trapped_chest_right"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.TRAPPED_CHEST_RIGHT,
                                ModelIdentifiers.TRAPPED_CHEST_RIGHT_TRUNK,
                                ModelIdentifiers.CHRISTMAS_CHEST_RIGHT,
                                ModelIdentifiers.CHRISTMAS_CHEST_RIGHT_TRUNK
                        },
                        ModelSelector.CHEST_WITH_CHRISTMAS,
                        DynamicModelEffects.CHEST
                )
        );
        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "ender_chest_center"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.ENDER_CHEST_CENTER,
                                ModelIdentifiers.ENDER_CHEST_CENTER_TRUNK
                        },
                        ModelSelector.CHEST,
                        DynamicModelEffects.CHEST
                )
        );

        //? if >= 1.21.9 {
        for (String[] chest : ModelIdentifiers.COPPER_CHESTS) {
            Identifier[] models = ModelIdentifiers.COPPER_CHEST_MODELS.get(chest[0]);

            DynamicModelProvidingPlugin.register(
                    Identifier.fromNamespaceAndPath("builtin", chest[0] + "_center"),
                    () -> new DynamicUnbakedModel(
                            new Identifier[] {
                                    models[ModelIdentifiers.COPPER_CENTER],
                                    models[ModelIdentifiers.COPPER_CENTER_TRUNK]
                            },
                            ModelSelector.CHEST,
                            DynamicModelEffects.CHEST
                    )
            );
            DynamicModelProvidingPlugin.register(
                    Identifier.fromNamespaceAndPath("builtin", chest[0] + "_left"),
                    () -> new DynamicUnbakedModel(
                            new Identifier[] {
                                    models[ModelIdentifiers.COPPER_LEFT],
                                    models[ModelIdentifiers.COPPER_LEFT_TRUNK]
                            },
                            ModelSelector.CHEST,
                            DynamicModelEffects.CHEST
                    )
            );
            DynamicModelProvidingPlugin.register(
                    Identifier.fromNamespaceAndPath("builtin", chest[0] + "_right"),
                    () -> new DynamicUnbakedModel(
                            new Identifier[] {
                                    models[ModelIdentifiers.COPPER_RIGHT],
                                    models[ModelIdentifiers.COPPER_RIGHT_TRUNK]
                            },
                            ModelSelector.CHEST,
                            DynamicModelEffects.CHEST
                    )
            );
        }
        //?}

        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "bell_between_walls"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.BELL_BETWEEN_WALLS_WITH_BELL,
                                ModelIdentifiers.BELL_BETWEEN_WALLS
                        },
                        ModelSelector.BELL,
                        DynamicModelEffects.BELL
                )
        );
        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "bell_ceiling"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.BELL_CEILING_WITH_BELL,
                                ModelIdentifiers.BELL_CEILING
                        },
                        ModelSelector.BELL,
                        DynamicModelEffects.BELL
                )
        );
        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "bell_floor"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.BELL_FLOOR_WITH_BELL,
                                ModelIdentifiers.BELL_FLOOR
                        },
                        ModelSelector.BELL,
                        DynamicModelEffects.BELL
                )
        );
        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "bell_wall"),
                () -> new DynamicUnbakedModel(
                        new Identifier[] {
                                ModelIdentifiers.BELL_WALL_WITH_BELL,
                                ModelIdentifiers.BELL_WALL
                        },
                        ModelSelector.BELL,
                        DynamicModelEffects.BELL
                )
        );
        for (DyeColor color : EBEUtil.DEFAULTED_DYE_COLORS) {
            DynamicModelProvidingPlugin.register(
                    Identifier.fromNamespaceAndPath("builtin", color != null ? color.getName()+"_shulker_box" : "shulker_box"),
                    () -> new DynamicUnbakedModel(
                            new Identifier[] {
                                    ModelIdentifiers.SHULKER_BOXES.get(color),
                                    ModelIdentifiers.SHULKER_BOX_BOTTOMS.get(color)
                            },
                            ModelSelector.SHULKER_BOX,
                            DynamicModelEffects.SHULKER_BOX
                    )
            );
        }

        DecoratedPotModelSelector decoratedPotSelector = new DecoratedPotModelSelector();
        DynamicModelProvidingPlugin.register(
                Identifier.fromNamespaceAndPath("builtin", "decorated_pot"),
                () -> new DynamicUnbakedModel(
                        decoratedPotSelector.createModelIDs(),
                        decoratedPotSelector,
                        DynamicModelEffects.DECORATED_POT
                )
        );
    }

    private static void putCutoutMipped(Block block) {
        if (!RENDER_LAYERS.add(block)) return;

        //? if fabric && <= 1.21.5 {
        /*BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutoutMipped());
        *///?} else if <= 1.21.5 {
        /*ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutoutMipped());
        *///?}
        //? if fabric && >= 1.21.6 && <= 1.21.9 {
        /*BlockRenderLayerMap.putBlock(block, ChunkSectionLayer.CUTOUT_MIPPED);
        *///?} else if >= 1.21.6 && <= 1.21.9 {
        /*ItemBlockRenderTypes.setRenderLayer(block, ChunkSectionLayer.CUTOUT_MIPPED);
        *///?}
        //? if fabric && >= 1.21.11 && <= 1.21.11 {
        /*BlockRenderLayerMap.putBlock(block, ChunkSectionLayer.CUTOUT);
        *///?} else if >= 1.21.11 && <= 1.21.11 {
        /*ItemBlockRenderTypes.setRenderLayer(block, ChunkSectionLayer.CUTOUT);
        *///?}
    }

    private static void putCutout(Block block) {
        if (!RENDER_LAYERS.add(block)) return;

        //? if fabric && <= 1.21.5 {
        /*BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
        *///?} else if <= 1.21.5 {
        /*ItemBlockRenderTypes.setRenderLayer(block, RenderType.cutout());
        *///?}
        //? if fabric && >= 1.21.6 && <= 1.21.11 {
        /*BlockRenderLayerMap.putBlock(block, ChunkSectionLayer.CUTOUT);
        *///?} else if >= 1.21.6 && <= 1.21.11 {
        /*ItemBlockRenderTypes.setRenderLayer(block, ChunkSectionLayer.CUTOUT);
        *///?}
    }

    public static void setupChests() {
        putCutoutMipped(Blocks.CHEST);
        putCutoutMipped(Blocks.TRAPPED_CHEST);
        putCutoutMipped(Blocks.ENDER_CHEST);

        Function<BlockEntity, Integer> christmasChestSelector = entity -> {
            int os = DateUtil.isChristmas() ? 3 : 0;
            ChestType type = entity.getBlockState().getValue(BlockStateProperties.CHEST_TYPE);
            return type == ChestType.RIGHT ? 2 + os : type == ChestType.LEFT ? 1 + os : os;
        };
        //? if >= 1.21.9 {
        Function<BlockEntity, Integer> chestSelector = entity -> {
            int variant = COPPER_CHEST_INDICES.getOrDefault(entity.getBlockState().getBlock(), -1);
            if (variant < 0) return christmasChestSelector.apply(entity);

            ChestType type = entity.getBlockState().getValue(BlockStateProperties.CHEST_TYPE);
            int half = type == ChestType.RIGHT ? 2 : type == ChestType.LEFT ? 1 : 0;

            return 6 + (variant * 3) + half;
        };
        //?} else {
        /*Function<BlockEntity, Integer> chestSelector = christmasChestSelector;
        *///?}
        var chestRenderer = new ChestBlockEntityRendererOverride(() -> {
                    //? if <= 1.21.4 {
                    /*var lids = new java.util.ArrayList<BakedModel>();
                    *///?} else {
                    var lids = new java.util.ArrayList<BlockStateModel>();
                    //?}

                    lids.add(ModelIdentifiers.getBakedModel(ModelIdentifiers.CHEST_CENTER_LID));
                    lids.add(ModelIdentifiers.getBakedModel(ModelIdentifiers.CHEST_LEFT_LID));
                    lids.add(ModelIdentifiers.getBakedModel(ModelIdentifiers.CHEST_RIGHT_LID));
                    lids.add(ModelIdentifiers.getBakedModel(ModelIdentifiers.CHRISTMAS_CHEST_CENTER_LID));
                    lids.add(ModelIdentifiers.getBakedModel(ModelIdentifiers.CHRISTMAS_CHEST_LEFT_LID));
                    lids.add(ModelIdentifiers.getBakedModel(ModelIdentifiers.CHRISTMAS_CHEST_RIGHT_LID));

                    //? if >= 1.21.9 {
                    for (String[] chest : ModelIdentifiers.COPPER_CHESTS) {
                        Identifier[] models = ModelIdentifiers.COPPER_CHEST_MODELS.get(chest[0]);

                        lids.add(ModelIdentifiers.getBakedModel(models[ModelIdentifiers.COPPER_CENTER_LID]));
                        lids.add(ModelIdentifiers.getBakedModel(models[ModelIdentifiers.COPPER_LEFT_LID]));
                        lids.add(ModelIdentifiers.getBakedModel(models[ModelIdentifiers.COPPER_RIGHT_LID]));
                    }
                    //?}

                    //? if <= 1.21.4 {
                    /*return lids.toArray(new BakedModel[0]);
                    *///?} else {
                    return lids.toArray(new BlockStateModel[0]);
                    //?}
                }, chestSelector);

        EnhancedBlockEntityRegistry.register(Blocks.CHEST, BlockEntityTypes.CHEST, BlockEntityRenderCondition.CHEST, chestRenderer);

        //? if >= 1.21.9 {
        Block[] copperChests = copperChestBlocks();
        COPPER_CHEST_INDICES.clear();

        for (int i = 0; i < ModelIdentifiers.COPPER_CHESTS.length; i++) {
            Block block = copperChests[i];

            COPPER_CHEST_INDICES.put(block, i);
            putCutoutMipped(block);
            EnhancedBlockEntityRegistry.register(block, BlockEntityTypes.CHEST, BlockEntityRenderCondition.CHEST, chestRenderer);
        }
        //?}
        EnhancedBlockEntityRegistry.register(Blocks.TRAPPED_CHEST, BlockEntityTypes.TRAPPED_CHEST, BlockEntityRenderCondition.CHEST,
                new ChestBlockEntityRendererOverride(() -> {
                    //? if <= 1.21.4 {
                    /*return new BakedModel[] {
                    *///?} else {
                    return new BlockStateModel[] {
                    //?}
                            ModelIdentifiers.getBakedModel(ModelIdentifiers.TRAPPED_CHEST_CENTER_LID),
                            ModelIdentifiers.getBakedModel(ModelIdentifiers.TRAPPED_CHEST_LEFT_LID),
                            ModelIdentifiers.getBakedModel(ModelIdentifiers.TRAPPED_CHEST_RIGHT_LID),
                            ModelIdentifiers.getBakedModel(ModelIdentifiers.CHRISTMAS_CHEST_CENTER_LID),
                            ModelIdentifiers.getBakedModel(ModelIdentifiers.CHRISTMAS_CHEST_LEFT_LID),
                            ModelIdentifiers.getBakedModel(ModelIdentifiers.CHRISTMAS_CHEST_RIGHT_LID)
                    };
                }, christmasChestSelector)
        );
        EnhancedBlockEntityRegistry.register(Blocks.ENDER_CHEST, BlockEntityTypes.ENDER_CHEST, BlockEntityRenderCondition.CHEST,
                new ChestBlockEntityRendererOverride(() -> {
                    //? if <= 1.21.4 {
                    /*return new BakedModel[] { ModelIdentifiers.getBakedModel(ModelIdentifiers.ENDER_CHEST_CENTER_LID) };
                    *///?} else {
                    return new BlockStateModel[] { ModelIdentifiers.getBakedModel(ModelIdentifiers.ENDER_CHEST_CENTER_LID) };
                    //?}
                }, entity -> 0)
        );
    }

    //? if >= 1.21.9 {
    //? if <= 26.1 {
    /*private static Block[] copperChestBlocks() {
        return new Block[] {
                Blocks.COPPER_CHEST, Blocks.EXPOSED_COPPER_CHEST,
                Blocks.WEATHERED_COPPER_CHEST, Blocks.OXIDIZED_COPPER_CHEST,
                Blocks.WAXED_COPPER_CHEST, Blocks.WAXED_EXPOSED_COPPER_CHEST,
                Blocks.WAXED_WEATHERED_COPPER_CHEST, Blocks.WAXED_OXIDIZED_COPPER_CHEST
        };
    }
    *///?} else {
    private static Block[] copperChestBlocks() {
        return Blocks.COPPER_CHEST.asList().toArray(new Block[0]);
    }
    //?}
    //?}

    public static void setupSigns() {
        for (var sign : new Block[] {
                Blocks.OAK_SIGN, Blocks.OAK_WALL_SIGN,
                Blocks.BIRCH_SIGN, Blocks.BIRCH_WALL_SIGN,
                Blocks.SPRUCE_SIGN, Blocks.SPRUCE_WALL_SIGN,
                Blocks.JUNGLE_SIGN, Blocks.JUNGLE_WALL_SIGN,
                Blocks.ACACIA_SIGN, Blocks.ACACIA_WALL_SIGN,
                Blocks.DARK_OAK_SIGN, Blocks.DARK_OAK_WALL_SIGN,
                Blocks.MANGROVE_SIGN, Blocks.MANGROVE_WALL_SIGN,
                Blocks.CHERRY_SIGN, Blocks.CHERRY_WALL_SIGN,
                Blocks.CRIMSON_SIGN, Blocks.CRIMSON_WALL_SIGN,
                Blocks.WARPED_SIGN, Blocks.WARPED_WALL_SIGN,
                Blocks.BAMBOO_SIGN, Blocks.BAMBOO_WALL_SIGN,
                Blocks.PALE_OAK_SIGN, Blocks.PALE_OAK_WALL_SIGN
        }) {
            EnhancedBlockEntityRegistry.register(sign, BlockEntityTypes.SIGN, BlockEntityRenderCondition.SIGN,
                    new SignBlockEntityRendererOverride()
            );
        }

        for (var sign : new Block[] {
                Blocks.OAK_HANGING_SIGN, Blocks.OAK_WALL_HANGING_SIGN,
                Blocks.BIRCH_HANGING_SIGN, Blocks.BIRCH_WALL_HANGING_SIGN,
                Blocks.SPRUCE_HANGING_SIGN, Blocks.SPRUCE_WALL_HANGING_SIGN,
                Blocks.JUNGLE_HANGING_SIGN, Blocks.JUNGLE_WALL_HANGING_SIGN,
                Blocks.ACACIA_HANGING_SIGN, Blocks.ACACIA_WALL_HANGING_SIGN,
                Blocks.DARK_OAK_HANGING_SIGN, Blocks.DARK_OAK_WALL_HANGING_SIGN,
                Blocks.MANGROVE_HANGING_SIGN, Blocks.MANGROVE_WALL_HANGING_SIGN,
                Blocks.CHERRY_HANGING_SIGN, Blocks.CHERRY_WALL_HANGING_SIGN,
                Blocks.CRIMSON_HANGING_SIGN, Blocks.CRIMSON_WALL_HANGING_SIGN,
                Blocks.WARPED_HANGING_SIGN, Blocks.WARPED_WALL_HANGING_SIGN,
                Blocks.BAMBOO_HANGING_SIGN, Blocks.BAMBOO_WALL_HANGING_SIGN,
                Blocks.PALE_OAK_HANGING_SIGN, Blocks.PALE_OAK_WALL_HANGING_SIGN
        }) {
            EnhancedBlockEntityRegistry.register(sign, BlockEntityTypes.HANGING_SIGN, BlockEntityRenderCondition.SIGN,
                    new SignBlockEntityRendererOverride()
            );
            putCutout(sign);
        }
    }

    public static void setupBells() {
        EnhancedBlockEntityRegistry.register(Blocks.BELL, BlockEntityTypes.BELL, BlockEntityRenderCondition.BELL,
                new BellBlockEntityRendererOverride()
        );
    }

    public static void setupBeds() {
        //? if <= 26.1 {
        /*EnhancedBlockEntityRegistry.register(Blocks.BLACK_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.BLUE_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.BROWN_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.CYAN_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.GRAY_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.GREEN_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.LIGHT_BLUE_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.LIGHT_GRAY_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.LIME_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.MAGENTA_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.ORANGE_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.PINK_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.PURPLE_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.RED_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.WHITE_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        EnhancedBlockEntityRegistry.register(Blocks.YELLOW_BED, BlockEntityTypes.BED, BlockEntityRenderCondition.NEVER, BlockEntityRendererOverride.NO_OP);
        *///?}
    }

    //? if >= 26.1 {
    private static Block shulkerBoxByColor(DyeColor color) {
        if (color == null) return Blocks.SHULKER_BOX;

        //? if >= 26.2 {
        return Blocks.DYED_SHULKER_BOX.pick(color);
        //?}

        //? if <= 26.1 {
        /*return switch (color) {
            case WHITE -> Blocks.WHITE_SHULKER_BOX;
            case ORANGE -> Blocks.ORANGE_SHULKER_BOX;
            case MAGENTA -> Blocks.MAGENTA_SHULKER_BOX;
            case LIGHT_BLUE -> Blocks.LIGHT_BLUE_SHULKER_BOX;
            case YELLOW -> Blocks.YELLOW_SHULKER_BOX;
            case LIME -> Blocks.LIME_SHULKER_BOX;
            case PINK -> Blocks.PINK_SHULKER_BOX;
            case GRAY -> Blocks.GRAY_SHULKER_BOX;
            case LIGHT_GRAY -> Blocks.LIGHT_GRAY_SHULKER_BOX;
            case CYAN -> Blocks.CYAN_SHULKER_BOX;
            case PURPLE -> Blocks.PURPLE_SHULKER_BOX;
            case BLUE -> Blocks.BLUE_SHULKER_BOX;
            case BROWN -> Blocks.BROWN_SHULKER_BOX;
            case GREEN -> Blocks.GREEN_SHULKER_BOX;
            case RED -> Blocks.RED_SHULKER_BOX;
            case BLACK -> Blocks.BLACK_SHULKER_BOX;
        };
        *///?}
    }
    //?}

    public static void setupShulkerBoxes() {
        for (DyeColor color : EBEUtil.DEFAULTED_DYE_COLORS) {
            //? if <= 1.21.11 {
            /*var block = ShulkerBoxBlock.getBlockByColor(color);
            *///?} else {
            var block = shulkerBoxByColor(color);
            //?}
            putCutoutMipped(block);
            EnhancedBlockEntityRegistry.register(block, BlockEntityTypes.SHULKER_BOX, BlockEntityRenderCondition.SHULKER_BOX,
                    new ShulkerBoxBlockEntityRendererOverride((map) -> {
                        for (DyeColor dc : EBEUtil.DEFAULTED_DYE_COLORS) {
                            map.put(dc, ModelIdentifiers.getBakedModel(ModelIdentifiers.SHULKER_BOX_LIDS.get(dc)));
                        }
                    })
            );
        }
    }

    public static void setupDecoratedPots() {
        EnhancedBlockEntityRegistry.register(Blocks.DECORATED_POT, BlockEntityTypes.DECORATED_POT,
                BlockEntityRenderCondition.DECORATED_POT, new DecoratedPotBlockEntityRendererOverride());
    }
}
