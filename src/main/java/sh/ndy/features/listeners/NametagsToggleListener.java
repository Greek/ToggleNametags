package sh.ndy.features.listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sh.ndy.config.Config;

public class NametagsToggleListener implements IBaseBindingListener {
  public void handleBinding(Minecraft client, KeyMapping binding) {
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

    client.player.sendSystemMessage(Component.literal(msg).withStyle(style -> style.withColor(ChatFormatting.DARK_GRAY)));
  }

  public void handleMixin(CallbackInfo ci) {
    if (Config.getOptions().getRenderNametags()) {
      return;
    }

    ci.cancel();
  }
}
