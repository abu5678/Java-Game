package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level2 extends GameLevel {

    private int numOfEnemies = 0;

    public Level2(Game game) {
        super(game);

        BodyImage platform1_image = new BodyImage("data/level/platform1.PNG", 4);

        Shape shape1 = new BoxShape(8, 1.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(-19f, -15f));
        platform1.addImage(platform1_image);
    }

    @Override
    public boolean isComplete() {
        return false;
    }
}