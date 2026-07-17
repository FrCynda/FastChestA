package foundationgames.enhancedblockentities.platform.forge;

//? if forge && >= 1.21.6 {

/*import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.client.render.gui.SignGuiElementRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterPictureInPictureRendererEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class ForgeClientEvents {

	private ForgeClientEvents() {
	}

	@SubscribeEvent
	public static void registerPictureInPictureRenderers(RegisterPictureInPictureRendererEvent event) {
		//? if <= 26.1 {
		/^event.register(new SignGuiElementRenderer(event.getBufferSource()));
		^///?} else {
		event.register(new SignGuiElementRenderer());
		//?}
	}
}
*///?}
