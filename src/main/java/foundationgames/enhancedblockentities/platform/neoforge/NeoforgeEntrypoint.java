package foundationgames.enhancedblockentities.platform.neoforge;

//? if neoforge {

/*import foundationgames.enhancedblockentities.EnhancedBlockEntities;
import foundationgames.enhancedblockentities.config.gui.screen.EBEConfigScreen;
import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.util.WorldUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@Mod(value = Main.MOD_ID, dist = Dist.CLIENT)
public class NeoforgeEntrypoint {

	public NeoforgeEntrypoint(IEventBus modBus, ModContainer container) {
		Main.onInitialize();

		container.registerExtensionPoint(IConfigScreenFactory.class,
				(mod, parent) -> new EBEConfigScreen(parent));

		NeoForge.EVENT_BUS.addListener(this::onLevelTickPost);

		EnhancedBlockEntities.initClient();
	}

	private void onLevelTickPost(LevelTickEvent.Post event) {
		if (event.getLevel() instanceof ClientLevel level) {
			WorldUtil.EVENT_LISTENER.onEndTick(level);
		}
	}
}
*///?}
