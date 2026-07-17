package foundationgames.enhancedblockentities.client.model;

//? if fabric && <= 1.21.4 {
/*import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBakedModel;
import net.fabricmc.fabric.api.renderer.v1.model.ModelHelper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class DynamicBakedModel implements BakedModel, FabricBakedModel {
    private final BakedModel[] models;
    private final ModelSelector selector;
    private final DynamicModelEffects effects;

    private final ThreadLocal<int[]> activeModelIndices;
    private final ThreadLocal<BakedModel[]> displayedModels;

    public DynamicBakedModel(BakedModel[] models, ModelSelector selector, DynamicModelEffects effects) {
        this.models = models;
        this.selector = selector;
        this.effects = effects;

        this.activeModelIndices = ThreadLocal.withInitial(() -> new int[selector.displayedModelCount]);
        this.displayedModels = ThreadLocal.withInitial(() -> new BakedModel[selector.displayedModelCount]);
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }

    @Override
    public void emitBlockQuads(QuadEmitter emitter, BlockAndTintGetter view, BlockState state, BlockPos pos, Supplier<RandomSource> rng, Predicate<Direction> cullTest) {
        RenderMaterial mat = null;

        var indices = this.activeModelIndices.get();
        var models = this.displayedModels.get();

        getSelector().writeModelIndices(view, state, pos, rng, indices);
        for (int i = 0; i < indices.length; i++) {
            int modelIndex = indices[i];

            if (modelIndex >= 0) {
                models[i] = this.models[modelIndex];
            } else {
                models[i] = null;
            }
        }

        var renderer = Renderer.get();
        if (renderer != null) {
            mat = renderer.materialById(RenderMaterial.STANDARD_ID);
        }

        for (int i = 0; i <= 6; i++) {
            Direction dir = ModelHelper.faceFromIndex(i);
            for (BakedModel model : models) if (model != null) {
                for (BakedQuad quad : model.getQuads(state, dir, rng.get())) {
                    emitter.fromVanilla(quad, mat, dir);
                    emitter.emit();
                }
            }
        }
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, RandomSource random) {
        return models[0].getQuads(state, face, random);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return getEffects().ambientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return models[getSelector().getParticleModelIndex()].getParticleIcon();
    }

    @Override
    public ItemTransforms getTransforms() {
        return null;
    }

    public BakedModel[] getModels() {
        return models;
    }

    public ModelSelector getSelector() {
        return selector;
    }

    public DynamicModelEffects getEffects() {
        return effects;
    }
}
*///?}
//? if neoforge && <= 1.21.4 {
/*import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelProperty;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DynamicBakedModel implements IDynamicBakedModel {
    public static final ModelProperty<int[]> MODEL_INDICES = new ModelProperty<>();

    private static final ThreadLocal<RandomSource> RANDOM = ThreadLocal.withInitial(RandomSource::create);

    private final BakedModel[] models;
    private final ModelSelector selector;
    private final DynamicModelEffects effects;

    public DynamicBakedModel(BakedModel[] models, ModelSelector selector, DynamicModelEffects effects) {
        this.models = models;
        this.selector = selector;
        this.effects = effects;
    }

    @Override
    public ModelData getModelData(BlockAndTintGetter view, BlockPos pos, BlockState state, ModelData modelData) {
        var indices = new int[this.selector.displayedModelCount];
        this.selector.writeModelIndices(view, state, pos, RANDOM::get, indices);

        return modelData.derive().with(MODEL_INDICES, indices).build();
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, RandomSource random, ModelData data, @Nullable RenderType renderType) {
        var indices = data.get(MODEL_INDICES);
        if (indices == null) return this.models[0].getQuads(state, face, random);

        var quads = new ArrayList<BakedQuad>();
        for (int modelIndex : indices) {
            if (modelIndex < 0 || modelIndex >= this.models.length) continue;

            var model = this.models[modelIndex];
            if (model != null) quads.addAll(model.getQuads(state, face, random, data, renderType));
        }

        return quads;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return getEffects().ambientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return models[getSelector().getParticleModelIndex()].getParticleIcon();
    }

    @Override
    public ItemTransforms getTransforms() {
        return null;
    }

    public BakedModel[] getModels() {
        return models;
    }

    public ModelSelector getSelector() {
        return selector;
    }

    public DynamicModelEffects getEffects() {
        return effects;
    }
}
*///?}
//? if forge && <= 1.21.4 {
/*import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.IDynamicBakedModel;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelProperty;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DynamicBakedModel implements IDynamicBakedModel {
    public static final ModelProperty<int[]> MODEL_INDICES = new ModelProperty<>();

    private static final ThreadLocal<RandomSource> RANDOM = ThreadLocal.withInitial(RandomSource::create);

    private final BakedModel[] models;
    private final ModelSelector selector;
    private final DynamicModelEffects effects;

    public DynamicBakedModel(BakedModel[] models, ModelSelector selector, DynamicModelEffects effects) {
        this.models = models;
        this.selector = selector;
        this.effects = effects;
    }

    @Override
    public ModelData getModelData(BlockAndTintGetter view, BlockPos pos, BlockState state, ModelData modelData) {
        var indices = new int[this.selector.displayedModelCount];
        this.selector.writeModelIndices(view, state, pos, RANDOM::get, indices);

        return modelData.derive().with(MODEL_INDICES, indices).build();
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction face, RandomSource random, ModelData data, @Nullable RenderType renderType) {
        var indices = data.get(MODEL_INDICES);
        if (indices == null) return this.models[0].getQuads(state, face, random);

        var quads = new ArrayList<BakedQuad>();
        for (int modelIndex : indices) {
            if (modelIndex < 0 || modelIndex >= this.models.length) continue;

            var model = this.models[modelIndex];
            if (model != null) quads.addAll(model.getQuads(state, face, random, data, renderType));
        }

        return quads;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return getEffects().ambientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return models[getSelector().getParticleModelIndex()].getParticleIcon();
    }

    @Override
    public ItemTransforms getTransforms() {
        return null;
    }

    public BakedModel[] getModels() {
        return models;
    }

    public ModelSelector getSelector() {
        return selector;
    }

    public DynamicModelEffects getEffects() {
        return effects;
    }
}
*///?}
//? if >= 1.21.5 {
//? if fabric && <= 1.21.11 {
/*import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.renderer.v1.model.FabricBlockStateModel;
*///?}
//? if fabric && >= 26.1 {
import net.fabricmc.fabric.api.client.renderer.v1.mesh.QuadEmitter;
import net.fabricmc.fabric.api.client.renderer.v1.model.FabricBlockStateModel;
//?}
//? if neoforge && >= 1.21.5 {
/*import net.neoforged.neoforge.client.model.DynamicBlockStateModel;
*///?}
//? if forge && >= 1.21.5 {
/*import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.data.ModelProperty;
*///?}
//? if forge && >= 1.21.5 && <= 1.21.5 {
/*import net.minecraft.client.renderer.RenderType;
*///?} else if forge && <= 1.21.11 {
/*import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
*///?}
//? if <= 1.21.11 {
/*import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockStateModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.BlockAndTintGetter;
*///?} else {
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.sprite.Material;
//?}
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

