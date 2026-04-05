package sh.ndy.mixin.client;

//? if <=1.21.1 {
/*import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
*///?} else {
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
//?}
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sh.ndy.features.listeners.NametagsToggleListener;

@Mixin(EntityRenderer.class)
public class MixinRenderNameTag
    //? if <=1.21.1 {
    /* <T extends Entity>
    *///?} else {
    <T extends Entity, S extends EntityRenderState>
    //?}
{
  private static final NametagsToggleListener listener = new NametagsToggleListener();

  @Inject(at = @At("HEAD"), method = "submitNameTag", cancellable = true)
  //? if <=1.21.1 {
  /* private void doNotRender(Entity entity, Text text, MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                            int light, float tickDelta, CallbackInfo ci) { *///?} else
  private void doNotRender(S state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraRenderState, CallbackInfo ci) {
    listener.handleMixin(ci);
  }
}
