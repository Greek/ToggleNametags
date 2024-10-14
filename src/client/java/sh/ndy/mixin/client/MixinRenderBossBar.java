package sh.ndy.mixin.client;

import net.minecraft.client.gui.hud.BossBarHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sh.ndy.config.Config;

@Mixin(BossBarHud.class)
public class MixinRenderBossBar {
    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    private void render(CallbackInfo ci) {
        if (Config.getOptions().getRenderBossbar())
            return;
        ci.cancel();
    }
}
