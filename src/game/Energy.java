package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Energy extends StaticBody{
    private static final Shape energyShape = new BoxShape(0.3f,0.4f);

    private static final BodyImage image = new BodyImage("level/energy.PNG",1.5f);

    SolidFixture gem = new SolidFixture(this,energyShape);

    public Energy(World w) {
        super(w);
        SolidFixture gem = new SolidFixture(this,energyShape);
        addImage(image);
        // setGravityScale(0);
    }

}