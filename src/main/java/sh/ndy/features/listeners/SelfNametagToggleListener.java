package sh.ndy.features.listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sh.ndy.config.Config;

public class SelfNametagToggleListener<T extends Entity> implements IBaseBindingListener {
  public void handleBinding(Minecraft client, KeyMapping binding) {
    if (client.player == null) return;

    var currSetting = Config.getOptions().getRenderSelfNametag();
    Config.getOptions().setRenderSelfNametag(!currSetting);
    Config.saveConfig();

    String msg;
    if (Config.getOptions().getRenderSelfNametag()) {
      msg = "Your nametag is visible!";
    } else {
      msg = "Your nametag is hidden!";
    }

    client.player.sendSystemMessage(Component.literal(msg).withStyle(style -> style.withColor(ChatFormatting.DARK_GRAY)));
  }

  public void handleMixin(T livingEntity, double d, CallbackInfoReturnable<Boolean> cir) {
    if (Config.getOptions().getRenderSelfNametag() && livingEntity == Minecraft.getInstance().getCameraEntity()) {
      cir.setReturnValue(true);
    }
  }
}
