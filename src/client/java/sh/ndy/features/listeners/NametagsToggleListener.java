package sh.ndy.features.listeners;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sh.ndy.config.Config;

public class NametagsToggleListener<T extends Entity, S extends EntityRenderState> implements IBaseBindingListener {
  public void handleBinding(MinecraftClient client, KeyBinding binding) {
	  boolean isSelfNametagShown = Config.getOptions().getRenderSelfNametag();
	  if (client.player == null) return;

	  Config.getOptions().setRenderNametags(!Config.getOptions().getRenderNametags());
	  Config.saveConfig();

	  String msg;
	  if (Config.getOptions().getRenderNametags()) {
		  msg = "Nametags are now shown!";
	  } else {
		  msg = "Nametags are now hidden!";
		  if (isSelfNametagShown) {
			  msg = "Nametags are now hidden (except yours)!";
		  }
	  }

	  client.player.sendMessage(Text.literal(msg).styled(style ->
			  style.withColor(Formatting.DARK_GRAY)), false);
  }

  public void handleMixin(CallbackInfo ci) {
	if (Config.getOptions().getRenderNametags()) {
	  return;
	}

	ci.cancel();
  }
}
