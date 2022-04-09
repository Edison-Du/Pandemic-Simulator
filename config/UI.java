package config;

import java.awt.*;

public final class UI {
    public static final int UPDATE_RATE = 10;
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;

    public static final int SIDE_BAR_WIDTH = 300;
    public static final int MAIN_PANEL_WIDTH  = WINDOW_WIDTH - SIDE_BAR_WIDTH;
    public static final int MAIN_PANEL_HEIGHT = WINDOW_HEIGHT;

    public static final Color MAIN_PANEL_BG = new Color(17, 17, 17);
    public static final Color SIDE_BAR_BG = new Color(40, 40, 40);

    public static final Color SUSCEPTIBLE_COLOR = new Color(0, 147, 154);
    public static final Color INFECTED_COLOR = new Color(231, 39, 45);
    public static final Color REMOVED_COLOR = new Color(102, 102, 102);

}
