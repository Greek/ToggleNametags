package sh.ndy.config;

public class Options {
    public boolean renderNametags = true;
    public boolean renderEntityNametags = true; // Non-player nametags
    public boolean renderSelfNametag = false;
    public  boolean renderBossbar = true;

    public boolean getRenderNametags() {
        return renderNametags;
    }

    public void setRenderNametags(boolean toggle) {
        this.renderNametags = toggle;
    }

    public boolean getRenderEntityNametags() {
        return renderEntityNametags;
    }

    public void setRenderEntityNametags(boolean toggle) {
        this.renderEntityNametags = toggle;
    }

    public boolean getRenderSelfNametag() {
        return renderSelfNametag;
    }

    public void setRenderSelfNametag(boolean toggle) {
        this.renderSelfNametag = toggle;
    }

    public boolean getRenderBossbar() {
        return renderBossbar;
    }

    public void setRenderBossbar(boolean toggle) {
        this.renderBossbar = toggle;
    }
}
