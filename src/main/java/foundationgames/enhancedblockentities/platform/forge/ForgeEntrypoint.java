package foundationgames.enhancedblockentities.platform.forge;

//? if forge {

/*import foundationgames.enhancedblockentities.EnhancedBlockEntities;
import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.config.gui.screen.EBEConfigScreen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Main.MOD_ID)
public class ForgeEntrypoint {

	public ForgeEntrypoint() {
		Main.onInitialize();

		if (FMLEnvironment.dist.isClient()) {
			ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
					() -> new ConfigScreenHandler.ConfigScreenFactory((mc, parent) -> new EBEConfigScreen(parent)));

			EnhancedBlockEntities.initClient();
		}
	}
}
*///?}
