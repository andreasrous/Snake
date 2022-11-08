package entities;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static constants.Constants.*;

public class Player {

    private int x[];
    private int y[];
    private int offset = 3;
    private int bodyParts = 6;
    private int applesEaten = 0;
    public char direction;
    private long lastCheck;
    private boolean running = true;
    public boolean up, left, down, right;
    private BufferedImage snakeImg;
    private Apple apple;
    private Game game;

    public Player(Game game, Apple apple) {
        this.game = game;
        this.apple = apple;
        direction = 'R';
        right = true;
        apple.newApple();

        x = new int[TILES_IN_WIDTH * TILES_IN_HEIGHT];
        y = new int[TILES_IN_WIDTH * TILES_IN_HEIGHT];

        for (int i = 0; i < TILES_IN_WIDTH * TILES_IN_HEIGHT; i++) {
            if (i < bodyParts)
                x[i] = (bodyParts - i - 1) * TILE_SIZE;
            else
                x[i] = 0;

            y[i] = 0;
        }

        try {
            snakeImg = ImageIO.read(new File("assets/snake_2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (running) {
            if (System.currentTimeMillis() - lastCheck >= DELAY) {
                lastCheck = System.currentTimeMillis();
                move();
                checkApple();
                checkCollisions();

                if (up)
                    direction = 'U';
                if (left)
                    direction = 'L';
                if (down)
                    direction = 'D';
                if (right)
                    direction = 'R';
            }
        } else {
            game.setState(MENU_SCENE);
            game.deleteGameScene();
        }
    }

    public void render(Graphics g) {
        drawSnake(g);
        drawApple(g);
    }

    private void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (up)
            y[0] = y[0] - TILE_SIZE;
        if (left)
            x[0] = x[0] - TILE_SIZE;
        if (down)
            y[0] = y[0] + TILE_SIZE;
        if (right)
            x[0] = x[0] + TILE_SIZE;
    }

    public void checkApple() {
        if (x[0] == apple.getX() && y[0] == apple.getY()) {
            bodyParts++;
            applesEaten++;
            apple.newApple();
        }
    }

    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if (x[i] == x[0] && y[i] == y[0]) {
                running = false;
                break;
            }

            if (x[i] < 0 || x[i] >= GAME_WIDTH) {
                running = false;
                break;
            }

            if (y[i] < 0 || y[i] >= GAME_HEIGHT) {
                running = false;
                break;
            }
        }
    }

    public void drawApple(Graphics g) {
        if (running) {
            g.setColor(new Color(180, 32, 26));
            g.setFont(new Font("Times New Roman", Font.BOLD, (int) (TILE_SIZE * 1.25)));
            g.fillRect(apple.getX() + offset, apple.getY() + offset,
                    TILE_SIZE - 2 * offset, TILE_SIZE - 2 * offset);
            g.setColor(new Color(252, 252, 250));
            g.drawString("Score: " + applesEaten, (GAME_WIDTH - g.getFontMetrics().
                    stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        }
    }

    public void drawSnake(Graphics g) {
        if (running) {
            for (int i = 0; i < bodyParts; i++) {
                g.setColor(new Color(252, 252, 250));
                g.fillRect(x[i] + offset, y[i] + offset, TILE_SIZE - 2 * offset, TILE_SIZE - 2 * offset);
                // g.drawImage(snakeImg, x[i] + offset, y[i] + offset, TILE_SIZE - 2 * offset, TILE_SIZE - 2 * offset, null);
            }
        }
    }
}
