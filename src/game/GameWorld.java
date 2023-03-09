package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {
    Player player;
    public GameWorld() {
        super();

        // make the ground
        Shape shape = new BoxShape(11, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // make the character
        player = new Player(this);
        player.setPosition(new Vec2(7, -9));


        //**move** here the rest of the code from Gave.java that
        //populates the World - add platforms, player, etc.
        //(don't add anything related to the view)
    }
    public Player getPlayer(){
        return player;
    }
}
