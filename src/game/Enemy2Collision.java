package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Enemy2Collision implements CollisionListener, ActionListener {
    private Player player;
    private Enemy2 enemy2;
    private Body enemy;
    private static BodyImage player_hit = new BodyImage("data/player/dead0.png", 3f);
    private static BodyImage enemy2_dead = new BodyImage("data/enemy2/enemy2_dead.png",4);


    private static SoundClip swordHit;
    static {
        try {
            swordHit = new SoundClip("data/player/SwordHit.wav");
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
        }
    }

    public Enemy2Collision(Player player,Enemy2 enemy2) {
        this.player = player;
        this.enemy2 = enemy2;
    }
    public void player_knockback(){
        player.removeAllImages();
        if (!player.isFacing_right()) {
            AttachedImage am3 = new AttachedImage(player, player_hit, 1, 0, new Vec2(-3f, -0.5f));
        }
        if (player.isFacing_right()){
            AttachedImage am4 = new AttachedImage(player, player_hit, 1, 0, new Vec2(0, -0.5f));
            am4.flipHorizontal();
        }
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Enemy2) {
            enemy = collisionEvent.getOtherBody();
            if (enemy2.isAttack()) {
                player.setHealth(player.getHealth() - 50);
                player.checkhp();
                if (player.getPosition().x < enemy2.getPosition().x) {
                    player.startWalking(-7);
                    player_knockback();
                }
                if (player.getPosition().x > enemy2.getPosition().x) {
                    player.startWalking(7);
                    player_knockback();
                }
            }
            if (player.isNormal_attack()) {
                swordHit.play();
            }
            if (player.isNormal_attack() || player.isSpecial_attack() || player.isUltimate_attack() ||
                    player.isFireball_attack()) {
                enemy.removeAllImages();
                enemy.addImage(enemy2_dead);
                player.setEnemiesKilled(player.getEnemiesKilled() + 1);

                Timer timer = new Timer(0, this);
                timer.setRepeats(false);
                timer.start();
            }
            if (!player.isUltimate_attack() && !player.isNormal_attack() && !player.isSpecial_attack()) {
                player.setHealth(player.getHealth() - 25);
                player.checkhp();
                if (player.getPosition().x < enemy.getPosition().x) {
                    player.startWalking(-7);
                    player_knockback();
                }
                if (player.getPosition().x > enemy.getPosition().x) {
                    player.startWalking(7);
                    player_knockback();
                }
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        player.setScore(player.getScore()+500);
        enemy.removeAllImages();
        enemy.destroy();
    }
}