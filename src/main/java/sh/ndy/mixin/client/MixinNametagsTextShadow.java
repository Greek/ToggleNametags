// TODO(Ravel): Failed to fully resolve file: class com.intellij.psi.impl.source.tree.java.PsiReferenceExpressionImpl cannot be cast to class com.intellij.psi.PsiLiteralExpression (com.intellij.psi.impl.source.tree.java.PsiReferenceExpressionImpl and com.intellij.psi.PsiLiteralExpression are in unnamed module of loader com.intellij.ide.plugins.cl.PluginClassLoader @173c341f)
package sh.ndy.mixin.client;

import net.minecraft.client.renderer.feature.NameTagFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import sh.ndy.features.listeners.NametagsTextShadowListener;

@Mixin(NameTagFeatureRenderer.class)
public class MixinNametagsTextShadow {
  private final String TEXT_RENDERER_TARGET =
      "Lnet/minecraft/client/gui/Font;drawInBatch(Lnet/minecraft/network/chat/Component;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)V";
  private static final NametagsTextShadowListener listener = new NametagsTextShadowListener();

  @ModifyArg(method = "render", at = @At(value = "INVOKE", target = TEXT_RENDERER_TARGET))
  private boolean render(boolean original) {
    return listener.handleMixin();
  }
}
