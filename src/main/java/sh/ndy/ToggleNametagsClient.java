package sh.ndy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sh.ndy.config.Config;
import sh.ndy.features.Bindings;
import sh.ndy.features.listeners.BossbarToggleListener;
import sh.ndy.features.listeners.NametagsToggleListener;
import sh.ndy.features.listeners.SelfNametagToggleListener;
import sh.ndy.screens.ConfigScreen;

public class ToggleNametagsClient implements ClientModInitializer {
  public static boolean isEssentialModLoaded;
  public static Logger logger = LoggerFactory.getLogger("ToggleNametags");

  private void checkForEssentialMod() {
    try {
      ToggleNametagsClient.class.getClassLoader().loadClass("gg.essential.Essential");
      this.isEssentialModLoaded = true;
      this.logger.info("Essential mod found. Disabling some features");
    } catch (ClassNotFoundException e) {
      this.isEssentialModLoaded = false;
    }
  }

  @Override
  public void onInitializeClient() {
    Minecraft c = Minecraft.getInstance();
    this.logger.info("Initializing Toggle Nametags");

    Config.loadConfig();
    Bindings.registerAll();
    checkForEssentialMod();

    if (this.isEssentialModLoaded) {
      this.logger.info("Set nametag opacity to its default because of Essential");
      Config.getOptions().setNametagOpacity(1);
    }

    KeyMapping renderNametagsKeybinding = Bindings.Action.TOGGLE_NAMETAGS.binding();
    NametagsToggleListener nametagsToggleListener = new NametagsToggleListener();

    KeyMapping renderBossBarKeybinding = Bindings.Action.TOGGLE_BOSS_BAR.binding();
    BossbarToggleListener bossbarToggleListener = new BossbarToggleListener();

    KeyMapping renderSelfNametagKeybinding = Bindings.Action.SHOW_SELF_NAMETAG.binding();
    SelfNametagToggleListener selfNametagToggleListener = new SelfNametagToggleListener();

    // TODO: prettify
    ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
      dispatcher.register(ClientCommandManager.literal("ntconfig").executes(context -> {
        context.getSource().getClient().schedule(() -> c.setScreen(new ConfigScreen(null, null)));

        return 1;
      }));
    });

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
      while (renderNametagsKeybinding.consumeClick()) {
        nametagsToggleListener.handleBinding(client, renderNametagsKeybinding);
      }

      while (renderBossBarKeybinding.consumeClick()) {
        bossbarToggleListener.handleBinding(client, renderBossBarKeybinding);
      }

      while (renderSelfNametagKeybinding.consumeClick()) {
        selfNametagToggleListener.handleBinding(client, renderSelfNametagKeybinding);
      }

    });
  }
}