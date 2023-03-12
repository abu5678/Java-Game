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
    private static int health = 200;
    SolidFixture P = new SolidFixture(this, playerShape);
    private static int score = 0;
    private boolean facing_right = false;

    private static int fireball_num = 0;

    public static boolean des_fixture = false;
    SolidFixture right;

    public static int getFireball_num() {
        return fireball_num;
    }

    public static void setFireball_num(int fireball_num) {
        Player.fireball_num = fireball_num;
    }

    public Player(World world) {
        super(world);
        addImage(player_image);
        P = new SolidFixture(this, playerShape);
        P.setFriction(30);
        this.health = 200;
        this.fireball_num = 0;
        setAlwaysOutline(true);
    }
    public static int getHealth() {
        return health;
    }

    public static void setHealth(int health) {
        Player.health = health;
    }

    public boolean isFacing_right() {
        return facing_right;
    }

    public SolidFixture getP() {
        return P;
    }

    public void shoot() {
        DynamicBody projectile = new DynamicBody(this.getWorld(), new BoxShape(2f,0.5f));
        Collision c1 = new Collision(this);
        projectile.addCollisionListener(c1);
        AttachedImage am = new AttachedImage(projectile,new BodyImage("level/fireball.PNG", 15),1,0,new Vec2(0,-1));
        projectile.setGravityScale(0);
        if (facing_right == false) {
            projectile.setPosition(new Vec2(this.getPosition().x-5,this.getPosition().y));
            projectile.setLinearVelocity(new Vec2(-30,0));
            am.flipHorizontal();
        }
        else if (facing_right) {
            projectile.setPosition(new Vec2(this.getPosition().x+2,this.getPosition().y));
            projectile.setLinearVelocity(new Vec2(30,0));

        }
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Player.score = score;
    }

    public void setFacing_right(boolean facing_right) {
        this.facing_right = facing_right;
    }
}