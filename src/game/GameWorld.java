package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {
    Player player;
    public GameWorld() {
        super();

        // make the ground
        Shape shape1 = new BoxShape(9, 1.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(-11f, -13.5f));

        Shape shape2 = new BoxShape(9, 1.5f);
        StaticBody platform2 = new StaticBody(this, shape2);
        platform2.setPosition(new Vec2(-11f, -13.5f));


        // make the character
        player = new Player(this);
        player.setPosition(new Vec2(-12, -10));


        //**move** here the rest of the code from Gave.java that
        //populates the World - add platforms, player, etc.
        //(don't add anything related to the view)
    }
    public Player getPlayer(){
        return player;
    }
}
