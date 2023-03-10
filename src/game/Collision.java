package game;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

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
        if (collisionEvent.getOtherBody() instanceof Enemy){
            enemy = collisionEvent.getOtherBody();
            enemy.removeAllImages();
          //  enemy.addImage(new BodyImage("enemy/enemy_dead5.GIF",3 ));
          //  Timer timer = new Timer(100,this);
          //  timer.setRepeats(false);
        //    timer.start();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        enemy.destroy();
    }
}