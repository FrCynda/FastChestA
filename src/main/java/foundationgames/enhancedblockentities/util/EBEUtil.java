package foundationgames.enhancedblockentities.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import foundationgames.enhancedblockentities.EnhancedBlockEntities;
import foundationgames.enhancedblockentities.platform.Platform;
//? if <= 1.21.11 {
/*import net.minecraft.client.renderer.ItemBlockRenderTypes;
*///?} else {
import com.mojang.blaze3d.vertex.QuadInstance;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockModelRenderState;
//?}
//? if <= 26.1 {
/*import net.minecraft.client.renderer.MultiBufferSource;
*///?}
//? if >= 1.21.9 {
import net.minecraft.client.renderer.SubmitNodeCollector;
//?}
//? if <= 1.21.11 {
/*import net.minecraft.client.renderer.block.model.BakedQuad;
*///?} else {
import net.minecraft.client.resources.model.geometry.BakedQuad;
//?}
//? if <= 1.21.4 {
/*import net.minecraft.client.resources.model.BakedModel;
*///?} else {
//? if <= 1.21.11 {
/*import net.minecraft.client.renderer.block.model.BlockStateModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
*///?} else {
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
//?}
//?}
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
//? if >= 26.3 {
/*import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ItemStackTemplate;
*///?}
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.VanillaPackResources;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public enum EBEUtil {;
    private static final Direction[] FACES = java.util.Arrays.copyOf(Direction.values(), 7);

    public static Direction faceFromIndex(int index) {
        return FACES[index];
    }

    private static final RandomSource dummy = RandomSource.create();

    // Contains all dye colors, and null
    public static final DyeColor[] DEFAULTED_DYE_COLORS;
    // All directions except up and down
    public static final Direction[] HORIZONTAL_DIRECTIONS;

    static {
        var dColors = DyeColor.values();
        DEFAULTED_DYE_COLORS = new DyeColor[dColors.length + 1];
        System.arraycopy(dColors, 0, DEFAULTED_DYE_COLORS, 0, dColors.length);

        HORIZONTAL_DIRECTIONS = new Direction[] {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};
    }

    //? if <= 26.1 {
    /*public static ResourceKey<DecoratedPotPattern> potPatternFromSherd(Optional<Item> sherd) {
        return sherd.map(DecoratedPotPatterns::getPatternFromItem).orElse(DecoratedPotPatterns.BLANK);
    }

    public static Collection<ResourceKey<DecoratedPotPattern>> potPatternKeys() {
        return BuiltInRegistries.DECORATED_POT_PATTERN.registryKeySet();
    }
    *///?} else if <= 26.2 {
    private static final Map<ResourceKey<Item>, ResourceKey<DecoratedPotPattern>> POT_PATTERNS_BY_ITEM = new HashMap<>();

    static {
        DecoratedPotPatterns.itemToPatternMappings(POT_PATTERNS_BY_ITEM::put);
    }

    public static ResourceKey<DecoratedPotPattern> potPatternFromSherd(Optional<Item> sherd) {
        var item = sherd.orElse(null);
        var itemKey = item != null ? BuiltInRegistries.ITEM.getResourceKey(item).orElse(null) : null;
        var pattern = itemKey != null ? POT_PATTERNS_BY_ITEM.get(itemKey) : null;

        return pattern != null ? pattern : DecoratedPotPatterns.BLANK;
    }

    public static Collection<ResourceKey<DecoratedPotPattern>> potPatternKeys() {
        return BuiltInRegistries.DECORATED_POT_PATTERN.registryKeySet();
    }

    public static Identifier potPatternAssetId(ResourceKey<DecoratedPotPattern> patternKey) {
        return BuiltInRegistries.DECORATED_POT_PATTERN.getValueOrThrow(patternKey).assetId();
    }
    //?} else {
    /*private static final ResourceKey<DecoratedPotPattern> POT_PATTERN_BLANK =
            ResourceKey.create(Registries.DECORATED_POT_PATTERN, Identifier.withDefaultNamespace("blank"));

    private static final List<ResourceKey<DecoratedPotPattern>> POT_PATTERN_KEYS = List.of(
            POT_PATTERN_BLANK,
            DecoratedPotPatterns.ANGLER,
            DecoratedPotPatterns.ARCHER,
            DecoratedPotPatterns.ARMS_UP,
            DecoratedPotPatterns.BLADE,
            DecoratedPotPatterns.BREWER,
            DecoratedPotPatterns.BURN,
            DecoratedPotPatterns.DANGER,
            DecoratedPotPatterns.EXPLORER,
            DecoratedPotPatterns.FLOW,
            DecoratedPotPatterns.FRIEND,
            DecoratedPotPatterns.GUSTER,
            DecoratedPotPatterns.HEART,
            DecoratedPotPatterns.HEARTBREAK,
            DecoratedPotPatterns.HOWL,
            DecoratedPotPatterns.MINER,
            DecoratedPotPatterns.MOURNER,
            DecoratedPotPatterns.PLENTY,
            DecoratedPotPatterns.PRIZE,
            DecoratedPotPatterns.SCRAPE,
            DecoratedPotPatterns.SHEAF,
            DecoratedPotPatterns.SHELTER,
            DecoratedPotPatterns.SKULL,
            DecoratedPotPatterns.SNORT
    );

    public static ResourceKey<DecoratedPotPattern> potPatternFromSherd(Optional<ItemStackTemplate> sherd) {
        var sherdStack = sherd.orElse(null);
        var pattern = sherdStack != null ? sherdStack.get(DataComponents.PROVIDES_POTTERY_PATTERN) : null;
        var patternKey = pattern != null ? pattern.unwrapKey().orElse(null) : null;

        return patternKey != null && POT_PATTERN_KEYS.contains(patternKey) ? patternKey : POT_PATTERN_BLANK;
    }

    public static Collection<ResourceKey<DecoratedPotPattern>> potPatternKeys() {
        return POT_PATTERN_KEYS;
    }

    public static Identifier potPatternAssetId(ResourceKey<DecoratedPotPattern> patternKey) {
        return POT_PATTERN_BLANK.equals(patternKey)
                ? Identifier.withDefaultNamespace("decorated_pot_side")
                : patternKey.identifier().withSuffix("_pottery_pattern");
    }
    *///?}

    public static int angle(Direction dir) {
        int h = dir.get2DDataValue();
        return h >= 0 ? h * 90 : 0;
    }

    public static void rotate(PoseStack matrices, Quaternionf rotation) {
        //? if <= 26.2 {
        matrices.mulPose(rotation);
        //?} else {
        /*matrices.rotate(rotation);
        *///?}
    }

    //? if >= 1.21.9 {
    //? if <= 1.21.11 {
    /*public static void renderBakedModel(SubmitNodeCollector output, BlockState state, PoseStack matrices, BlockStateModel model, int light, int overlay) {
        if (model == null) return;
        output.submitBlockModel(matrices, ItemBlockRenderTypes.getRenderType(state), model, 1, 1, 1, light, overlay, 0);
    }
    *///?} else {
    public static void renderBakedModel(SubmitNodeCollector output, BlockState state, PoseStack matrices, BlockStateModel model, int light, int overlay) {
        if (model == null) return;

        var parts = new ObjectArrayList<BlockStateModelPart>();
        model.collectParts(dummy, parts);
        if (parts.isEmpty()) return;

        //? if <= 26.1 {
        /*output.submitBlockModel(matrices, Sheets.cutoutBlockSheet(), parts,
                BlockModelRenderState.EMPTY_TINTS, light, overlay, 0);
        *///?} else {
        output.submitBlockModel(matrices, Sheets.cutoutBlockItemSheet(), parts,
                BlockModelRenderState.EMPTY_TINTS, light, overlay, 0);
        //?}
    }
    //?}
    //?}

    //? if <= 1.21.4 {
    /*public static void renderBakedModel(MultiBufferSource vertexConsumers, BlockState state, PoseStack matrices, BakedModel model, int light, int overlay) {
        if (model == null) return;
        VertexConsumer vertices = vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(state));
        for (int i = 0; i <= 6; i++) {
            for (BakedQuad q : model.getQuads(null, faceFromIndex(i), dummy)) {
                vertices.putBulkData(matrices.last(), q, 1, 1, 1, 1, light, overlay);
            }
        }
    }
    *///?} else {
    //? if <= 1.21.11 {
    /*public static void renderBakedModel(MultiBufferSource vertexConsumers, BlockState state, PoseStack matrices, BlockStateModel model, int light, int overlay) {
        if (model == null) return;
        VertexConsumer vertices = vertexConsumers.getBuffer(ItemBlockRenderTypes.getRenderType(state));
        for (BlockStateModelPart part : model.collectParts(dummy)) {
            for (int i = 0; i <= 6; i++) {
                for (BakedQuad q : part.getQuads(faceFromIndex(i))) {
                    vertices.putBulkData(matrices.last(), q, 1, 1, 1, 1, light, overlay);
                }
            }
        }
    }
    *///?} else {
    //? if <= 26.1 {
    /*public static void renderBakedModel(MultiBufferSource vertexConsumers, BlockState state, PoseStack matrices, BlockStateModel model, int light, int overlay) {
        if (model == null) return;

        var parts = new ObjectArrayList<BlockStateModelPart>();
        model.collectParts(dummy, parts);

        var quadInstance = new QuadInstance();
        quadInstance.setLightCoords(light);
        quadInstance.setOverlayCoords(overlay);

        VertexConsumer vertices = vertexConsumers.getBuffer(Sheets.cutoutBlockSheet());
        for (BlockStateModelPart part : parts) {
            for (int i = 0; i <= 6; i++) {
                for (BakedQuad q : part.getQuads(faceFromIndex(i))) {
                    vertices.putBakedQuad(matrices.last(), q, quadInstance);
                }
            }
        }
    }
    *///?}
    //?}
    //?}

    public static boolean isVanillaResourcePack(PackResources pack) {
        return (pack instanceof VanillaPackResources) ||
                // Terrible quilt compat hack
                ("org.quiltmc.qsl.resource.loader.api.GroupResourcePack$Wrapped".equals(pack.getClass().getName()));
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(EnhancedBlockEntities.NAMESPACE, path);
    }

    public static final String DUMP_FOLDER_NAME = "enhanced_bes_dump";

    public static void dumpResources() throws IOException {
        var path = Platform.getGameDir().resolve(DUMP_FOLDER_NAME);

        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        ResourceUtil.dumpAllPacks(path);
    }
}
