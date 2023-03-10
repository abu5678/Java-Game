package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Player extends Walker {
    private static BodyImage player_image = new BodyImage("player/idle/idle_animation_left.GIF", 4f);
    private static final Shape playerShape = new PolygonShape(
            -2.49f, -1.68f, -0.76f, -1.66f,
            -0.24f, -1.22f, -0.14f, 0.62f,
            -1.39f, 1.96f, -2.07f, 1.91f,
            -2.32f, 1.63f, -2.55f, -1.59f
    );
    private static int health = 5;
    SolidFixture P = new SolidFixture(this, playerShape);
    private static int score = 0;
    private boolean facing_right = false;

    public Player(World world) {
        super(world);
        addImage(player_image);
        P = new SolidFixture(this, playerShape);
        P.setFriction(30);
        this.health = 200;
        setAlwaysOutline(true);
    }

    public static int getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        Player.health = health;
    }

    public void shoot() {
        DynamicBody projectile = new DynamicBody(this.getWorld(), new CircleShape(0.5f));
        Collision c1 = new Collision(this);
        projectile.addCollisionListener(c1);
        if (facing_right == false) {
            projectile.setPosition(new Vec2(this.getPosition().x+3,this.getPosition().y));
            projectile.setLinearVelocity(new Vec2(30,0));
            //projectile.addImage()
        }
        else if (facing_right == true) {
            projectile.setPosition(new Vec2(this.getPosition().x-3,this.getPosition().y));
            projectile.setLinearVelocity(new Vec2(30,0));
        }
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Player.score = score;
    }

    public void GetPosition() {
        Vec2 starting_pos = this.getPosition();
    }


    public void setFacing_right(boolean facing_right) {
        this.facing_right = facing_right;
    }
}