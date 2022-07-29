package dev.yeat.togglenametags.config;

public class Reader {

    public static boolean renderOtherEntities = false;
    public static boolean refreshingConfig;

    public static void register(boolean init) {
        SimpleConfig config = SimpleConfig.of("ToggleNametagsConfig").provider(Reader::ltProvider).request();

        if (init) {
            renderOtherEntities = config.getOrDefault("render_all_except_players", renderOtherEntities);
        }
    }

    public static String ltProvider(String filename) {
        return "# Toggle Nametags settings."
                + "\nrender_all_except_players=" + renderOtherEntities;
    }

}
