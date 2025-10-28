package sh.ndy.bindings.listeners;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import sh.ndy.config.Config;

public class BossbarToggleListener implements IBaseBindingListener {
    public static void handleEvent(MinecraftClient client, KeyBinding binding) {
        if (client.player == null) return;

        Config.getOptions().setRenderBossbar(!Config.getOptions().getRenderBossbar());
        Config.saveConfig();

        if (Config.getOptions().getRenderBossbar()) {
            client.player.sendMessage(Text.literal("The bossbar is now shown!").styled(style ->
                    style.withColor(Formatting.DARK_GRAY)), false);
        } else {
            client.player.sendMessage(Text.literal("The bossbar is now hidden!").styled(style ->
                    style.withColor(Formatting.DARK_GRAY)), false);
        }
    }

}
