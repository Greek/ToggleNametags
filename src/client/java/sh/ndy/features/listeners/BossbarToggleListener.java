package sh.ndy.features.listeners;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sh.ndy.config.Config;

public class BossbarToggleListener implements IBaseBindingListener {
  public void handleBinding(MinecraftClient client, KeyBinding binding) {
	if (client.player == null) return;

	Config.getOptions().setRenderBossbar(!Config.getOptions().getRenderBossbar());
	Config.saveConfig();

	String msg;
	if (Config.getOptions().getRenderBossbar()) {
	  msg = "The bossbar is visible!";
	} else {
	  msg = "The bossbar is hidden!";
	}

	client.player.sendMessage(Text.literal(msg).styled(style ->
			style.withColor(Formatting.DARK_GRAY)), false);
  }

  public void handleMixin(CallbackInfo ci) {
	if (Config.getOptions().getRenderBossbar()) {
	  return;
	}

	ci.cancel();
  }
}
