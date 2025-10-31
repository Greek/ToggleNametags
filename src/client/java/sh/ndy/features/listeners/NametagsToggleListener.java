package sh.ndy.features.listeners;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sh.ndy.config.Config;
import sh.ndy.config.Options;

public class NametagsToggleListener implements IBaseBindingListener {
  private Options options = Config.getOptions();

  public void handleBinding(MinecraftClient client, KeyBinding binding) {
	if (client.player == null) return;

	options.setRenderNametags(!options.getRenderNametags());
	Config.saveConfig();

	if (Config.getOptions().getRenderNametags()) {
	  client.player.sendMessage(Text.literal("Nametags are now shown!").styled(style ->
			  style.withColor(Formatting.DARK_GRAY)), false);
	} else {
	  client.player.sendMessage(Text.literal("Nametags are now hidden!").styled(style ->
			  style.withColor(Formatting.DARK_GRAY)), false);
	}
  }

  public void handleMixin(CallbackInfo ci) {
	if (options.getRenderNametags()) {
	  return;
	}

	ci.cancel();
  }
}
