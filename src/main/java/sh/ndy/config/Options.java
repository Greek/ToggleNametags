package sh.ndy.config;

import sh.ndy.Constants;

// TODO: Use lombok
public class Options {
  public boolean renderNametags = true;
  public boolean renderSelfNametag = false;
  public boolean renderBossbar = true;
  private double nametagOpacity = 1 * Constants.NAMETAG_OPACITY_MULTIPLIER;
  private boolean renderNametagTextShadow = false;

  public boolean getRenderNametags() {
    return renderNametags;
  }

  public void setRenderNametags(boolean toggle) {
    this.renderNametags = toggle;
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

  public double getNametagOpacity() {
    return this.nametagOpacity;
  }

  public void setNametagOpacity(float newOpacity) {
    // round to the nearest hundredths
    this.nametagOpacity = (Math.floor(newOpacity * 100.0) / 100.0) * Constants.NAMETAG_OPACITY_MULTIPLIER;
  }

  public boolean getRenderNametagTextShadow() {
    return this.renderNametagTextShadow;
  }

  public void setRenderNametagTextShadow(boolean newSetting) {
    this.renderNametagTextShadow = newSetting;
  }
}
