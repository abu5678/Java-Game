package game;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Collision implements CollisionListener, ActionListener {
    private Player player;
    private Body enemy;

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
                if (player.isNormal_attack() || player.isSpecial_attack() || player.isUltimate_attack()||
                        player.isFireball_attack()) {
                    enemy.removeAllImages();
                    enemy.addImage(new BodyImage("data/enemy/enemy_dead5.GIF", 3));

                    Timer timer = new Timer(100, this);
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