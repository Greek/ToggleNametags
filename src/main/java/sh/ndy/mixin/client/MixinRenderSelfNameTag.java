package sh.ndy.mixin.client;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sh.ndy.features.listeners.SelfNametagToggleListener;

@Mixin(LivingEntityRenderer.class)
public class MixinRenderSelfNameTag<T extends LivingEntity> {
  @Unique
  private static final SelfNametagToggleListener<Entity> listener = new SelfNametagToggleListener<>();

  @Inject(at = @At("HEAD"), method = "hasLabel(Lnet/minecraft/entity/LivingEntity;D)Z", cancellable = true)
  private void viewOwnLabel(T livingEntity, double d, CallbackInfoReturnable<Boolean> cir) {
    listener.handleMixin(livingEntity, d, cir);
  }
}
