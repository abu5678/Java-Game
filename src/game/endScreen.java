package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class endScreen {
    private JButton restartButton;
    private JButton quitButton;
    private JPanel mainPanel;

    public endScreen(Game game) {
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.restartGame();
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
