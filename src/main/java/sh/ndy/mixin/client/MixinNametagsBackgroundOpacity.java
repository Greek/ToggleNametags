package sh.ndy.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.renderer.feature.NameTagFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import sh.ndy.config.Config;


@Mixin(NameTagFeatureRenderer.Storage.class)
public class MixinNametagsBackgroundOpacity {
  @ModifyExpressionValue(method = "add",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/state/OptionsRenderState;getBackgroundOpacity(F)F"))
  private float submitLabel(float original) {
    return (float) Config.getOptions().getNametagOpacity();
  }
}
