package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {
    Player player;
    Enemy enemy;
    public GameWorld() {
        super();

        // make the ground
        Shape shape1 = new BoxShape(9, 1.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(-11f, -13.5f));
        platform1.addImage(new BodyImage("platform1.PNG", 4));


        Shape shape2 = new BoxShape(8, 1f);
        StaticBody platform2 = new StaticBody(this, shape2);
        platform2.setPosition(new Vec2(10f, -10f));

        // make the character
        player = new Player(this);
        player.setPosition(new Vec2(-12, -10));


        //make enemy
        enemy = new Enemy(this);
        enemy.setPosition(new Vec2(10, -10));

        Collision collision = new Collision(player);
        player.addCollisionListener(collision);

        Gems gem1 = new Gems(this);
        gem1.setPosition(new Vec2(-9,-9));

        GemsPickup gp = new GemsPickup(player);
        player.addCollisionListener(gp);
        //**move** here the rest of the code from Gave.java that
        //populates the World - add platforms, player, etc.
        //(don't add anything related to the view)
    }
    public Player getPlayer(){
        return player;
    }
}
