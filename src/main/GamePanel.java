package main;

import inputs.KeyHandler;
import inputs.MouseHandler;

import javax.swing.*;
import java.awt.*;

import static constants.Constants.*;

public class GamePanel extends JPanel {

    private Game game;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;

    public GamePanel(Game game) {
        this.game = game;
        this.keyHandler = new KeyHandler(game);
        this.mouseHandler = new MouseHandler(game);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setBackground(new Color(45, 42, 46));
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocus();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
}
