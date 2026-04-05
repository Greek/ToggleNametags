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

    Config.getOptions().setRenderSelfNametag(!Config.getOptions().getRenderSelfNametag());
    Config.saveConfig();

    String msg;
    if (Config.getOptions().getRenderSelfNametag()) {
      msg = "Your nametag is visible!";
    } else {
      msg = "Your nametag is hidden!";
    }

    client.player.displayClientMessage(Component.literal(msg).withStyle(style -> style.withColor(ChatFormatting.DARK_GRAY)), false);
  }

  public void handleMixin(T livingEntity, double d, CallbackInfoReturnable<Boolean> cir) {
    if (Config.getOptions().getRenderSelfNametag() && livingEntity == Minecraft.getInstance().getCameraEntity()) {
      cir.setReturnValue(true);
    }
  }
}
