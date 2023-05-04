package game;

import city.cs.engine.*;

public class Portal extends StaticBody {
    private static final Shape portalShape = new BoxShape(0.3f,1.5f);

    private static final BodyImage image = new BodyImage("data/level/portal.PNG",4f);

    SolidFixture portal;


    public Portal(World w) {
        super(w);
        portal = new SolidFixture(this,portalShape);
        addImage(image);
        //setAlwaysOutline(true);
        // setGravityScale(0);
    }

}