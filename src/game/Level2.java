package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level2 extends GameLevel {

    private int numOfEnemies = 0;

    public Level2(Game game) {
        super(game);

        BodyImage platform1_image = new BodyImage("data/level/dungeon_platform1.PNG", 4);

        Shape shape1 = new BoxShape(19.5f, 2f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(1f, -15f));
        platform1.addImage(platform1_image);
        platform1.setAlwaysOutline(true);
    }

    @Override
    public boolean isComplete() {
        if (getPlayer().getEnemiesKilled() == 0){
            return true;
        }
        else
            return false;
    }

}