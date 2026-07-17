package foundationgames.enhancedblockentities.platform.neoforge;

//? if neoforge && <= 1.21.4 {

/*import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.client.model.DynamicModelProvidingPlugin;
import foundationgames.enhancedblockentities.client.model.ModelIdentifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT)
public final class NeoforgeLegacyModelEvents {

	private NeoforgeLegacyModelEvents() {
	}

	@SubscribeEvent
	public static void registerModelLoader(ModelEvent.RegisterLoaders event) {
		DynamicModelProvidingPlugin.registerModelLoader(event);
	}

	@SubscribeEvent
	public static void registerAdditionalModels(ModelEvent.RegisterAdditional event) {
		ModelIdentifiers.registerAdditionalModels(event);
	}

	@SubscribeEvent
	public static void captureBakedModels(ModelEvent.BakingCompleted event) {
		ModelIdentifiers.captureBakedModels(event);
	}
}
*///?}
