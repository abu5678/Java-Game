package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Gems extends StaticBody{
    private static final Shape GemShape = new BoxShape(0.3f,0.4f);

    private static final BodyImage image = new BodyImage("data/level/gem.PNG",1.5f);

    SolidFixture gem;

    public Gems(World w) {
        super(w);
        gem = new SolidFixture(this,GemShape);
        addImage(image);
    }

}
