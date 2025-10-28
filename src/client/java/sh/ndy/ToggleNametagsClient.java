package sh.ndy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import sh.ndy.bindings.Bindings;
import sh.ndy.config.Config;

public class ToggleNametagsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Config.loadConfig();
        Bindings.registerAll();

        KeyBinding renderNametagsKeybinding = Bindings.Action.TOGGLE_NAMETAGS.binding();
        KeyBinding renderBossBarKeybinding = Bindings.Action.TOGGLE_BOSS_BAR.binding();
        KeyBinding renderSelfNametagKeybinding = Bindings.Action.SHOW_SELF_NAMETAG.binding();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (renderNametagsKeybinding.wasPressed()) {
				if (Config.getOptions().getRenderNametags()) {
					Config.getOptions().setRenderNametags(false);

					if (client.player == null) return;
					client.player.sendMessage(Text.literal("Nametags are now hidden!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				} else {
					Config.getOptions().setRenderNametags(true);

					if (client.player == null) return;
					client.player.sendMessage(Text.literal("Nametags are now shown!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				}

				Config.saveConfig();
			}

			while (renderBossBarKeybinding.wasPressed()) {
				if (Config.getOptions().getRenderBossbar()) {
					Config.getOptions().setRenderBossbar(false);
					if (client.player == null) return;

					client.player.sendMessage(Text.literal("The bossbar is now hidden!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				} else {
					Config.getOptions().setRenderBossbar(true);
					if (client.player == null) return;

					client.player.sendMessage(Text.literal("The bossbar is now shown!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				}

				Config.saveConfig();
			}

			while (renderSelfNametagKeybinding.wasPressed()) {
				if (Config.getOptions().getRenderSelfNametag()) {
					Config.getOptions().setRenderSelfNametag(false);
					if (client.player == null) return;

					client.player.sendMessage(Text.literal("Your nametag is hidden!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				} else {
					Config.getOptions().setRenderSelfNametag(true);
					if (client.player == null) return;

					client.player.sendMessage(Text.literal("You can see your own nametag!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				}

				Config.saveConfig();
			}

		});
	}
}