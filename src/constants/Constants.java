package constants;

public class Constants {
    public static final int FPS = 60;
    public static final int DELAY = 70;
    public static final double SCALE = 1.5;
    public static final int GAME_SCENE = 1;
    public static final int MENU_SCENE = 2;
    public static final int TILES_IN_WIDTH = 32;
    public static final int TILES_IN_HEIGHT = 32;
    public static final int DEFAULT_TILE_SIZE = 24;
    public static final int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
    public static final int GAME_WIDTH = TILE_SIZE * TILES_IN_WIDTH;
    public static final int GAME_HEIGHT = TILE_SIZE * TILES_IN_HEIGHT;
}
