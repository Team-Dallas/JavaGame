package display;

import ImageLoader.Assets;
import ImageLoader.ImagePanel;
import game.Game;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends JFrame {

    private Game game;

    public MenuScreen(Game game) {
        this.game = game;
        init();
    }

    public  final void init() {
        Assets.init();

        JPanel panel = new ImagePanel();
        panel.setLayout(null);

        //Setting the parameters of the frame
        this.setTitle("BULGARIAN TRAFFIC");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setContentPane(panel);

        setResizable(false);
        //START GAME
        JButton startGameButton = new JButton("New Game");
        startGameButton.setBounds(50, 60, 400, 70);
        startGameButton.setFont(new Font("Verdana", Font.BOLD, 22));
        startGameButton.addActionListener(event -> {
            game.start();
            this.dispose();
        });

        //QUIT
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(50, 390, 400, 70);
        quitButton.setFont(new Font("Verdana", Font.BOLD, 22));
        quitButton.addActionListener(event -> {
            System.exit(0);
        });

        //ADD BUTTONS TO JPANEL
        panel.setOpaque(false);
        panel.add(startGameButton);
        panel.add(quitButton);
        panel.setVisible(true);
    }
}
