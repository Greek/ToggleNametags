package sh.ndy.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sh.ndy.config.Config;

@Mixin(LivingEntityRenderer.class)
public class MixinRenderSelfNameTag<T extends LivingEntity> {
    @Inject(at = @At("HEAD"), method = "hasLabel(Lnet/minecraft/entity/LivingEntity;D)Z", cancellable = true)
    private void viewOwnLabel(T livingEntity, double d, CallbackInfoReturnable<Boolean> cir) {
        if (Config.getOptions().getRenderSelfNametag() && livingEntity == MinecraftClient.getInstance().getCameraEntity()) {
            cir.setReturnValue(true);
        }
        ;
    }
}
