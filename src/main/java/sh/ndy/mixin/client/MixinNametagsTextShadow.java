package sh.ndy.mixin.client;

import net.minecraft.client.renderer.feature.NameTagFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import sh.ndy.features.listeners.NametagsTextShadowListener;

@Mixin(NameTagFeatureRenderer.class)
public class MixinNametagsTextShadow {
  //? if < 26.2 {
  /* @Unique private static final String TARGET =
    "Lnet/minecraft/client/gui/Font;drawInBatch(Lnet/minecraft/network/chat/Component;FFIZLorg/joml/Matrix4fc;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)V";
  *///?} else {
  @Unique private static final String TARGET =
      "Lnet/minecraft/client/gui/Font;prepareText(Lnet/minecraft/util/FormattedCharSequence;FFIZZI)Lnet/minecraft/client/gui/Font$PreparedText;";
  //?}

  private static final NametagsTextShadowListener listener = new NametagsTextShadowListener();

  //? if < 26.2 {
  /*
  @ModifyArg(method = "renderTranslucent", at = @At(value = "INVOKE", target = TARGET))
  private boolean render(boolean original) {
    return listener.handleMixin();
  }
  */
  //?} else {
  @ModifyArg(method = "prepareText",
      at = @At(value = "INVOKE", target = TARGET),
      index = 4
  )
  private static boolean render(boolean drawShadow) {
    return listener.handleMixin();
  }
  //?}

}
