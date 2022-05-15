package dev.yeat.togglenametags.togglenametags.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
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
                    client.player.sendSystemMessage(new LiteralText("Nametags are now hidden!").styled(style ->
                            style.withColor(Formatting.DARK_GRAY)), Util.NIL_UUID);
                } else {
                    shouldRender = true;
                    client.player.sendSystemMessage(new LiteralText("Nametags are now shown!").styled(style ->
                            style.withColor(Formatting.DARK_GRAY)), Util.NIL_UUID);
                }
            }
        });
    }
}
