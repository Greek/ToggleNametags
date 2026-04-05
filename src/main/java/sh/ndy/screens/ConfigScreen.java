package sh.ndy.screens;


import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import sh.ndy.Constants;
import sh.ndy.ToggleNametagsClient;
import sh.ndy.config.Config;


public class ConfigScreen extends Screen {
  private static Screen parent;
  private static MutableComponent TITLE = Component.translatable("key.category.minecraft.toggle_nametags");
  private static Component backText;
  private int STANDARD_BTN_WIDTH = 162;
  private int FULL_BTN_WIDTH = 332;

  public ConfigScreen(Screen parent, String backText) {
    super(TITLE);

    this.parent = parent;
    this.backText = backText != null ? Component.nullToEmpty(backText) : Component.nullToEmpty("Back to Game");
  }

  private Component getRenderNametagsText() {
    return Component.nullToEmpty("Show all nametags: " + (Config.getOptions().getRenderNametags() ? "§aYes" : "§cNo"));
  }

  private Component getRenderSelfNametagText() {
    return Component.nullToEmpty("Show your own nametags: " + (Config.getOptions().getRenderSelfNametag() ? "§aYes" : "§cNo"));
  }

  private Component getRenderBossbarText() {
    return Component.nullToEmpty("Show the bossbar: " + (Config.getOptions().getRenderBossbar() ? "§aYes" : "§cNo"));
  }

  private double calcNametagOpacityDisplayVal() {
    return Config.getOptions().getNametagOpacity() / Constants.NAMETAG_OPACITY_MULTIPLIER;
  }

  private Component getNametagOpacityText() {
    return Component.nullToEmpty("Nametag Opacity: " + String.format("%.2f", calcNametagOpacityDisplayVal()) + "%");
  }

  private Component getRenderNametagShadowText() {
    return Component.nullToEmpty(
        "Show nametag text shadow: " + (Config.getOptions().getRenderNametagTextShadow() ? "§aYes" : "§cNo"));
  }

  protected void init() {
    GridLayout grid = new GridLayout();
    grid.defaultCellSetting().padding(4, 4, 4, 2);
    GridLayout.RowHelper adder = grid.createRowHelper(2);

    Button renderNametagsBtnWidget = Button.builder(getRenderNametagsText(), (btn) -> {
      Config.getOptions().setRenderNametags(!Config.getOptions().getRenderNametags());
      Config.saveConfig();
      btn.setFocused(false);
      btn.setMessage(getRenderNametagsText());
    }).width(STANDARD_BTN_WIDTH).build();

    Button renderSelfNametagsBtnWidget = Button.builder(getRenderSelfNametagText(), (btn) -> {
      Config.getOptions().setRenderSelfNametag(!Config.getOptions().getRenderSelfNametag());
      Config.saveConfig();
      btn.setFocused(false);
      btn.setMessage(getRenderSelfNametagText());
    }).width(STANDARD_BTN_WIDTH).build();

    Button renderNametagShadowBtnWidget = Button.builder(getRenderNametagShadowText(), (btn) -> {
      Config.getOptions().setRenderNametagTextShadow(!Config.getOptions().getRenderNametagTextShadow());
      Config.saveConfig();
      btn.setFocused(false);
      btn.setMessage(getRenderNametagShadowText());
    }).width(STANDARD_BTN_WIDTH).build();

    Button renderBossbarBtnWidget = Button.builder(getRenderBossbarText(), (btn) -> {
      Config.getOptions().setRenderBossbar(!Config.getOptions().getRenderBossbar());
      Config.saveConfig();
      btn.setFocused(false);
      btn.setMessage(getRenderBossbarText());
    }).width(FULL_BTN_WIDTH).build();


    Button backBtn = Button.builder(this.backText, (btn) -> this.onClose()).width(STANDARD_BTN_WIDTH).build();

    AbstractSliderButton nametagOpacitySlider =
        new AbstractSliderButton(this.width / 2, 185, 162, 20, getNametagOpacityText(), calcNametagOpacityDisplayVal()) {
          @Override
          protected void updateMessage() {
            this.setMessage(getNametagOpacityText());
          }

          @Override
          protected void applyValue() {
            Config.getOptions().setNametagOpacity((float) this.value);
            Config.saveConfig();
          }
        };

    // TODO: Find a way to remove Essential's padding around the nametags, so we don't have to disable
    //       this option when Essential is loaded.
    if (ToggleNametagsClient.isEssentialModLoaded) {
      nametagOpacitySlider.active = false;
      nametagOpacitySlider.setTooltip(Tooltip.create(Component.nullToEmpty(
          "Please disable \"Essential\" in your §llauncher§r, in order to change nametag opacity.")));
    }

    adder.addChild(renderNametagsBtnWidget);
    adder.addChild(renderSelfNametagsBtnWidget);
    adder.addChild(renderNametagShadowBtnWidget);
    adder.addChild(nametagOpacitySlider);
    adder.addChild(renderBossbarBtnWidget, 2);

    grid.arrangeElements();

    FrameLayout.alignInRectangle(grid, 0, 0, this.width, this.height, 0.5F, 0.25F);
    FrameLayout.centerInRectangle(backBtn, this.width / 2, 0, 0, this.height + 20);

    grid.visitWidgets(this::addRenderableWidget);
    backBtn.visitWidgets(this::addRenderableWidget);
  }

  @Override
  public void render(GuiGraphics context, int mouseX, int mouseY, float deltaTicks) {
    super.render(context, mouseX, mouseY, deltaTicks);
    context.drawCenteredString(this.font, this.title, this.width / 2, 8, 0xFFFFFFFF);
  }

  @Override
  public void onClose() {
    this.minecraft.setScreen(parent);
  }
}
