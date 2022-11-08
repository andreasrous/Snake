package scenes;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static constants.Constants.*;

public class MenuScene {

    private Game game;
    private Rect titleRect, playRect, exitRect;
    private BufferedImage playCurrentImage, exitCurrentImage;
    private BufferedImage title, play, playPressed, exit, exitPressed;

    public MenuScene(Game game) {
        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/menu_1.png"));
            title = spritesheet.getSubimage(0, 242, 960, 240);
            play = spritesheet.getSubimage(0, 121, 261, 121);
            playPressed = spritesheet.getSubimage(264, 121, 261, 121);
            exit = spritesheet.getSubimage(0, 0, 233, 93);
            exitPressed = spritesheet.getSubimage(264, 0, 233, 93);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.game = game;

        titleRect = new Rect(GAME_WIDTH / 6, GAME_HEIGHT / 5,
                (int) (GAME_WIDTH / 1.5), GAME_HEIGHT / 6);

        playRect = new Rect((int) (GAME_WIDTH / 3.3), (int) (GAME_HEIGHT / 2.5),
                (int) (GAME_WIDTH / 2.5), (int) (GAME_HEIGHT / 6.5));

        exitRect = new Rect((int) (GAME_WIDTH / 2.7), (int) (GAME_HEIGHT / 1.75),
                (int) (GAME_WIDTH / 3.75), (int) (GAME_HEIGHT / 10.5));

        playCurrentImage = play;
        exitCurrentImage = exit;
    }

    public void update() {

    }

    public void render(Graphics g) {
        g.setColor(new Color(169, 220, 118));
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        g.drawImage(title, titleRect.x, titleRect.y, titleRect.width, titleRect.height, null);
        g.drawImage(playCurrentImage, playRect.x, playRect.y, playRect.width, playRect.height, null);
        g.drawImage(exitCurrentImage, exitRect.x, exitRect.y, exitRect.width, exitRect.height, null);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getX() >= playRect.x && e.getX() <= playRect.x + playRect.width &&
                e.getY() >= playRect.y && e.getY() <= playRect.y + playRect.height) {

            if (game.getGameScene() == null)
                game.createGameScene();

            game.setState(GAME_SCENE);
        }
        if (e.getX() >= exitRect.x && e.getX() <= exitRect.x + exitRect.width &&
                e.getY() >= exitRect.y && e.getY() <= exitRect.y + exitRect.height)
            game.exitGame();
    }

    public void mouseMoved(MouseEvent e) {
        if (e.getX() >= playRect.x && e.getX() <= playRect.x + playRect.width &&
                e.getY() >= playRect.y && e.getY() <= playRect.y + playRect.height)
            playCurrentImage = playPressed;
        else
            playCurrentImage = play;
        if (e.getX() >= exitRect.x && e.getX() <= exitRect.x + exitRect.width &&
                e.getY() >= exitRect.y && e.getY() <= exitRect.y + exitRect.height)
            exitCurrentImage = exitPressed;
        else
            exitCurrentImage = exit;
    }
}
