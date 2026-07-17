package foundationgames.enhancedblockentities.client.model;

//? if fabric && <= 1.21.4 {
/*import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DynamicModelProvidingPlugin implements ModelLoadingPlugin, ModelModifier.OnLoad {
    private final Supplier<DynamicUnbakedModel> model;
    private final Identifier id;

    public DynamicModelProvidingPlugin(Identifier id, Supplier<DynamicUnbakedModel> model) {
        this.model = model;
        this.id = id;
    }

    public static void register(Identifier id, Supplier<DynamicUnbakedModel> model) {
        ModelLoadingPlugin.register(new DynamicModelProvidingPlugin(id, model));
    }

    @Override
    public void initialize(ModelLoadingPlugin.Context ctx) {
        ctx.modifyModelOnLoad().register(this);
    }

    @Override
    public @Nullable UnbakedModel modifyModelOnLoad(@Nullable UnbakedModel model, ModelModifier.OnLoad.Context context) {
        if (context.id().equals(this.id)) return this.model.get();
        return model;
    }
}
*///?}
//? if neoforge && <= 1.21.4 {
/*import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import foundationgames.enhancedblockentities.client.resource.EBEPack;
import foundationgames.enhancedblockentities.util.EBEUtil;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.model.UnbakedModelLoader;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class DynamicModelProvidingPlugin {
    private static final Map<Identifier, Supplier<DynamicUnbakedModel>> PROVIDERS = new HashMap<>();

    private DynamicModelProvidingPlugin() {
    }

    public static void register(Identifier id, Supplier<DynamicUnbakedModel> model) {
        PROVIDERS.put(id, model);
    }

    public static @Nullable DynamicUnbakedModel get(Identifier id) {
        var provider = PROVIDERS.get(id);
        return provider != null ? provider.get() : null;
    }

    public static void registerModelLoader(ModelEvent.RegisterLoaders event) {
        UnbakedModelLoader<DynamicUnbakedModel> loader = DynamicModelProvidingPlugin::read;
        event.register(EBEUtil.id("dynamic"), loader);
    }

    public static void emitModels(EBEPack pack) {
        for (var id : PROVIDERS.keySet()) {
            pack.addPlainTextResource(
                    Identifier.fromNamespaceAndPath(id.getNamespace(), "models/" + id.getPath() + ".json"),
                    "{\"loader\":\"" + EBEUtil.id("dynamic") + "\",\"key\":\"" + id + "\"}");
        }
    }

    private static DynamicUnbakedModel read(JsonObject json, JsonDeserializationContext context) {
        var key = Identifier.parse(json.get("key").getAsString());
        var model = get(key);

        if (model == null) {
            throw new JsonParseException("No Enhanced Block Entities dynamic model registered for " + key);
        }

        return model;
    }
}
*///?}
//? if forge && <= 1.21.4 {
/*import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.client.resource.EBEPack;
import foundationgames.enhancedblockentities.util.EBEUtil;
import net.minecraft.resources.Identifier;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class DynamicModelProvidingPlugin {
    private static final Map<Identifier, Supplier<DynamicUnbakedModel>> PROVIDERS = new HashMap<>();

    private DynamicModelProvidingPlugin() {
    }

    public static void register(Identifier id, Supplier<DynamicUnbakedModel> model) {
        PROVIDERS.put(id, model);
    }

    public static @Nullable DynamicUnbakedModel get(Identifier id) {
        var provider = PROVIDERS.get(id);
        return provider != null ? provider.get() : null;
    }

    public static void registerGeometryLoader(ModelEvent.RegisterGeometryLoaders event) {
        IGeometryLoader<DynamicUnbakedModel> loader = DynamicModelProvidingPlugin::readGeometry;
        event.register("dynamic", loader);
    }

    private static String loaderId() {
        return Main.MOD_ID + ":dynamic";
    }

    public static void emitModels(EBEPack pack) {
        for (var id : PROVIDERS.keySet()) {
            pack.addPlainTextResource(
                    Identifier.fromNamespaceAndPath(id.getNamespace(), "models/" + id.getPath() + ".json"),
                    "{\"elements\":[],\"loader\":\"" + loaderId() + "\",\"key\":\"" + id + "\"}");
        }
    }

    private static DynamicUnbakedModel readGeometry(JsonObject json, JsonDeserializationContext context) {
        var key = Identifier.parse(json.get("key").getAsString());
        var model = get(key);

        if (model == null) {
            throw new JsonParseException("No Enhanced Block Entities dynamic model registered for " + key);
        }

        return model;
    }
}
*///?}
//? if >= 1.21.5 {
//? if fabric {
import net.fabricmc.fabric.api.client.model.loading.v1.CustomUnbakedBlockStateModel;
//?} else if neoforge {
/*import net.neoforged.neoforge.client.event.RegisterBlockStateModels;
*///?} else if forge {
/*import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.client.resource.EBEPack;
import foundationgames.enhancedblockentities.util.EBEUtil;
import net.minecraft.util.RandomSource;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
*///?}
//? if forge && <= 1.21.11 {
/*import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.block.model.BlockStateModelPart;
import net.minecraft.client.renderer.block.model.SimpleModelWrapper;
import net.minecraft.client.resources.model.QuadCollection;
*///?} else if forge {
/*import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.resources.model.SimpleModelWrapper;
import net.minecraft.client.resources.model.geometry.QuadCollection;
*///?}
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public final class DynamicModelProvidingPlugin {
    private static final Map<Identifier, Supplier<DynamicUnbakedModel>> PROVIDERS = new HashMap<>();

    //? if fabric {
    static {
        CustomUnbakedBlockStateModel.register(DynamicUnbakedModel.Unbaked.TYPE_ID, DynamicUnbakedModel.Unbaked.CODEC);
    }
    //?} else if neoforge {
    /*public static void registerModelType(RegisterBlockStateModels event) {
        event.registerModel(DynamicUnbakedModel.Unbaked.TYPE_ID, DynamicUnbakedModel.Unbaked.CODEC);
    }
    *///?} else if forge {
    /*private static final Map<QuadCollection, BlockStateModel> BAKED = new ConcurrentHashMap<>();

    public static void registerGeometryLoader(ModelEvent.RegisterGeometryLoaders event) {
        IGeometryLoader loader = DynamicModelProvidingPlugin::readGeometry;
        //? if <= 1.21.6 {
        /^event.register("dynamic", loader);
        ^///?} else {
        event.register(EBEUtil.id("dynamic"), loader);
        //?}
    }

    private static String loaderId() {
        //? if <= 1.21.6 {
        /^return Main.MOD_ID + ":dynamic";
        ^///?} else {
        return EBEUtil.id("dynamic").toString();
        //?}
    }

    public static void emitModels(EBEPack pack) {
        for (var id : PROVIDERS.keySet()) {
            pack.addPlainTextResource(
                    Identifier.fromNamespaceAndPath(id.getNamespace(), "models/" + id.getPath() + ".json"),
                    "{\"loader\":\"" + loaderId() + "\",\"key\":\"" + id + "\"}");
        }
    }

    public static void putBaked(QuadCollection quads, BlockStateModel model) {
        BAKED.put(quads, model);
    }

    public static void applyDynamicModels(ModelEvent.ModifyBakingResult event) {
        if (BAKED.isEmpty()) return;

        var random = RandomSource.create();
        var parts = new ArrayList<BlockStateModelPart>();

        for (var entry : event.getResults().blockStateModels().entrySet()) {
            parts.clear();
            entry.getValue().collectParts(random, parts);

            if (parts.size() != 1 || !(parts.get(0) instanceof SimpleModelWrapper wrapper)) continue;

            var dynamic = BAKED.get(wrapper.quads());
            if (dynamic != null) entry.setValue(dynamic);
        }

        BAKED.clear();
    }

    private static DynamicUnbakedModel.Geometry readGeometry(JsonObject json, JsonDeserializationContext context) {
        var key = Identifier.parse(json.get("key").getAsString());

        if (get(key) == null) {
            throw new JsonParseException("No Enhanced Block Entities dynamic model registered for " + key);
        }

        return new DynamicUnbakedModel.Geometry(key);
    }
    *///?}

    private DynamicModelProvidingPlugin() {
    }

    public static void register(Identifier id, Supplier<DynamicUnbakedModel> model) {
        PROVIDERS.put(id, model);
    }

    public static @Nullable DynamicUnbakedModel get(Identifier id) {
        var provider = PROVIDERS.get(id);
        return provider != null ? provider.get() : null;
    }
}
//?}
