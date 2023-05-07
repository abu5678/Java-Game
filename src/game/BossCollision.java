package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class BossCollision implements CollisionListener, ActionListener {
    private Player player;
    private Body Boss;
    private static SoundClip swordHit;
    private static BodyImage player_hit = new BodyImage("data/player/dead0.png", 3f);


    static {
        try {
            swordHit = new SoundClip("data/player/SwordHit.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException f) {
        }
    }
    private Boss boss;

    public BossCollision(Player player,Boss boss) {
        this.boss = boss;
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Boss) {
            Boss = collisionEvent.getOtherBody();
            if (boss.isAttack()) {
                player.setHealth(player.getHealth() - 50);
                if (player.getPosition().x < boss.getPosition().x) {
                    player.startWalking(-7);
                }
                if (player.getPosition().x > boss.getPosition().x) {
                    player.startWalking(7);
                }
            }
            if (boss.isUltimate_attack()) {
                player.setHealth(player.getHealth() - 100);
                if (player.getPosition().x < boss.getPosition().x) {
                    player.startWalking(-7);
                }
                if (player.getPosition().x > boss.getPosition().x) {
                    player.startWalking(7);
                }
            }
            if (!boss.isUltimate_attack() && !boss.isAttack() && !player.isUltimate_attack()
            && !player.isNormal_attack() && !player.isSpecial_attack()) {
                player.setHealth(player.getHealth() - 25);
                if (player.getPosition().x < boss.getPosition().x) {
                    player.startWalking(-7);
                }
                if (player.getPosition().x > boss.getPosition().x) {
                    player.startWalking(7);
                }
            }
            player.removeAllImages();
            if (!player.isFacing_right()) {
                AttachedImage am3 = new AttachedImage(player, player_hit, 1, 0, new Vec2(-3f, -0.5f));
            }
            if (player.isFacing_right()){
                AttachedImage am4 = new AttachedImage(player, player_hit, 1, 0, new Vec2(0, -0.5f));
                am4.flipHorizontal();
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}