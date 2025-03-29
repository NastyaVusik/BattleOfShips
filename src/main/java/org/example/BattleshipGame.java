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

    private void playerAttack(int x, int y) {
        if (enemyShips[x][y]) {
            enemyGrid[x][y].setBackground(Color.RED);
            enemyShips[x][y] = false;
            enemyShipsLeft--;
        } else {
            enemyGrid[x][y].setBackground(Color.GRAY);
        }
        enemyTurn();
        checkWin();
    }


    private void enemyTurn() {
        int x, y;
        do {
            x = random.nextInt(GRID_SIZE);
            y = random.nextInt(GRID_SIZE);
        } while (!playerGrid[x][y].isEnabled());

        if (playerShips[x][y]) {
            playerGrid[x][y].setBackground(Color.RED);
            playerShips[x][y] = false;
            playerShipsLeft--;
        } else {
            playerGrid[x][y].setBackground(Color.GRAY);
        }
        playerGrid[x][y].setEnabled(false);
        checkWin();
    }

    private void checkWin() {
        if (enemyShipsLeft == 0) {
            JOptionPane.showMessageDialog(this, "You win!");
            System.exit(0);
        } else if (playerShipsLeft == 0) {
            JOptionPane.showMessageDialog(this, "You lose!");
            System.exit(0);
        }
    }
}
