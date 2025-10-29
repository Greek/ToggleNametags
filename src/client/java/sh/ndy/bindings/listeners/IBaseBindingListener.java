package sh.ndy.bindings.listeners;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

interface IBaseBindingListener {
  static void handleEvent(MinecraftClient client, KeyBinding binding) {
  }
}
