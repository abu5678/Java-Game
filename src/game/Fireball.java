package game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Fireball extends StaticBody{
    private static final Shape fireballShape = new BoxShape(2f,0.5f);

    private static final BodyImage image = new BodyImage("data/level/fireball.PNG",15f);

    SolidFixture fireball;

    public Fireball(World w) {
        super(w);
        fireball = new SolidFixture(this,fireballShape);
        addImage(image);
        // setGravityScale(0);
    }

}