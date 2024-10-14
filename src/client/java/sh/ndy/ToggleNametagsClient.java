package sh.ndy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;
import sh.ndy.config.Config;
import sh.ndy.config.Options;

public class ToggleNametagsClient implements ClientModInitializer {
	private static KeyBinding renderNametagsKeybinding;
	private static KeyBinding renderAllExceptPlayersKeybinding;
	private static KeyBinding renderBossBarKeybinding;
	private static KeyBinding renderSelfNametagKeybinding;

	@Override
	public void onInitializeClient() {
		Config.loadConfig();

		renderNametagsKeybinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"Toggle Nametags", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_MINUS, // The keycode of the key
				"Nametags" // The translation key of the keybinding's category.
		));

		renderAllExceptPlayersKeybinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"Toggle Other Nametags",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_EQUAL,
				"Nametags"
		));

		renderBossBarKeybinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"Toggle Boss Bar",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_M,
				"Nametags"
		));

		renderSelfNametagKeybinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("Show your own nametag",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT_BRACKET,
				"Nametags"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (renderNametagsKeybinding.wasPressed()) {
				if (Config.getOptions().getRenderNametags()) {
					Config.getOptions().setRenderNametags(false);

					if (client.player == null) return;
					client.player.sendMessage(Text.literal("Nametags are now hidden!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				} else {
					Config.getOptions().setRenderNametags(false);

					if (client.player == null) return;
					client.player.sendMessage(Text.literal("Nametags are now shown!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				}

				Config.saveConfig();
			}

			while (renderAllExceptPlayersKeybinding.wasPressed()) {
				if (Config.getOptions().getRenderEntityNametags()) {
					Config.getOptions().setRenderEntityNametags(false);
					if (client.player == null) return;

					client.player.sendMessage(Text.literal("Nametags from other entities are now hidden!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				} else {
					Config.getOptions().setRenderEntityNametags(true);
					if (client.player == null) return;

					client.player.sendMessage(Text.literal("Nametags from other entities are now shown!").styled(style ->
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