//? if fabric {
public class DynamicBakedModel implements BlockStateModel, FabricBlockStateModel {
//?} else if neoforge {
/*public class DynamicBakedModel implements DynamicBlockStateModel {
*///?} else if forge {
/*public class DynamicBakedModel implements BlockStateModel {
*///?}
    //? if forge {
    /*public static final ModelProperty<int[]> MODEL_INDICES = new ModelProperty<>();

    private static final ThreadLocal<RandomSource> RANDOM = ThreadLocal.withInitial(RandomSource::create);

    *///?}
    private final BlockStateModelPart[] models;
    private final ModelSelector selector;
    private final DynamicModelEffects effects;

    private final ThreadLocal<int[]> activeModelIndices;

    //? if >= 26.1 {
    private final int materialFlags;
    //?}

    public DynamicBakedModel(BlockStateModelPart[] models, ModelSelector selector, DynamicModelEffects effects) {
        this.models = new BlockStateModelPart[models.length];
        for (int i = 0; i < models.length; i++) {
            this.models[i] = models[i] != null ? new EffectPart(models[i], effects) : null;
        }

        this.selector = selector;
        this.effects = effects;

        this.activeModelIndices = ThreadLocal.withInitial(() -> new int[selector.displayedModelCount]);

        //? if >= 26.1 {
        int flags = 0;
        for (var model : this.models) {
            if (model != null) flags |= model.materialFlags();
        }
        this.materialFlags = flags;
        //?}
    }

