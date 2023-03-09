package game;

import city.cs.engine.*;

public class Enemy extends Walker {
    private static final Shape enemyShape = new PolygonShape(
            -0.81f,2.0f, 0.6f,1.73f,
            0.89f,0.72f, 0.86f,-0.61f,
            0.67f,-1.52f, -1.85f,-1.58f,
            -1.75f,-0.4f, -1.33f,1.8f
    );

    private static final BodyImage image =
            new BodyImage("enemy/idle/idle0.png", 4f);

    public Enemy(World world) {
        super(world, enemyShape);
        addImage(image);
    }
}