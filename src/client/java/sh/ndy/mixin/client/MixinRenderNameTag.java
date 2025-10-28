package sh.ndy.mixin.client;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sh.ndy.config.Config;

@Mixin(EntityRenderer.class)
public class MixinRenderNameTag<T extends Entity, S extends EntityRenderState> {
    @Inject(at = @At("HEAD"), method = "renderLabelIfPresent", cancellable = true)
    private void doNotRender(S state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraRenderState, CallbackInfo ci) {
        if (Config.getOptions().getRenderNametags()) {
            return;
        }

        ci.cancel();
    }
}
