package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {
    Player player;
    BodyImage platform1_image = new BodyImage("data/level/platform1.PNG", 4);
    public GameWorld() {
        super();
        player = new Player(this);
        player.setPosition(new Vec2(-12, -10));

        Shape shape1 = new BoxShape(8, 1.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(-19f, -15f));
        platform1.addImage(platform1_image);

        Shape shape2 = new BoxShape(6, 1f);
        StaticBody platform2 = new StaticBody(this, shape2);
        platform2.setPosition(new Vec2(-10f, -5f));
        AttachedImage am1 = new AttachedImage(platform2,platform1_image,0.8f,0,new Vec2(0,0));

        moving_platform moving_platform = new moving_platform(this);
        moving_platform.setPosition(new Vec2(0,0));
        AttachedImage am2 = new AttachedImage(moving_platform,platform1_image,0.4f,0,new Vec2(0,0));

        Shape shape3 = new BoxShape(6, 1f);
        StaticBody platform3 = new StaticBody(this, shape3);
        platform3.setPosition(new Vec2(10f, 13f));
        AttachedImage am3 = new AttachedImage(platform3,platform1_image,0.8f,0,new Vec2(0,0));

        Shape shape4 = new BoxShape(6, 1f);
        StaticBody platform4 = new StaticBody(this, shape4);
        platform4.setPosition(new Vec2(25f, 5f));
        AttachedImage am4 = new AttachedImage(platform4,platform1_image,0.8f,0,new Vec2(0,0));

        Shape shape5 = new BoxShape(6, 1f);
        StaticBody platform5 = new StaticBody(this, shape5);
        platform5.setPosition(new Vec2(50f, 5f));
        AttachedImage am5 = new AttachedImage(platform5,platform1_image,0.8f,0,new Vec2(0,0));

        Shape shape6 = new BoxShape(6, 1f);
        StaticBody platform6 = new StaticBody(this, shape6);
        platform6.setPosition(new Vec2(75f, -3f));
        AttachedImage am6 = new AttachedImage(platform6,platform1_image,0.8f,0,new Vec2(0,0));

        Shape shape7 = new BoxShape(6, 1f);
        StaticBody platform7 = new StaticBody(this, shape7);
        platform7.setPosition(new Vec2(87.5f, -3f));
        AttachedImage am7 = new AttachedImage(platform7,platform1_image,0.8f,0,new Vec2(0,0));


        Enemy enemy1 = new Enemy(this);
        enemy1.setPosition(new Vec2(13, 15));

        Enemy enemy2 = new Enemy(this);
        enemy2.setPosition(new Vec2(76,-1));

        Enemy enemy3 = new Enemy(this);
        enemy3.setPosition(new Vec2(90f,-1));

        Collision enemy_collision = new Collision(player);
        player.addCollisionListener(enemy_collision);

        Gems gem1 = new Gems(this);
        gem1.setPosition(new Vec2(-19,-11));

        Gems gem2 = new Gems(this);
        gem2.setPosition(new Vec2(-7,-1.5f));

        Gems gem3 = new Gems(this);
        gem3.setPosition(new Vec2(81,-1));

        Energy energy1 = new Energy(this);
        energy1.setPosition(new Vec2(27,9));

        Fireball fireball1 = new Fireball(this);
        fireball1.setPosition(new Vec2(52,8));

        ItemPickup items = new ItemPickup(player);
        player.addCollisionListener(items);

    }
    public Player getPlayer(){
        return player;
    }
}
