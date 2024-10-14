package sh.ndy.config;

public class Options {
    public static boolean renderNametags;
    public static boolean renderEntityNametags; // Non-player nametags
    public static boolean renderSelfNametag;

    public Options(boolean renderNametags, boolean renderEntityNametags, boolean renderSelfNametag) {
        Options.renderNametags = renderNametags;
        Options.renderEntityNametags = renderEntityNametags;
        Options.renderSelfNametag = renderSelfNametag;
    }
}
