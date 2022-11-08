package scenes;

import entities.Apple;
import entities.Player;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

import static constants.Constants.MENU_SCENE;

public class GameScene {

    private Game game;
    private Apple apple;
    private Player player;

    public GameScene(Game game) {
        this.game = game;
        apple = new Apple();
        player = new Player(game, apple);
    }

    public void update() {
        player.update();
    }

    public void render(Graphics g) {
        player.render(g);
    }

    public void reset() {
        game.setState(MENU_SCENE);
    }

    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) && player.direction != 'D') {
            player.up = true;
            player.left = false;
            player.right = false;
        }

        if ((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) && player.direction != 'R') {
            player.left = true;
            player.up = false;
            player.down = false;
        }

        if ((e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) && player.direction != 'U') {
            player.down = true;
            player.left = false;
            player.right = false;
        }

        if ((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && player.direction != 'L') {
            player.right = true;
            player.up = false;
            player.down = false;
        }
    }
}
