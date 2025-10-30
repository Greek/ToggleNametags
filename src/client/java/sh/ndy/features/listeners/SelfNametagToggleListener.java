package sh.ndy.features.listeners;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import sh.ndy.config.Config;

public class SelfNametagToggleListener implements IBaseBindingListener {
  public void handleBinding(MinecraftClient client, KeyBinding binding) {
	if (client.player == null) return;

	Config.getOptions().setRenderSelfNametag(!Config.getOptions().getRenderSelfNametag());
	Config.saveConfig();

	if (Config.getOptions().getRenderSelfNametag()) {
	  client.player.sendMessage(Text.literal("Your nametag is visible!").styled(style ->
			  style.withColor(Formatting.DARK_GRAY)), false);
	} else {
	  client.player.sendMessage(Text.literal("Your nametag is hidden!").styled(style ->
			  style.withColor(Formatting.DARK_GRAY)), false);
	}
  }
}
