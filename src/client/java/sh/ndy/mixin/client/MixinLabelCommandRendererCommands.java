package sh.ndy.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.render.command.LabelCommandRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import sh.ndy.config.Config;


@Mixin(LabelCommandRenderer.Commands.class)
public class MixinLabelCommandRendererCommands {
    @ModifyExpressionValue(method = "add", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/option/GameOptions;getTextBackgroundOpacity(F)F"
    ))
    private float submitLabel(float original) {
        return (float) Config.getOptions().getNametagOpacity();
    }


}
