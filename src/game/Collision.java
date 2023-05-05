package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Collision implements CollisionListener, ActionListener {
    private Player player;
    private Body enemy;
    private static SoundClip swordHit;
    static {
        try {
            swordHit = new SoundClip("data/player/SwordHit.wav");
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
        }
    }

    public Collision(Player player) {
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(!(collisionEvent.getReportingBody() instanceof Player)) {
            collisionEvent.getReportingBody().destroy();
        }

            if (collisionEvent.getOtherBody() instanceof Enemy) {
                enemy = collisionEvent.getOtherBody();
                if (player.isNormal_attack()){
                    swordHit.play();
                }
                if (player.isNormal_attack() || player.isSpecial_attack() || player.isUltimate_attack()||
                        player.isFireball_attack()) {
                    enemy.removeAllImages();
                    enemy.addImage(new BodyImage("data/enemy/enemy_dead5.GIF", 3));
                    player.setEnemiesKilled(player.getEnemiesKilled()+1);

                    Timer timer = new Timer(1000, this);
                    timer.setRepeats(false);
                    timer.start();



                }
                else if(collisionEvent.getReportingBody() instanceof Player){
                    player.startWalking(-7);
                    player.setHealth(player.getHealth()-25);
                    }
                }

            }


    @Override
    public void actionPerformed(ActionEvent e) {
        enemy.destroy();
        player.setScore(player.getScore()+500);
        player.setFireball_attack(false);
    }
}