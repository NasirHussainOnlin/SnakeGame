package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameCycle implements ActionListener {
    private final SnakeGame snakeGame;

    public GameCycle(SnakeGame snakeGame) {
        this.snakeGame = snakeGame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snakeGame.update();
    }
}
