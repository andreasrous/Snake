package entities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.SecureRandom;

import static constants.Constants.*;

public class Apple {

    private int x, y;
    private SecureRandom random;
    private BufferedImage appleImg;

    public Apple() {
        try {
            appleImg = ImageIO.read(new File("assets/apple_1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newApple() {
        random = new SecureRandom();
        x = random.nextInt(TILES_IN_WIDTH) * TILE_SIZE;
        y = random.nextInt(TILES_IN_HEIGHT) * TILE_SIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getAppleImg() {
        return appleImg;
    }
}
