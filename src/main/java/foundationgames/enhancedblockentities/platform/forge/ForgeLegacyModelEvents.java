package foundationgames.enhancedblockentities.platform.forge;

//? if forge && <= 1.21.4 {

/*import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.client.model.DynamicModelProvidingPlugin;
import foundationgames.enhancedblockentities.client.model.ModelIdentifiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ForgeLegacyModelEvents {

	private ForgeLegacyModelEvents() {
	}

	@SubscribeEvent
	public static void registerGeometryLoaders(ModelEvent.RegisterGeometryLoaders event) {
		DynamicModelProvidingPlugin.registerGeometryLoader(event);
	}

	@SubscribeEvent
	public static void registerModelStateDefinitions(ModelEvent.RegisterModelStateDefinitions event) {
		ModelIdentifiers.registerModelStateDefinitions(event);
	}

	@SubscribeEvent
	public static void modifyBakingResult(ModelEvent.ModifyBakingResult event) {
		ModelIdentifiers.captureExtraModels(event);
	}
}
*///?}
