package dev.yeat.togglenametags;

import dev.yeat.togglenametags.config.Reader;
import net.fabricmc.api.ModInitializer;

public class Togglenametags implements ModInitializer {
    @Override
    public void onInitialize() {
        Reader.register(true);
    }
}
