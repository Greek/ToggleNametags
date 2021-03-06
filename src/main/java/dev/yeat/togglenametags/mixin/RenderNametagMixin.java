package dev.yeat.togglenametags.mixin;

import dev.yeat.togglenametags.client.TogglenametagsClient;
import dev.yeat.togglenametags.config.Reader;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class RenderNametagMixin<T extends Entity> {
    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private void doNotRender(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (TogglenametagsClient.shouldRender) {
            return;
        } else {
            if (Reader.renderOtherEntities) {
                if (entity instanceof PlayerEntity) {
                    ci.cancel();
                }

                // Armor stands are used as nametags on some servers
                // we'll pretend they're players too.
                if (entity instanceof ArmorStandEntity) {
                    ci.cancel();
                }

                } else {
                    ci.cancel();
                }
            }
    }
}
