package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BattleshipGame extends JFrame {
    private final int GRID_SIZE = 5;
    private final JButton[][] playerGrid = new JButton[GRID_SIZE][GRID_SIZE];
    private final JButton[][] enemyGrid = new JButton[GRID_SIZE][GRID_SIZE];
    private final boolean[][] enemyShips = new boolean[GRID_SIZE][GRID_SIZE];
    private final boolean[][] playerShips = new boolean[GRID_SIZE][GRID_SIZE];
    private final Random random = new Random();
    private int playerShipsLeft = 3;
    private int enemyShipsLeft = 3;

    public BattleshipGame() {
        setTitle("Battleship Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        JPanel playerPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        JPanel enemyPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));

        initializeGrid(playerGrid, playerPanel, false);
        initializeGrid(enemyGrid, enemyPanel, true);

        add(playerPanel);
        add(enemyPanel);

        placeShips(playerShips);
        placeShips(enemyShips);
    }

    private void initializeGrid(JButton[][] grid, JPanel panel, boolean isEnemy) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = new JButton();
                grid[i][j].setBackground(Color.BLUE);
                grid[i][j].setEnabled(isEnemy);
                final int x = i, y = j;
                if (isEnemy) {
                    grid[i][j].addActionListener(e -> playerAttack(x, y));
                }
                panel.add(grid[i][j]);
            }
        }
    }

    private void placeShips(boolean[][] ships) {
        int placed = 0;
        while (placed < 3) {
            int x = random.nextInt(GRID_SIZE);
            int y = random.nextInt(GRID_SIZE);
            if (!ships[x][y]) {
                ships[x][y] = true;
                placed++;
            }
        }
    }
}
