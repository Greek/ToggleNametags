package sh.ndy.features.listeners;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sh.ndy.config.Config;

public class SelfNametagToggleListener<T extends Entity> implements IBaseBindingListener {
  public void handleBinding(MinecraftClient client, KeyBinding binding) {
	if (client.player == null) return;

	Config.getOptions().setRenderSelfNametag(!Config.getOptions().getRenderSelfNametag());
	Config.saveConfig();

	String msg;
	if (Config.getOptions().getRenderSelfNametag()) {
	  msg = "Your nametag is visible!";
	} else {
	  msg = "Your nametag is hidden!";
	}

	client.player.sendMessage(Text.literal(msg).styled(style ->
			style.withColor(Formatting.DARK_GRAY)), false);
  }

  public void handleMixin(T livingEntity, double d, CallbackInfoReturnable<Boolean> cir) {
	if (Config.getOptions().getRenderSelfNametag() && livingEntity == MinecraftClient.getInstance().getCameraEntity()) {
	  cir.setReturnValue(true);
	}
  }
}
