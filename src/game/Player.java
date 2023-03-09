package game;

import city.cs.engine.*;

public class Player extends Walker {
    private static final Shape playerShape = new PolygonShape(
            -2.49f,-1.68f, -0.76f,-1.66f,
            -0.24f,-1.22f, -0.14f,0.62f,
            -1.39f,1.96f, -2.07f,1.91f,
            -2.32f,1.63f, -2.55f,-1.59f
    );

    private static final BodyImage image =
            new BodyImage("player/idle/idle0.png", 4f);

    public Player(World world) {
        super(world, playerShape);
        addImage(image);
    }
}
