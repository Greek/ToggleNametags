package dev.yeat.togglenametags.togglenametags.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class TogglenametagsClient implements ClientModInitializer {

    private static KeyBinding keyBinding;
    public static boolean shouldRender = true;


    @Override
    public void onInitializeClient() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Toggle Nametags", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_MINUS, // The keycode of the key
                "Nametags" // The translation key of the keybinding's category.
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                if (shouldRender) {
                    shouldRender = false;
                    client.player.sendMessage(new LiteralText("Nametags are now hidden!"), false);
                } else {
                    shouldRender = true;
                    client.player.sendMessage(new LiteralText("Nametags are now shown!"), false);
                }
            }
        });
    }
}
