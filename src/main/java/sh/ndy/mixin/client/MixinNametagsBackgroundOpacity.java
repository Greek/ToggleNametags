package sh.ndy.mixin.client;

//? if < 26.2 {
 /*import com.llamalad7.mixinextras.injector.ModifyExpressionValue; 
*///?}
//? if < 26.3 {
 /*import net.minecraft.client.renderer.feature.NameTagFeatureRenderer; 
*///?}
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import sh.ndy.config.Config;

//? if < 26.2 {
 /*@Mixin(NameTagFeatureRenderer.Storage.class) 
*///?} else if < 26.3 {
 /*@Mixin(NameTagFeatureRenderer.class) 
*///?} else {
@Mixin(SubmitNodeCollection.class)
//?}
public class MixinNametagsBackgroundOpacity {
  //? if < 26.2 {
   /*@Unique private static final String TARGET = "Lnet/minecraft/client/renderer/state/OptionsRenderState;getBackgroundOpacity(F)F"; 
  *///?} else if < 26.3 {
   /*@Unique private static final String TARGET = "Lnet/minecraft/client/gui/Font;prepareText(Lnet/minecraft/util/FormattedCharSequence;FFIZZI)Lnet/minecraft/client/gui/Font$PreparedText;";
  
  *///?} else {
    @Unique private static final String TARGET = "Lnet/minecraft/client/renderer/state/OptionsRenderState;getBackgroundOpacity(F)F";
  //?}

  //? if < 26.2 {
   /*@ModifyExpressionValue(
      method = "add",
      at = @At(
          value = "INVOKE",
          target = TARGET
      )
   )
   private float changeOpacity(float original) {
    return (float) Config.getOptions().getNametagOpacity();
  }
  *///?} else if < 26.3 {
   /*@ModifyArg(
      method = "renderText",
      at = @At(
          value = "INVOKE",
          target = TARGET
      ),
      index = 6
  )
  *///?} else {
  @ModifyArg(method = "submitNameTag", at = @At(value = "INVOKE", target = TARGET))
  //?}
  private static float disableNametag(float originalColor) {
    if (!Config.getOptions().getNametagBackgroundEnabled()) {
      return 0.0F;
    }
    return originalColor;
  }
}