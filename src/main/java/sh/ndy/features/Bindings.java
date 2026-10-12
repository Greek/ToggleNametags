package sh.ndy.features;

import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
//? if <=1.21.10 {
/* import net.minecraft.resources.ResourceLocation; */
//?} else
import net.minecraft.resources.Identifier;
//? if < 26.3 {
 /*import org.lwjgl.glfw.GLFW; 
*///?}

public class Bindings {
  private static final String CATEGORY_NAME = "toggle_nametags";

  public static void registerAll() {
    //? if <=1.21.10 {
    /* KeyMapping.Category category = new KeyMapping.Category(ResourceLocation.parse(CATEGORY_NAME)); */
    //?} else
    KeyMapping.Category category = new KeyMapping.Category(Identifier.parse(CATEGORY_NAME));

    for (Action a : Action.values()) {
      //? if < 26.3 {
       /*a.binding =
          KeyMappingHelper.registerKeyMapping(new KeyMapping(a.label, InputConstants.Type.KEYSYM, a.defaultKey, category)); 
      *///?} else {
      a.binding =
          KeyMappingHelper.registerKeyMapping(new KeyMapping(a.label, InputConstants.Type.KEYBOARD, a.defaultKey, category));
      //?}
    }
  }

  public enum Action {
    //? if < 26.3 {
     /*TOGGLE_NAMETAGS("Toggle Nametags", GLFW.GLFW_KEY_MINUS), TOGGLE_BOSS_BAR("Toggle Boss Bar", GLFW.GLFW_KEY_M), SHOW_SELF_NAMETAG("Show your own nametag", GLFW.GLFW_KEY_RIGHT_BRACKET); 
    *///?} else {
    TOGGLE_NAMETAGS("Toggle Nametags", InputConstants.KEY_MINUS), TOGGLE_BOSS_BAR("Toggle Boss Bar",InputConstants.KEY_M), SHOW_SELF_NAMETAG("Show your own nametag", InputConstants.KEY_RBRACKET);
    //?}

    private final String label;
    private final int defaultKey;
    private KeyMapping binding;

    Action(String label, int defaultKey) {
      this.label = label;
      this.defaultKey = defaultKey;
    }

    public KeyMapping binding() {
      return binding;
    }
  }

}
