package display;

import ImageLoader.GameOverPanel;
import game.Launcher;

import javax.swing.*;
import java.awt.*;

public class GameOverScreen extends JFrame {

    public GameOverScreen() {
        init();
    }

    private void init() {
        GameOverPanel gameOverPanel = new GameOverPanel();
        gameOverPanel.setLayout(null);

        this.setTitle("Game Over");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setContentPane(gameOverPanel);

        setResizable(false);
        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setBounds(50, 410, 350, 50);
        playAgainButton.setFont(new Font("Verdana", Font.BOLD, 22));
        playAgainButton.addActionListener(e -> {
            this.dispose();
            Launcher.main(new String[]{});
        });

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(50, 470, 350, 50);
        quitButton.setFont(new Font("Verdana", Font.BOLD, 22));
        quitButton.addActionListener(event -> System.exit(0));

        gameOverPanel.setOpaque(false);
        gameOverPanel.add(playAgainButton);
        gameOverPanel.add(quitButton);
        gameOverPanel.setVisible(true);
    }

}
