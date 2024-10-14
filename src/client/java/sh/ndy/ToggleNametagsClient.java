package sh.ndy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;
import sh.ndy.config.Options;
import sh.ndy.config_old.Reader;
import sh.ndy.config_old.SimpleConfig;

public class ToggleNametagsClient implements ClientModInitializer {
	private static KeyBinding renderNametagsKeybinding;
	private static KeyBinding renderAllExceptPlayersKeybinding;
	private static KeyBinding renderBossBarKeybinding;
	private static KeyBinding renderSelfNametagKeybinding;

	public static boolean shouldRender = true;
	public static boolean shouldRenderBossBar = true;

	@Override
	public void onInitializeClient() {
		Options options = new Options(true, true, false);

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
				if (shouldRender) {
					shouldRender = false;
					if (client.player == null) return;
					client.player.sendMessage(Text.literal("Nametags are now hidden!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				} else {
					shouldRender = true;
					if (client.player == null) return;
					client.player.sendMessage(Text.literal("Nametags are now shown!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				}
			}

			while (renderAllExceptPlayersKeybinding.wasPressed()) {
				SimpleConfig config = SimpleConfig.of("ToggleNametagsConfig").provider(Reader::ltProvider).request();

				if (Reader.renderOtherEntities) {
					Reader.renderOtherEntities = false;
					config.getOrDefault("render_all_except_players", false);
					if (client.player == null) return;
					client.player.sendMessage(Text.literal("Nametags from other entities are now hidden!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				} else {
					Reader.renderOtherEntities = true;
					config.getOrDefault("render_all_except_players", true);
					if (client.player == null) return;
					client.player.sendMessage(Text.literal("Nametags from other entities are now shown!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				}
			}
			while (renderBossBarKeybinding.wasPressed()) {
				if (shouldRenderBossBar) {
					shouldRenderBossBar = false;
					if (client.player == null) return;

					client.player.sendMessage(Text.literal("The bossbar is now hidden!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				} else {
					shouldRenderBossBar = true;
					if (client.player == null) return;
					client.player.sendMessage(Text.literal("The bossbar is now shown!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				}
			}

			while (renderSelfNametagKeybinding.wasPressed()) {
				SimpleConfig config = SimpleConfig.of("ToggleNametagsConfig").provider(Reader::ltProvider).request();

				if (Reader.renderOwnNametag) {
					config.getOrDefault("render_own_nametag", false);
					Reader.renderOwnNametag = false;
					if (client.player == null) return;
					client.player.sendMessage(Text.literal("Your nametag is hidden!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				} else {
					config.getOrDefault("render_own_nametag", true);
					Reader.renderOwnNametag = true;
					if (client.player == null) return;
					client.player.sendMessage(Text.literal("You can see your own nametag!").styled(style ->
							style.withColor(Formatting.DARK_GRAY)), false);
				}
			}

		});
	}
}