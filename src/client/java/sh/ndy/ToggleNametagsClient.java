package sh.ndy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import sh.ndy.bindings.Bindings;
import sh.ndy.bindings.listeners.NametagsToggleListener;
import sh.ndy.bindings.listeners.BossbarToggleListener;
import sh.ndy.bindings.listeners.SelfNametagToggleListener;
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
                NametagsToggleListener.handleEvent(client, renderNametagsKeybinding);
			}

			while (renderBossBarKeybinding.wasPressed()) {
                BossbarToggleListener.handleEvent(client, renderBossBarKeybinding);
			}

			while (renderSelfNametagKeybinding.wasPressed()) {
                SelfNametagToggleListener.handleEvent(client, renderSelfNametagKeybinding);
			}

		});
	}
}