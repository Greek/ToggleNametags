package sh.ndy.features.listeners;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

interface IBaseBindingListener<T> {
  static void handleBinding(Minecraft client, KeyMapping binding) {

  }

  static void handleMixin() {
  }
}
