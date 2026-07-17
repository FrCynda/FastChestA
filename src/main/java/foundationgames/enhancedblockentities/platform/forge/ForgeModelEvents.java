package foundationgames.enhancedblockentities.platform.forge;

//? if forge && >= 1.21.5 {

/*import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.client.model.DynamicModelProvidingPlugin;
import foundationgames.enhancedblockentities.client.model.ModelIdentifiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
//? if <= 1.21.5 {
/^import net.minecraftforge.eventbus.api.SubscribeEvent;
^///?} else {
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
//?}
import net.minecraftforge.fml.common.Mod;

//? if <= 1.21.6 {
/^@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
^///?} else {
@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
//?}
public final class ForgeModelEvents {

	private ForgeModelEvents() {
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
		DynamicModelProvidingPlugin.applyDynamicModels(event);
		ModelIdentifiers.captureExtraModels(event);
	}
}
*///?}
