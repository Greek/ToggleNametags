package sh.ndy.mixin.client;

import net.minecraft.client.renderer.feature.NameTagFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import sh.ndy.config.Config;


@Mixin(NameTagFeatureRenderer.class)
public class MixinNametagsBackgroundOpacity {
  //? if < 26.2 {
  /* @Unique private static final String TARGET = "Lnet/minecraft/client/renderer/state/OptionsRenderState;getBackgroundOpacity(F)F"; */
  //?} else
  @Unique private static final String TARGET = "Lnet/minecraft/client/gui/Font;prepareText(Lnet/minecraft/util/FormattedCharSequence;FFIZZI)Lnet/minecraft/client/gui/Font$PreparedText;";


  //? if < 26.2 {
  /* @ModifyExpressionValue(
      method = "add",
      at = @At(
          value = "INVOKE",
          target = TARGET
      )
   )
   private float changeOpacity(float original) {
    return (float) Config.getOptions().getNametagOpacity();
  }
  *///? else {
  @ModifyArg(
      method = "prepareText",
      at = @At(
          value = "INVOKE",
          target = TARGET
      ),
      index = 6
  )

  private static int disableNametag(int originalColor) {
    if (!Config.getOptions().getNametagBackgroundEnabled()) {
      return 0;
    }

    return originalColor;
  }
}