package foundationgames.enhancedblockentities;

import foundationgames.enhancedblockentities.client.model.ModelIdentifiers;
import foundationgames.enhancedblockentities.client.model.item.EBEIsChristmasProperty;
import foundationgames.enhancedblockentities.client.render.SignRenderManager;
import foundationgames.enhancedblockentities.client.resource.template.TemplateLoader;
import foundationgames.enhancedblockentities.config.EBEConfig;
import foundationgames.enhancedblockentities.util.EBEUtil;
import foundationgames.enhancedblockentities.util.ResourceUtil;
import foundationgames.enhancedblockentities.util.WorldUtil;
//? if fabric {
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
//?}
//? if fabric && <= 1.21.6 {
/*import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
*///?}
//? if fabric && >= 1.21.6 && <= 1.21.11 {
/*import net.fabricmc.fabric.api.client.rendering.v1.SpecialGuiElementRegistry;
*///?}
//? if fabric && >= 26.1 {
import net.fabricmc.fabric.api.client.rendering.v1.PictureInPictureRendererRegistry;
//?}
//? if >= 1.21.6 {
import foundationgames.enhancedblockentities.client.render.gui.SignGuiElementRenderer;
//?}
import foundationgames.enhancedblockentities.platform.Platform;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

//? if fabric {
public final class EnhancedBlockEntities implements ClientModInitializer {
//?} else {
/*public final class EnhancedBlockEntities {
*///?}
    public static final String ID = "enhancedblockentities";
    public static final String NAMESPACE = "ebe";
    public static final Logger LOG = LogManager.getLogger("Enhanced Block Entities");
    public static final EBEConfig CONFIG = new EBEConfig();

    public static final TemplateLoader TEMPLATE_LOADER = new TemplateLoader();

    public static final String API_V1 = "ebe_v1";

    //? if fabric {
    @Override
    public void onInitializeClient() {
        initClient();
        registerLoaderEvents();
    }
    //?}

    @SuppressWarnings("unchecked")
    public static void initClient() {
        Platform.forEachApiEntrypoint(API_V1, Consumer.class,
                (modId, init) -> init.accept((Runnable) EnhancedBlockEntities::load));

        ConditionalItemModelProperties.ID_MAPPER.put(EBEUtil.id("ebe_is_christmas"), EBEIsChristmasProperty.CODEC);

        ModelIdentifiers.init();
        EBESetup.setupResourceProviders();

        load();

        //foundationgames.enhancedblockentities.util.AutoTest.start();
    }

    private static void registerLoaderEvents() {
        //? if fabric && <= 1.21.6 {
        /*WorldRenderEvents.END.register(ctx -> SignRenderManager.endFrame());
        *///?}
        //? if fabric && <= 1.21.11 {
        /*ClientTickEvents.END_WORLD_TICK.register(WorldUtil.EVENT_LISTENER::onEndTick);
        *///?}
        //? if fabric && >= 26.1 {
        ClientTickEvents.END_LEVEL_TICK.register(WorldUtil.EVENT_LISTENER::onEndTick);
        //?}
        //? if fabric && >= 1.21.6 && <= 1.21.11 {
        /*SpecialGuiElementRegistry.register(ctx -> new SignGuiElementRenderer(ctx.vertexConsumers()));
        *///?}
        //? if fabric && >= 26.1 && <= 26.1 {
        /*PictureInPictureRendererRegistry.register(ctx -> new SignGuiElementRenderer(ctx.bufferSource()));
        *///?}
        //? if fabric && >= 26.2 {
        PictureInPictureRendererRegistry.register(ctx -> new SignGuiElementRenderer());
        //?}
    }

    public static void reload(ReloadType type) {
        load();
        if (type == ReloadType.WORLD) {
            //? if <= 26.1 {
            /*Minecraft.getInstance().levelRenderer.allChanged();
            *///?} else {
            Minecraft.getInstance().levelExtractor.allChanged();
            //?}
        } else if (type == ReloadType.RESOURCES) {
            Minecraft.getInstance().reloadResourcePacks();
        }
    }

    public static void load() {
        CONFIG.load();

        EnhancedBlockEntityRegistry.clear();
        ResourceUtil.resetBasePack();
        ResourceUtil.resetTopLevelPack();

        if (CONFIG.renderEnhancedChests) {
            EBESetup.setupChests();
            EBESetup.setupRRPChests();
        }

        if (CONFIG.renderEnhancedSigns) {
            EBESetup.setupSigns();
            EBESetup.setupRRPSigns();
        }

        if (CONFIG.renderEnhancedBells) {
            EBESetup.setupBells();
            EBESetup.setupRRPBells();
        }

        if (CONFIG.renderEnhancedBeds) {
            EBESetup.setupBeds();
            EBESetup.setupRRPBeds();
        }

        if (CONFIG.renderEnhancedShulkerBoxes) {
            EBESetup.setupShulkerBoxes();
            EBESetup.setupRRPShulkerBoxes();
        }

        if (CONFIG.renderEnhancedDecoratedPots) {
            EBESetup.setupDecoratedPots();
            EBESetup.setupRRPDecoratedPots();
        }
    }
}
