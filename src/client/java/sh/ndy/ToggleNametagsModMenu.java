package sh.ndy;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import sh.ndy.screens.ConfigScreen;

public class ToggleNametagsModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> new ConfigScreen(screen);
    }
}
