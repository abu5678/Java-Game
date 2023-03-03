package game;

import city.cs.engine.*;

public class Player extends DynamicBody {
    private static final Shape playerShape = new BoxShape(1,2);

    private static final BodyImage image =
            new BodyImage("data/student.png", 4f);

    public Player(World world) {
        super(world, playerShape);
        addImage(image);
    }
}
