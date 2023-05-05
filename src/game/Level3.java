package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Level3 extends GameLevel {

    public Level3(Game game) {
        super(game);

        BodyImage platform1_image = new BodyImage("data/level/dungeon_platform1.PNG", 4);
        BodyImage platform2_image = new BodyImage("data/level/dungeon_platform3.PNG", 4);

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

        Shape shape3 = new BoxShape(5.5f, 1.5f);
        StaticBody platform3 = new StaticBody(this, shape3);
        platform3.setPosition(new Vec2(-2f, -6f));
        platform3.addImage(platform2_image);
        platform3.setAlwaysOutline(true);

        Shape shape4 = new BoxShape(5.5f, 1.5f);
        StaticBody platform4 = new StaticBody(this, shape4);
        platform4.setPosition(new Vec2(15f, 3));
        platform4.addImage(platform2_image);
        platform4.setAlwaysOutline(true);

        Shape shape5 = new BoxShape(5.5f, 1.5f);
        StaticBody platform5 = new StaticBody(this, shape5);
        platform5.setPosition(new Vec2(33f, -6f));
        platform5.addImage(platform2_image);
        platform5.setAlwaysOutline(true);

        Boss boss = new Boss(this,getPlayer());
        boss.setPosition(new Vec2(35, -11));

    }

    @Override
    public boolean isComplete() {
        return false;
    }
}