package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Gems extends DynamicBody{
    private static final Shape GemShape = new BoxShape(0.3f,0.4f);

    private static final BodyImage image = new BodyImage("level/gem.PNG",1.5f);

    GhostlyFixture gem = new GhostlyFixture(this,GemShape);

    public Gems(World w) {
        super(w);
        GhostlyFixture gem = new GhostlyFixture(this,GemShape);
        addImage(image);
        setGravityScale(0);
    }

}
