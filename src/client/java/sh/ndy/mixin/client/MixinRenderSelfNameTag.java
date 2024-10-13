package sh.ndy.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sh.ndy.config_old.Reader;

@Mixin(LivingEntityRenderer.class)
public class MixinRenderSelfNameTag<T extends LivingEntity> {
    @Inject(
            at = @At("HEAD"),
            method = "hasLabel(Lnet/minecraft/entity/LivingEntity;)Z",
            cancellable = true
    )
    private void viewOwnLabel(T livingEntity, CallbackInfoReturnable<Boolean> cir) {
        if (Reader.renderOwnNametag && livingEntity == MinecraftClient.getInstance().cameraEntity) {
            cir.setReturnValue(true);
        };
    }
}
