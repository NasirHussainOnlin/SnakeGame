package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private final SnakeGame snakeGame;

    public GamePanel(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
        setPreferredSize(new Dimension(SnakeGame.WIDTH, SnakeGame.HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (snakeGame.getDirection() != 'R') {
                            snakeGame.setDirection('L');
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (snakeGame.getDirection() != 'L') {
                            snakeGame.setDirection('R');
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (snakeGame.getDirection() != 'D') {
                            snakeGame.setDirection('U');
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (snakeGame.getDirection() != 'U') {
                            snakeGame.setDirection('D');
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if (snakeGame.isRunning()) {
            g.setColor(Color.RED);
            g.fillOval(snakeGame.getApple().x, snakeGame.getApple().y, SnakeGame.UNIT_SIZE, SnakeGame.UNIT_SIZE);

            for (Point point : snakeGame.getSnake()) {
                g.setColor(Color.GREEN);
                g.fillRect(point.x, point.y, SnakeGame.UNIT_SIZE, SnakeGame.UNIT_SIZE);
            }
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 75);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString(msg, (SnakeGame.WIDTH - metrics.stringWidth(msg)) / 2, SnakeGame.HEIGHT / 2);
    }
}
