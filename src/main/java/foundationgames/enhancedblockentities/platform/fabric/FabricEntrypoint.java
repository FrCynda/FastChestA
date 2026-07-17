package foundationgames.enhancedblockentities.platform.fabric;

//? fabric {

import foundationgames.enhancedblockentities.Main;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ModInitializer;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		Main.onInitialize();
	}
}
//?}
