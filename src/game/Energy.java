package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Energy extends StaticBody{
    private static final Shape energyShape = new BoxShape(0.3f,0.4f);
    private static final BodyImage image = new BodyImage("data/level/energy.PNG",3f);

    SolidFixture energy;

    public Energy(World w) {
        super(w);
        energy = new SolidFixture(this,energyShape);
        addImage(image);
    }

}