    //? if fabric {
    @Override
    public void emitQuads(QuadEmitter emitter, BlockAndTintGetter view, BlockPos pos, BlockState state, RandomSource random, Predicate<Direction> cullTest) {
        var indices = this.activeModelIndices.get();

        getSelector().writeModelIndices(view, state, pos, () -> random, indices);

        for (int modelIndex : indices) {
            if (modelIndex < 0 || modelIndex >= this.models.length) continue;

            var model = this.models[modelIndex];
            if (model != null) model.emitQuads(emitter, cullTest);
        }
    }
    //?} else if neoforge {
    /*@Override
    public void collectParts(BlockAndTintGetter view, BlockPos pos, BlockState state, RandomSource random, List<BlockStateModelPart> parts) {
        var indices = this.activeModelIndices.get();

        getSelector().writeModelIndices(view, state, pos, () -> random, indices);

        for (int modelIndex : indices) {
            if (modelIndex < 0 || modelIndex >= this.models.length) continue;

            var model = this.models[modelIndex];
            if (model != null) parts.add(model);
        }
    }
    *///?} else if forge {
    /*@Override
    public ModelData getModelData(BlockAndTintGetter view, BlockPos pos, BlockState state, ModelData modelData) {
        var indices = new int[getSelector().displayedModelCount];

        getSelector().writeModelIndices(view, state, pos, RANDOM::get, indices);

        return modelData.derive().with(MODEL_INDICES, indices).build();
    }

    private void collectDynamicParts(List<BlockStateModelPart> parts, ModelData data) {
        var indices = data.get(MODEL_INDICES);
        if (indices == null) {
            var particle = this.models[getSelector().getParticleModelIndex()];
            if (particle != null) parts.add(particle);
            return;
        }

        for (int modelIndex : indices) {
            if (modelIndex < 0 || modelIndex >= this.models.length) continue;

            var model = this.models[modelIndex];
            if (model != null) parts.add(model);
        }
    }

    public void collectAllParts(List<BlockStateModelPart> parts) {
        for (var model : this.models) {
            if (model != null) parts.add(model);
        }
    }
    *///?}
    //? if forge && >= 1.21.5 && <= 1.21.5 {
    /*@Override
    public void collectParts(RandomSource random, List<BlockStateModelPart> parts, ModelData data, RenderType renderType) {
        collectDynamicParts(parts, data);
    }
    *///?} else if forge && <= 1.21.11 {
    /*@Override
    public void collectParts(RandomSource random, List<BlockStateModelPart> parts, ModelData data, ChunkSectionLayer layer) {
        collectDynamicParts(parts, data);
    }
    *///?} else if forge {
    /*@Override
    public void collectParts(RandomSource random, List<BlockStateModelPart> parts, ModelData data) {
        collectDynamicParts(parts, data);
    }
    *///?}

    @Override
    public void collectParts(RandomSource random, List<BlockStateModelPart> parts) {
        var model = this.models[getSelector().getParticleModelIndex()];
        if (model != null) parts.add(model);
    }

    //? if <= 1.21.11 {
    /*@Override
    public TextureAtlasSprite particleIcon() {
        return this.models[getSelector().getParticleModelIndex()].particleIcon();
    }
    *///?} else {
    @Override
    public Material.Baked particleMaterial() {
        return this.models[getSelector().getParticleModelIndex()].particleMaterial();
    }

    @Override
    public int materialFlags() {
        return this.materialFlags;
    }
    //?}

    public BlockStateModelPart[] getModels() {
        return models;
    }

    public ModelSelector getSelector() {
        return selector;
    }

    public DynamicModelEffects getEffects() {
        return effects;
    }

    private record EffectPart(BlockStateModelPart delegate, DynamicModelEffects effects) implements BlockStateModelPart {
        @Override
        public List<BakedQuad> getQuads(@Nullable Direction face) {
            return delegate.getQuads(face);
        }

        @Override
        public boolean useAmbientOcclusion() {
            return effects.ambientOcclusion();
        }

        //? if <= 1.21.11 {
        /*@Override
        public TextureAtlasSprite particleIcon() {
            return delegate.particleIcon();
        }
        *///?} else {
        @Override
        public Material.Baked particleMaterial() {
            return delegate.particleMaterial();
        }

        @Override
        public int materialFlags() {
            return delegate.materialFlags();
        }
        //?}
    }
}
//?}
