package sh.ndy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import sh.ndy.features.Bindings;
import sh.ndy.features.listeners.BossbarToggleListener;
import sh.ndy.features.listeners.NametagsToggleListener;
import sh.ndy.features.listeners.SelfNametagToggleListener;
import sh.ndy.config.Config;
import sh.ndy.screens.ConfigScreen;

public class ToggleNametagsClient implements ClientModInitializer {
  public static boolean isEssentialModLoaded;

  private void checkForEssentialMod() {
	  try {
          ToggleNametagsClient.class.getClassLoader().loadClass("gg.essential.Essential");
          this.isEssentialModLoaded = true;
      } catch (ClassNotFoundException e) {
          this.isEssentialModLoaded = false;
      }
  }

  @Override
  public void onInitializeClient() {
	MinecraftClient c = MinecraftClient.getInstance();

	Config.loadConfig();
	Bindings.registerAll();
	checkForEssentialMod();

	if (this.isEssentialModLoaded) {
		Config.getOptions().setNametagOpacity(1);
	}

	KeyBinding renderNametagsKeybinding = Bindings.Action.TOGGLE_NAMETAGS.binding();
	NametagsToggleListener nametagsToggleListener = new NametagsToggleListener();

	KeyBinding renderBossBarKeybinding = Bindings.Action.TOGGLE_BOSS_BAR.binding();
	BossbarToggleListener bossbarToggleListener = new BossbarToggleListener();

	KeyBinding renderSelfNametagKeybinding = Bindings.Action.SHOW_SELF_NAMETAG.binding();
	SelfNametagToggleListener selfNametagToggleListener = new SelfNametagToggleListener();

	ClientTickEvents.END_CLIENT_TICK.register(client -> {
	  while (renderNametagsKeybinding.wasPressed()) {
		nametagsToggleListener.handleBinding(client, renderNametagsKeybinding);
	  }

	  while (renderBossBarKeybinding.wasPressed()) {
		bossbarToggleListener.handleBinding(client, renderBossBarKeybinding);
	  }

	  while (renderSelfNametagKeybinding.wasPressed()) {
		selfNametagToggleListener.handleBinding(client, renderSelfNametagKeybinding);
	  }

	});

	// TODO: prettify
	ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
		dispatcher.register(ClientCommandManager.literal("ntconfig").executes(context -> {
			c.execute(() -> c.setScreen(new ConfigScreen(null)));

			return 1;
		}));
	});
  }
}