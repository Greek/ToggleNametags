package sh.ndy.features.listeners;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

interface IBaseBindingListener<T> {
  void handleBinding(MinecraftClient client, KeyBinding binding);
  static void handleMixin() {}
}
