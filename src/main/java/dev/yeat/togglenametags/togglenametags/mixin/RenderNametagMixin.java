package dev.yeat.togglenametags.togglenametags.mixin;

import net.minecraft.client.render.entity.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.yeat.togglenametags.togglenametags.client.TogglenametagsClient.shouldRender;

@Mixin(EntityRenderer.class)
public class RenderNametagMixin {
    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private void doNotRender(CallbackInfo ci) {
        if (shouldRender) {
            return;
        } else {
            ci.cancel();
        }

    }
}
