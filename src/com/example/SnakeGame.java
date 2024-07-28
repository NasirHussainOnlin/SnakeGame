package com.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JFrame {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int DELAY = 75;

    private final ArrayList<Point> snake = new ArrayList<>();
    private Point apple;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;

    public SnakeGame() {
        add(new GamePanel(this));
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        startGame();
    }

    public void startGame() {
        snake.clear();
        snake.add(new Point(0, 0));
        direction = 'R';
        running = true;
        spawnApple();
        timer = new Timer(DELAY, new GameCycle(this));
        timer.start();
    }

    public void spawnApple() {
        Random random = new Random();
        int x = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int y = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        apple = new Point(x, y);
    }

    public void move() {
        Point head = new Point(snake.get(0));
        switch (direction) {
            case 'U':
                head.y -= UNIT_SIZE;
                break;
            case 'D':
                head.y += UNIT_SIZE;
                break;
            case 'L':
                head.x -= UNIT_SIZE;
                break;
            case 'R':
                head.x += UNIT_SIZE;
                break;
        }
        snake.add(0, head);
        if (head.equals(apple)) {
            spawnApple();
        } else {
            snake.remove(snake.size() - 1);
        }
    }

    public void checkCollisions() {
        Point head = snake.get(0);

        for (int i = 1; i < snake.size(); i++) {
            if (head.equals(snake.get(i))) {
                running = false;
                break;
            }
        }

        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void update() {
        if (running) {
            move();
            checkCollisions();
        }
        repaint();
    }

    public ArrayList<Point> getSnake() {
        return snake;
    }

    public Point getApple() {
        return apple;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public boolean isRunning() {
        return running;
    }
}
