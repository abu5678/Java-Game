package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {
    Player player;
    Enemy enemy;
    BodyImage platform1_image = new BodyImage("platform1.PNG", 4);
    public GameWorld() {
        super();
        player = new Player(this);
        player.setPosition(new Vec2(-12, -10));

        // make the ground
        Shape shape1 = new BoxShape(8, 1.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(-19f, -15f));
        platform1.addImage(platform1_image);


        Shape shape2 = new BoxShape(6, 1f);
        StaticBody platform3 = new StaticBody(this, shape2);
        platform3.setPosition(new Vec2(10f, 13f));
        AttachedImage am1 = new AttachedImage(platform3,platform1_image,0.8f,0,new Vec2(0,0));

        Shape shape3 = new BoxShape(6, 1f);
        StaticBody platform2 = new StaticBody(this, shape2);
        platform2.setPosition(new Vec2(-10f, -5f));
        AttachedImage am3 = new AttachedImage(platform2,platform1_image,0.8f,0,new Vec2(0,0));

        moving_platform moving_platform = new moving_platform(this);
        moving_platform.setPosition(new Vec2(0,0));
        AttachedImage am2 = new AttachedImage(moving_platform,platform1_image,0.4f,0,new Vec2(0,0));

        // moving_platform.addImage(platform1_image);
        // make the character



        //make enemy
        enemy = new Enemy(this);
        enemy.setPosition(new Vec2(13, 15));

        Collision collision = new Collision(player);
        player.addCollisionListener(collision);

        Gems gem1 = new Gems(this);
        gem1.setPosition(new Vec2(-19,-11));

        Gems gem2 = new Gems(this);
        gem2.setPosition(new Vec2(-7,-1.5f));


        ItemPickup gems = new ItemPickup(player);
        player.addCollisionListener(gems);

        //**move** here the rest of the code from Gave.java that
        //populates the World - add platforms, player, etc.
        //(don't add anything related to the view)
    }
    public Player getPlayer(){
        return player;
    }
}
