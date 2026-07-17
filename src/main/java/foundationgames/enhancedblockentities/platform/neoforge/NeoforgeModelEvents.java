package foundationgames.enhancedblockentities.platform.neoforge;

//? if neoforge && >= 1.21.5 {

/*import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.client.model.DynamicModelProvidingPlugin;
import foundationgames.enhancedblockentities.client.model.ModelIdentifiers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterBlockStateModels;

@EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT)
public final class NeoforgeModelEvents {

	private NeoforgeModelEvents() {
	}

	@SubscribeEvent
	public static void registerModelType(RegisterBlockStateModels event) {
		DynamicModelProvidingPlugin.registerModelType(event);
	}

	@SubscribeEvent
	public static void registerStandaloneModels(ModelEvent.RegisterStandalone event) {
		ModelIdentifiers.registerStandaloneModels(event);
	}
}
*///?}
