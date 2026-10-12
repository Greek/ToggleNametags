package sh.ndy.mixin.client;

//? if < 26.3 {
 /*import net.minecraft.client.renderer.feature.NameTagFeatureRenderer; 
*///?}
import net.minecraft.client.renderer.SubmitNodeCollection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import sh.ndy.features.listeners.NametagsTextShadowListener;

//? if < 26.3 {
   /*@Mixin(NameTagFeatureRenderer.class) 
*///?} else {
@Mixin(SubmitNodeCollection.class)
//?}
public class MixinNametagsTextShadow {
  //? if < 26.2 {
   /*@Unique private static final String ARG_TARGET =
    "Lnet/minecraft/client/gui/Font;drawInBatch(Lnet/minecraft/network/chat/Component;FFIZLorg/joml/Matrix4fc;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)V";
  *///?} else if < 26.3 {
   /*@Unique private static final String ARG_TARGET =
      "Lnet/minecraft/client/gui/Font;prepareText(Lnet/minecraft/util/FormattedCharSequence;FFIZZI)Lnet/minecraft/client/gui/Font$PreparedText;"; 
  *///?} else {
  @Unique private static final String ARG_TARGET =
      "Lnet/minecraft/client/renderer/feature/TextFeatureRenderer$Content$Text;<init>(FFLnet/minecraft/util/FormattedCharSequence;ZIII)V";
  //?}

  private static final NametagsTextShadowListener listener = new NametagsTextShadowListener();

  //? if < 26.2 {
  
  /*@ModifyArg(method = "renderTranslucent", at = @At(value = "INVOKE", target = ARG_TARGET))
  private boolean render(boolean original) {
    return listener.handleMixin();
  }
  
  *///?} else {
  @ModifyArg(method = "nameTag",
      at = @At(value = "INVOKE", target = ARG_TARGET),
      index = 3
  )
  private static boolean render(boolean drawShadow) {
    return listener.handleMixin();
  }
  //?}

}
