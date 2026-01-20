package sh.ndy.screens;


import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.SimplePositioningWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import sh.ndy.config.Options;
import sh.ndy.config.Config;


public class ConfigScreen extends Screen {
    private static Screen parent;
    private static MutableText TITLE = Text.translatable("key.category.minecraft.toggle_nametags");
    private static Options options = Config.getOptions();

    public ConfigScreen(Screen parent) {
        super(TITLE);

        this.parent = parent;
    }

    private Text getRenderNametagsText() {
        return Text.of(
                "Show all nametags: " +
                        (Config.getOptions().getRenderNametags() ? "§aYes" : "§cNo")
        );
    }

    private Text getRenderSelfNametagText() {
        return Text.of(
                "Show your own nametags: " +
                        (Config.getOptions().getRenderSelfNametag() ? "§aYes" : "§cNo")
        );
    }

    private Text getRenderBossbarText() {
        return Text.of(
                "Show the bossbar: " +
                        (Config.getOptions().getRenderBossbar() ? "§aYes" : "§cNo")
        );
    }

    protected void init() {
        super.init();

        GridWidget grid = new GridWidget();
        grid.getMainPositioner().margin(4, 4, 4, 2);
        GridWidget.Adder adder = grid.createAdder(2);

        ButtonWidget renderNametagsBtnWidget = ButtonWidget.builder(
                getRenderNametagsText(), (btn) -> {
                    Config.getOptions().setRenderNametags(!Config.getOptions().getRenderNametags());
                    Config.saveConfig();
                    btn.setFocused(false);
                    btn.setMessage(getRenderNametagsText());
                }
        ).width(162).build();

        ButtonWidget renderSelfNametagsBtnWidget = ButtonWidget.builder(
                getRenderSelfNametagText(), (btn) -> {
                    Config.getOptions().setRenderSelfNametag(!Config.getOptions().getRenderSelfNametag());
                    Config.saveConfig();
                    btn.setFocused(false);
                    btn.setMessage(getRenderSelfNametagText());
                }
        ).width(162).build();

        ButtonWidget renderBossbarBtnWidget = ButtonWidget.builder(
                getRenderBossbarText(), (btn) -> {
                    Config.getOptions().setRenderBossbar(!Config.getOptions().getRenderBossbar());
                    Config.saveConfig();
                    btn.setFocused(false);
                    btn.setMessage(getRenderBossbarText());
                }
        ).width(332).build();

        ButtonWidget backToModMenuBtn = ButtonWidget.builder(
                Text.of("Back to Mod Menu"), (btn) -> this.close()
        ).width(162).build();

        adder.add(renderNametagsBtnWidget);
        adder.add(renderSelfNametagsBtnWidget);
        adder.add(renderBossbarBtnWidget, 2);

        grid.refreshPositions();

        SimplePositioningWidget.setPos(grid, 0, 0, this.width, this.height, 0.5F, 0.25F);
        SimplePositioningWidget.setPos(
                backToModMenuBtn, this.width / 2, 0, 0, this.height - 28);

        grid.forEachChild(this::addDrawableChild);
        backToModMenuBtn.forEachChild(this::addDrawableChild);
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
