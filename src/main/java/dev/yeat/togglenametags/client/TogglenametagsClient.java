package dev.yeat.togglenametags.client;

import dev.yeat.togglenametags.config.Reader;
import dev.yeat.togglenametags.config.SimpleConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class TogglenametagsClient implements ClientModInitializer {

    private static KeyBinding keyBinding;
    private static KeyBinding keyBinding2;
    public static boolean shouldRender = true;


    @Override
    public void onInitializeClient() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Toggle Nametags", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_MINUS, // The keycode of the key
                "Nametags" // The translation key of the keybinding's category.
        ));

        keyBinding2 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Toggle Other Nametags",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_EQUAL,
                "Nametags"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                if (shouldRender) {
                    shouldRender = false;
                    client.player.sendMessage(Text.literal("Nametags are now hidden!").styled(style ->
                            style.withColor(Formatting.DARK_GRAY)), false);
                } else {
                    shouldRender = true;
                    client.player.sendMessage(Text.literal("Nametags are now shown!").styled(style ->
                            style.withColor(Formatting.DARK_GRAY)), false);
                }
            }

            while (keyBinding2.wasPressed()) {
                SimpleConfig config = SimpleConfig.of("ToggleNametagsConfig").provider(Reader::ltProvider).request();

                if (Reader.renderOtherEntities) {
                    Reader.renderOtherEntities = false;
                    config.getOrDefault("render_all_except_players", false);
                    client.player.sendMessage(Text.literal("Nametags from other entities are now hidden!").styled(style ->
                            style.withColor(Formatting.DARK_GRAY)), false);
                } else {
                    Reader.renderOtherEntities = true;
                    config.getOrDefault("render_all_except_players", true);
                    client.player.sendMessage(Text.literal("Nametags from other entities are now shown!").styled(style ->
                            style.withColor(Formatting.DARK_GRAY)), false);
                }
            }
        });
    }
}
