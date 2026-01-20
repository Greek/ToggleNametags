package sh.ndy.config;

public class Options {
  public boolean renderNametags = true;
  public boolean renderSelfNametag = false;
  public boolean renderBossbar = true;
  private double nametagOpacity = 0.25F;

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
      return nametagOpacity;
  }

  public void setNametagOpacity(float newOpacity) {
      this.nametagOpacity = Math.floor(newOpacity * 100) / 100;
      System.out.println(this.nametagOpacity);
  }
}
