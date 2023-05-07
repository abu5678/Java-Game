package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level2 extends GameLevel implements ActionListener {

    private int numOfEnemies = 0;
    Timer enemy_spawn = new Timer(8000, this);


    public Level2(Game game) {
        super(game);
        enemy_spawn.start();
        BodyImage platform1_image = new BodyImage("data/level/dungeon_platform1.PNG", 4);

        Shape shape1 = new BoxShape(19.5f, 2f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(1f, -15f));
        platform1.addImage(platform1_image);
        platform1.setAlwaysOutline(true);

        Shape shape2 = new BoxShape(19.5f, 2f);
        StaticBody platform2 = new StaticBody(this, shape2);
        platform2.setPosition(new Vec2(30f, -15f));
        platform2.addImage(platform1_image);
        platform2.setAlwaysOutline(true);

        Portal portal1 = new Portal(this);
        portal1.setPosition(new Vec2(44,-10));
        getPlayer().addCollisionListener(new PortalCollision(this, game));

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (getPlayer().getEnemiesKilled()){
            case 0:
                Enemy2 enemy1 = new Enemy2(this,getPlayer());
                enemy1.setPosition(new Vec2(33, -11));
                Enemy2Collision enemy2_collision = new Enemy2Collision(getPlayer(),enemy1);
                getPlayer().addCollisionListener(enemy2_collision);
                break;
            case 1:
                Enemy2 enemy2 = new Enemy2(this,getPlayer());
                enemy2.setPosition(new Vec2(7, -11));
                enemy2_collision = new Enemy2Collision(getPlayer(),enemy2);
                getPlayer().addCollisionListener(enemy2_collision);
                break;
            case 2:
                Enemy2 enemy3 = new Enemy2(this,getPlayer());
                enemy3.setPosition(new Vec2(33, -11));
                enemy2_collision = new Enemy2Collision(getPlayer(),enemy3);
                getPlayer().addCollisionListener(enemy2_collision);
                break;
            case 3:
                Enemy2 enemy4 = new Enemy2(this,getPlayer());
                enemy4.setPosition(new Vec2(7, -11));
                enemy2_collision = new Enemy2Collision(getPlayer(),enemy4);
                getPlayer().addCollisionListener(enemy2_collision);
                break;
        }
    }

    @Override
    public boolean isComplete() {
        if (getPlayer().getEnemiesKilled() >= 4){
            return true;
        }
        else
            return false;
    }

}