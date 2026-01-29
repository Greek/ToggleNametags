package sh.ndy.screens;


import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.SimplePositioningWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import sh.ndy.Constants;
import sh.ndy.ToggleNametagsClient;
import sh.ndy.config.Config;


public class ConfigScreen extends Screen {
  private static final MutableText TITLE = Text.translatable("key.category.minecraft.toggle_nametags");
  private final Screen parent;
  private final Text backText;
  private int STANDARD_BTN_WIDTH = 162;
  private int FULL_BTN_WIDTH = 332;

  public ConfigScreen(Screen parent, String backText) {
    super(TITLE);

    this.parent = parent;
    this.backText = backText != null ? Text.of(backText) : Text.of("Back to Game");
  }

  private Text getRenderNametagsText() {
    return Text.of("Show all nametags: " + (Config.getOptions().getRenderNametags() ? "§aYes" : "§cNo"));
  }

  private Text getRenderSelfNametagText() {
    return Text.of("Show your own nametags: " + (Config.getOptions().getRenderSelfNametag() ? "§aYes" : "§cNo"));
  }

  private Text getRenderBossbarText() {
    return Text.of("Show the bossbar: " + (Config.getOptions().getRenderBossbar() ? "§aYes" : "§cNo"));
  }

  private double calcNametagOpacityDisplayVal() {
    return Config.getOptions().getNametagOpacity() / Constants.NAMETAG_OPACITY_MULTIPLIER;
  }

  private Text getNametagOpacityText() {
    return Text.of("Nametag Opacity: " + String.format("%.2f", calcNametagOpacityDisplayVal()) + "%");
  }

  private Text getRenderNametagShadowText() {
    return Text.of(
        "Show nametag text shadow: " + (Config.getOptions().getRenderNametagTextShadow() ? "§aYes" : "§cNo"));
  }

  protected void init() {
    GridWidget grid = new GridWidget();
    grid.getMainPositioner().margin(4, 4, 4, 2);
    GridWidget.Adder adder = grid.createAdder(2);

    ButtonWidget renderNametagsBtnWidget = ButtonWidget.builder(getRenderNametagsText(), (btn) -> {
      Config.getOptions().setRenderNametags(!Config.getOptions().getRenderNametags());
      Config.saveConfig();
      btn.setFocused(false);
      btn.setMessage(getRenderNametagsText());
    }).width(STANDARD_BTN_WIDTH).build();

    ButtonWidget renderSelfNametagsBtnWidget = ButtonWidget.builder(getRenderSelfNametagText(), (btn) -> {
      Config.getOptions().setRenderSelfNametag(!Config.getOptions().getRenderSelfNametag());
      Config.saveConfig();
      btn.setFocused(false);
      btn.setMessage(getRenderSelfNametagText());
    }).width(STANDARD_BTN_WIDTH).build();

    ButtonWidget renderNametagShadowBtnWidget = ButtonWidget.builder(getRenderNametagShadowText(), (btn) -> {
      Config.getOptions().setRenderNametagTextShadow(!Config.getOptions().getRenderNametagTextShadow());
      Config.saveConfig();
      btn.setFocused(false);
      btn.setMessage(getRenderNametagShadowText());
    }).width(STANDARD_BTN_WIDTH).build();

    ButtonWidget renderBossbarBtnWidget = ButtonWidget.builder(getRenderBossbarText(), (btn) -> {
      Config.getOptions().setRenderBossbar(!Config.getOptions().getRenderBossbar());
      Config.saveConfig();
      btn.setFocused(false);
      btn.setMessage(getRenderBossbarText());
    }).width(FULL_BTN_WIDTH).build();


    ButtonWidget backBtn = ButtonWidget.builder(this.backText, (btn) -> this.close()).width(STANDARD_BTN_WIDTH).build();

    SliderWidget nametagOpacitySlider =
        new SliderWidget(this.width / 2, 185, 162, 20, getNametagOpacityText(), calcNametagOpacityDisplayVal()) {
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
      nametagOpacitySlider.setTooltip(Tooltip.of(Text.of(
          "Please disable \"Essential\" in your §llauncher§r, in order to change nametag opacity.")));
    }

    adder.add(renderNametagsBtnWidget);
    adder.add(renderSelfNametagsBtnWidget);
    adder.add(renderNametagShadowBtnWidget);
    adder.add(nametagOpacitySlider);
    adder.add(renderBossbarBtnWidget, 2);

    grid.refreshPositions();

    SimplePositioningWidget.setPos(grid, 0, 0, this.width, this.height, 0.5F, 0.25F);
    SimplePositioningWidget.setPos(backBtn, this.width / 2, 0, 0, this.height + 20);

    grid.forEachChild(this::addDrawableChild);
    backBtn.forEachChild(this::addDrawableChild);
  }

  @Override
  public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
    super.render(context, mouseX, mouseY, deltaTicks);
    context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 8, 0xFFFFFFFF);
  }

  @Override
  public void close() {
    this.client.setScreen(parent);
  }
}
