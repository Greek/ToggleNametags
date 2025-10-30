package sh.ndy.features;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class Bindings {
  private static final String CATEGORY_NAME = "toggle_nametags";

  public static void registerAll() {
	KeyBinding.Category category = new KeyBinding.Category(Identifier.of(CATEGORY_NAME));
	for (Action a : Action.values()) {
	  a.binding = KeyBindingHelper.registerKeyBinding(
			  new KeyBinding(
					  a.label,
					  InputUtil.Type.KEYSYM,
					  a.defaultKey,
					  category
			  )
	  );
	}
  }

  public enum Action {
	TOGGLE_NAMETAGS("Toggle Nametags", GLFW.GLFW_KEY_MINUS),
	TOGGLE_BOSS_BAR("Toggle Boss Bar", GLFW.GLFW_KEY_M),
	SHOW_SELF_NAMETAG("Show your own nametag", GLFW.GLFW_KEY_RIGHT_BRACKET);

	private final String label;
	private final int defaultKey;
	private KeyBinding binding;

	Action(String label, int defaultKey) {
	  this.label = label;
	  this.defaultKey = defaultKey;
	}

	public KeyBinding binding() {
	  return binding;
	}
  }

}
