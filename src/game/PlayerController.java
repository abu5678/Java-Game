package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener, ActionListener {
    Player player;
    BodyImage move_left = new BodyImage("player/player_move_left.PNG", 4);
    BodyImage move_right = new BodyImage("player/player_move_right.GIF", 4);

    BodyImage idle_left = new BodyImage("player/idle/idle_animation_left.GIF", 4);

    BodyImage idle_right = new BodyImage("player/idle/idle_animation_right.GIF", 4);

    BodyImage attack_left = new BodyImage("player/special_attack/special_attack1.GIF", 12);

    private static final Shape player_right_hitbox = new PolygonShape(
            1.69f,1.95f, 2.23f,1.66f, 2.5f,-1.67f, 0.79f,-1.68f, 0.27f,0.7f

    );


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
            player.setFacing_right(false);
            player.startWalking(-7);
            player.removeAllImages();
            player.addImage(move_left);
        }
        else if (code == KeyEvent.VK_D) {
            player.setFacing_right(true);
            player.startWalking(7);
            player.removeAllImages();
            AttachedImage am = new AttachedImage(player,move_left,1,0,new Vec2(+3f,0));
            am.flipHorizontal();
        }
        else if (code == KeyEvent.VK_SPACE) {
            player.jump(12);
        }
        else if (code == KeyEvent.VK_K) {
            player.shoot();
        }
        else if (code == KeyEvent.VK_J) {
            player.removeAllImages();
            AttachedImage am3 = new AttachedImage(player,attack_left,1,0,new Vec2(-5f,+1));
            if (player.isFacing_right()== true){
                player.removeAllImages();
                AttachedImage am4 = new AttachedImage(player,attack_left,1,0,new Vec2(-2f,+1));
                am4.flipHorizontal();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_A) {
            player.stopWalking();
            player.removeAllImages();
            player.addImage(idle_left);
        }
        else if (code == KeyEvent.VK_D) {
            player.stopWalking();
            player.removeAllImages();
            AttachedImage am2 = new AttachedImage(player, idle_left, 1, 0, new Vec2(+3f, 0));
            am2.flipHorizontal();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}