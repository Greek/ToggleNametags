package sh.ndy.mixin.client;

//? if < 26.2 {
 /*import com.llamalad7.mixinextras.injector.ModifyExpressionValue; 
 import net.minecraft.client.renderer.feature.NameTagFeatureRenderer;
*///?}
import net.minecraft.client.renderer.SubmitNodeCollection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import sh.ndy.config.Config;

//? if < 26.2 {
 /*@Mixin(NameTagFeatureRenderer.Storage.class) 
*///?} else {
@Mixin(SubmitNodeCollection.class)
//?}
public class MixinNametagsBackgroundOpacity {
  @Unique private static final String TARGET = "Lnet/minecraft/client/renderer/state/OptionsRenderState;getBackgroundOpacity(F)F";

  //? if < 26.2 {
   /*@ModifyExpressionValue(
      method = "add",
      at = @At(
          value = "INVOKE",
          target = TARGET
      )
   )
  *///?} else {
  @ModifyArg(method = "submitNameTag", at = @At(value = "INVOKE", target = TARGET))
  //?}
  private static float disableNametag(float originalColor) {
    return (float) Config.getOptions().getNametagOpacity();
  }
}