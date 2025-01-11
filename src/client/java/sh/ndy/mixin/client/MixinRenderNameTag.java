package sh.ndy.mixin.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sh.ndy.config.Config;

@Mixin(EntityRenderer.class)
public class MixinRenderNameTag<T extends Entity, S extends EntityRenderState> {
    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private void doNotRender(S state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (Config.getOptions().getRenderNametags()) {
            return;
        }

        ci.cancel();
    }
}
