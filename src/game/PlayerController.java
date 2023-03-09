package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {
    Player player;

    public PlayerController(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_A) {
            player.startWalking(-5);
        }
        else if (code == KeyEvent.VK_D) {
            player.startWalking(5);
        }
        else if (code == KeyEvent.VK_SPACE) {
            player.jump(10);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_A) {
            player.stopWalking();
        } else if (code == KeyEvent.VK_D) {
            player.stopWalking();
        }
    }
}