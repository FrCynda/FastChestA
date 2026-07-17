package foundationgames.enhancedblockentities.client.model.misc;

import foundationgames.enhancedblockentities.client.model.ModelIdentifiers;
import foundationgames.enhancedblockentities.client.model.ModelSelector;
import foundationgames.enhancedblockentities.util.EBEUtil;
import foundationgames.enhancedblockentities.util.duck.AppearanceStateHolder;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
//? if <= 1.21.11 {
/*import net.minecraft.world.level.BlockAndTintGetter;
*///?} else {
import net.minecraft.client.renderer.block.BlockAndTintGetter;
//?}
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class DecoratedPotModelSelector extends ModelSelector {
    public static final int BUILTIN_MODEL_COUNT = 2;

    public static final int IDX_EMPTY = 0;
    public static final int IDX_BASE_POT = 1;

    private final List<ResourceKey<DecoratedPotPattern>> potteryPatterns;

    public DecoratedPotModelSelector() {
        super(5);

        this.potteryPatterns = new ArrayList<>(EBEUtil.potPatternKeys());
    }

    public Identifier[] createModelIDs() {
        ModelIdentifiers.refreshPotteryPatterns();

        var ids = new Identifier[BUILTIN_MODEL_COUNT + potteryPatterns.size() * 4];
        ids[IDX_EMPTY] = ModelIdentifiers.DECORATED_POT_SHAKING;
        ids[IDX_BASE_POT] = ModelIdentifiers.DECORATED_POT_BASE;

        int idIndex = BUILTIN_MODEL_COUNT;
        for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
            for (var pattern : this.potteryPatterns) {
                ids[idIndex] = ModelIdentifiers.POTTERY_PATTERNS.get(pattern)[dirIndex];

                idIndex++;
            }
        }

        return ids;
    }

    @Override
    public void writeModelIndices(BlockAndTintGetter view, BlockState state, BlockPos pos, Supplier<RandomSource> rand, int[] indices) {
        final int patternCount = potteryPatterns.size();

        indices[0] = IDX_BASE_POT;
        if (view.getBlockEntity(pos) instanceof DecoratedPotBlockEntity pot) {
            if (pot instanceof AppearanceStateHolder ms && ms.getModelState() > 0) {
                Arrays.fill(indices, IDX_EMPTY);
                return;
            }

            var sherds = pot.getDecorations();

            indices[1] = BUILTIN_MODEL_COUNT + patternIndex(EBEUtil.potPatternFromSherd(sherds.back()), patternCount);
            indices[2] = BUILTIN_MODEL_COUNT + patternIndex(EBEUtil.potPatternFromSherd(sherds.left()), patternCount) + patternCount;
            indices[3] = BUILTIN_MODEL_COUNT + patternIndex(EBEUtil.potPatternFromSherd(sherds.right()), patternCount) + patternCount * 2;
            indices[4] = BUILTIN_MODEL_COUNT + patternIndex(EBEUtil.potPatternFromSherd(sherds.front()), patternCount) + patternCount * 3;

            return;
        }

        for (int i = 0; i < 4; i++) {
            indices[1 + i] = BUILTIN_MODEL_COUNT + patternCount * i;
        }
    }

    private int patternIndex(ResourceKey<DecoratedPotPattern> patternKey, int max) {
        return Mth.clamp(this.potteryPatterns.indexOf(patternKey), 0, max - 1);
    }
}
