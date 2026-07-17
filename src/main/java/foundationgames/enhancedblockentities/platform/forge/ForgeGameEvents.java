package foundationgames.enhancedblockentities.platform.forge;

//? if forge {

/*import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.util.WorldUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
//? if <= 1.21.5 {
/^import net.minecraftforge.eventbus.api.SubscribeEvent;
^///?} else {
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
//?}
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ForgeGameEvents {

	private ForgeGameEvents() {
	}

	@SubscribeEvent
	public static void onLevelTickPost(TickEvent.LevelTickEvent.Post event) {
		//? if <= 1.21.6 {
		/^var level = event.level;
		^///?} else {
		var level = event.level();
		//?}

		if (level instanceof ClientLevel clientLevel) {
			WorldUtil.EVENT_LISTENER.onEndTick(clientLevel);
		}
	}
}
*///?}
