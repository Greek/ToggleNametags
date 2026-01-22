package sh.ndy.features.listeners;

import sh.ndy.config.Config;

public class NametagsTextShadowListener implements IBaseBindingListener {
    public boolean handleMixin() {
        return Config.getOptions().getRenderNametagTextShadow();
    }
}
