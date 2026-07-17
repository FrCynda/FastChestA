package foundationgames.enhancedblockentities.platform.neoforge;

//? if neoforge && >= 1.21.6 {

/*import foundationgames.enhancedblockentities.Main;
import foundationgames.enhancedblockentities.client.render.gui.SignGuiElementRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterPictureInPictureRenderersEvent;

@EventBusSubscriber(modid = Main.MOD_ID, value = Dist.CLIENT)
public final class NeoforgeClientEvents {

	private NeoforgeClientEvents() {
	}

	@SubscribeEvent
	public static void registerPictureInPictureRenderers(RegisterPictureInPictureRenderersEvent event) {
		event.register(SignGuiElementRenderer.State.class, SignGuiElementRenderer::new);
	}
}
*///?}
