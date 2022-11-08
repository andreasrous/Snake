package main;

import scenes.GameScene;
import scenes.MenuScene;

import java.awt.*;

import static constants.Constants.*;

public class Game implements Runnable {

    private int state;
    private boolean running;
    private Thread gameThread;
    private MenuScene menuScene;
    private GameScene gameScene;
    private GamePanel gamePanel;
    private GameFrame gameFrame;

    public Game() {
        running = true;
        state = MENU_SCENE;
        menuScene = new MenuScene(this);
        gameScene = new GameScene(this);
        gamePanel = new GamePanel(this);
        gameFrame = new GameFrame(gamePanel);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        if (state == MENU_SCENE)
            menuScene.update();
        else
            gameScene.update();
    }

    public void render(Graphics g) {
        if (state == MENU_SCENE)
            menuScene.render(g);
        else
            gameScene.render(g);
    }

    public void windowFocusLost() {
        if (state == GAME_SCENE)
            gameScene.reset();
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void exitGame() {
        running = false;
        gameFrame.dispose();
    }

    public void deleteGameScene() {
        gameScene = null;
    }

    public void createGameScene() {
        gameScene = new GameScene(this);
    }

    public MenuScene getMenuScene() {
        return menuScene;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    @Override
    public void run() {
        int frames = 0;
        double delta = 0;
        double timePerFrame = 1000000000.0 / FPS;
        long previousTime = System.nanoTime();
        double lastCheck = System.currentTimeMillis();

        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (delta >= 1) {
                update();
                gamePanel.repaint();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                System.out.println("FPS: " + frames);
                lastCheck += 1000;
                frames = 0;
            }
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
