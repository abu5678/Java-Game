package game;

import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {
    Player player;
   // private static final  idle_right = "player/idle/idle_animation/right.GIF";

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
            player.removeAllImages();
            player.addImage(new BodyImage("player/player_move_left.PNG", 4));
        }
        else if (code == KeyEvent.VK_D) {
            player.startWalking(5);
            player.removeAllImages();
            player.addImage(new BodyImage("player/player_move_right.GIF", 4));
        }
        else if (code == KeyEvent.VK_SPACE) {
            player.jump(10);
        }
        else if (code == KeyEvent.VK_J) {
            player.removeAllImages();
            player.addImage(new BodyImage("player/special_attack/special_attack1.GIF", 4));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_A) {
            player.stopWalking();
            player.removeAllImages();
            player.addImage(new BodyImage("player/idle/idle_animation_left.GIF", 4));
        } else if (code == KeyEvent.VK_D) {
            player.stopWalking();
            player.removeAllImages();
            player.addImage(new BodyImage("player/idle/idle_animation_right.GIF", 4));
        }
    }
}