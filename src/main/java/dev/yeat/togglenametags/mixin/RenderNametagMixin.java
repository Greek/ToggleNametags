package dev.yeat.togglenametags.mixin;

import dev.yeat.togglenametags.client.TogglenametagsClient;
import net.minecraft.client.render.entity.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class RenderNametagMixin {
    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private void doNotRender(CallbackInfo ci) {
        if (TogglenametagsClient.shouldRender) {
            return;
        } else {
            ci.cancel();
        }

    }
}
