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
    private static BodyImage boss_hit = new BodyImage("data/boss/boss_dying_left.GIF", 4f);
    private static BodyImage boss_dead = new BodyImage("data/boss/boss_dead_left.GIF", 4f);


    Timer dead_timer = new Timer(2000, this);


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
    public void check_boss_hp(){
        if(boss.getHealth() <= 0){
            player.setScore(player.getScore()+5400);
            boss.stopWalking();
            boss.removeAllImages();
            dead_timer.setRepeats(false);
            dead_timer.start();
            if (player.isFacing_right()) {
                AttachedImage am3 = new AttachedImage(boss, boss_hit, 1, 0, new Vec2(0,0));
            }
            if (!player.isFacing_right()){
                AttachedImage am4 = new AttachedImage(boss, boss_hit, 1, 0, new Vec2(0,0));
                am4.flipHorizontal();
            }
        }
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Boss) {
            Boss = collisionEvent.getOtherBody();
            if (boss.isAttack()) {
                player.setHealth(player.getHealth() - 50);
                if (player.getPosition().x < boss.getPosition().x) {
                    player.startWalking(-7);
                    player_knockback();
                }
                if (player.getPosition().x > boss.getPosition().x) {
                    player.startWalking(7);
                    player_knockback();
                }
            }
            if (boss.isUltimate_attack()) {
                player.setHealth(player.getHealth() - 100);
                if (player.getPosition().x < boss.getPosition().x) {
                    player.startWalking(-7);
                    player_knockback();
                }
                if (player.getPosition().x > boss.getPosition().x) {
                    player.startWalking(7);
                    player_knockback();
                }
            }
            if (!boss.isUltimate_attack() && !boss.isAttack() && !player.isUltimate_attack()
            && !player.isNormal_attack() && !player.isSpecial_attack()) {
                player.setHealth(player.getHealth() - 25);
                if (player.getPosition().x < boss.getPosition().x) {
                    player.startWalking(-7);
                    player_knockback();
                }
                if (player.getPosition().x > boss.getPosition().x) {
                    player.startWalking(7);
                    player_knockback();
                }
            }
            if(!boss.isUltimate_attack() && !boss.isAttack() && player.isNormal_attack()){
                boss.setHealth(boss.getHealth() - 50);
                swordHit.play();
                check_boss_hp();
            }
            if(!boss.isUltimate_attack() && !boss.isAttack() && player.isSpecial_attack()){
                boss.setHealth(boss.getHealth() - 150);
                swordHit.play();
                check_boss_hp();
            }
            if(!boss.isUltimate_attack() && !boss.isAttack() && player.isUltimate_attack()){
                boss.setHealth(boss.getHealth() - 250);
                swordHit.play();
                check_boss_hp();
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        boss.removeAllImages();
        if (player.isFacing_right()) {
            AttachedImage am3 = new AttachedImage(boss, boss_dead, 1, 0, new Vec2(0,0));
        }
        if (!player.isFacing_right()){
            AttachedImage am4 = new AttachedImage(boss, boss_dead, 1, 0, new Vec2(0,0));
            am4.flipHorizontal();
        }
        boss.destroy();

    }
}