package sh.ndy.mixin.client;

import net.minecraft.client.render.command.LabelCommandRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import sh.ndy.features.listeners.NametagsTextShadowListener;

@Mixin(LabelCommandRenderer.class)
public class MixinNametagsTextShadow {
    private static final NametagsTextShadowListener listener = new NametagsTextShadowListener();

    @ModifyArg(method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/Text;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)V"
            )
    )
    private boolean render(boolean original) {
        return listener.handleMixin();
    }
}
