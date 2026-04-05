package sh.ndy.features.listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sh.ndy.config.Config;

public class BossbarToggleListener implements IBaseBindingListener {
  public void handleBinding(Minecraft client, KeyMapping binding) {
    if (client.player == null) return;

    Config.getOptions().setRenderBossbar(!Config.getOptions().getRenderBossbar());
    Config.saveConfig();

    String msg;
    if (Config.getOptions().getRenderBossbar()) {
      msg = "The bossbar is visible!";
    } else {
      msg = "The bossbar is hidden!";
    }

    client.player.displayClientMessage(Component.literal(msg).withStyle(style -> style.withColor(ChatFormatting.DARK_GRAY)), false);
  }

  public void handleMixin(CallbackInfo ci) {
    if (Config.getOptions().getRenderBossbar()) {
      return;
    }

    ci.cancel();
  }
}
