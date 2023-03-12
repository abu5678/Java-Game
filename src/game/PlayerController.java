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

    BodyImage normal_attack_left = new BodyImage("player/normal_attack_left.GIF", 12);

    BodyImage ultimate_attack_left = new BodyImage("player/ultimate_attack_left.GIF", 12);

    BodyImage attack_left = new BodyImage("player/special_attack/special_attack.GIF", 12);

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
            player.jump(15);
        }
        else if (code == KeyEvent.VK_K) {
            player.shoot();
        }
        else if (code == KeyEvent.VK_P) {
            player.setNormal_attack(true);
            player.removeAllImages();
            AttachedImage am5 = new AttachedImage(player,normal_attack_left,1,0,new Vec2(-5f,+1));
            if (player.isFacing_right()== true){
                player.removeAllImages();
                AttachedImage am6 = new AttachedImage(player,normal_attack_left,1,0,new Vec2(-2f,+1));
                am6.flipHorizontal();
            }
        }
        else if (code == KeyEvent.VK_L) {
            if (player.getEnergy()>= 100) {
                player.setUltimate_attack(true);
                player.removeAllImages();
                AttachedImage am7 = new AttachedImage(player, ultimate_attack_left, 1, 0, new Vec2(-7f, +2));
                if (player.isFacing_right() == true) {
                    player.removeAllImages();
                    AttachedImage am8 = new AttachedImage(player, ultimate_attack_left, 1, 0, new Vec2(-4, +2));
                    am8.flipHorizontal();
                }
            }
        }

        else if (code == KeyEvent.VK_J) {
            if(player.getEnergy()>=75) {
                player.setSpecial_attack(true);
                player.removeAllImages();
                AttachedImage am3 = new AttachedImage(player, attack_left, 1, 0, new Vec2(-5f, +1));
                if (player.isFacing_right() == true) {
                    player.removeAllImages();
                    AttachedImage am4 = new AttachedImage(player, attack_left, 1, 0, new Vec2(-2f, +1));
                    am4.flipHorizontal();
                }
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
        else if (code == KeyEvent.VK_P) {
            player.setEnergy(player.getEnergy() - 100);
            player.setNormal_attack(false);
            player.removeAllImages();
            player.addImage(idle_left);
            if (player.isFacing_right() == true) {
                player.removeAllImages();
                AttachedImage am3 = new AttachedImage(player, idle_left, 1, 0, new Vec2(+3f, 0));
                am3.flipHorizontal();
            }
        }
            else if (code == KeyEvent.VK_J) {
            player.setEnergy(player.getEnergy() - 75);
            player.setSpecial_attack(false);
            player.removeAllImages();
            player.addImage(idle_left);
            if (player.isFacing_right() == true) {
                player.removeAllImages();
                AttachedImage am3 = new AttachedImage(player, idle_left, 1, 0, new Vec2(+3f, 0));
                am3.flipHorizontal();
            }
        }
            else if (code == KeyEvent.VK_L) {
            player.setUltimate_attack(false);
            player.removeAllImages();
            player.addImage(idle_left);
            if (player.isFacing_right() == true) {
                player.removeAllImages();
                AttachedImage am3 = new AttachedImage(player, idle_left, 1, 0, new Vec2(+3f, 0));
                am3.flipHorizontal();
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}