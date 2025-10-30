package sh.ndy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import sh.ndy.features.Bindings;
import sh.ndy.features.listeners.BossbarToggleListener;
import sh.ndy.features.listeners.NametagsToggleListener;
import sh.ndy.features.listeners.SelfNametagToggleListener;
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
		new NametagsToggleListener().handleBinding(client, renderNametagsKeybinding);
	  }

	  while (renderBossBarKeybinding.wasPressed()) {
		new BossbarToggleListener().handleBinding(client, renderBossBarKeybinding);
	  }

	  while (renderSelfNametagKeybinding.wasPressed()) {
		new SelfNametagToggleListener().handleBinding(client, renderSelfNametagKeybinding);
	  }

	});
  }
}