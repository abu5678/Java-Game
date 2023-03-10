package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Player extends Walker {
    private static BodyImage player_image = new BodyImage("player/idle/idle_animation_left.GIF", 4f);
    private static final Shape playerShape = new PolygonShape(
            -2.49f,-1.68f, -0.76f,-1.66f,
            -0.24f,-1.22f, -0.14f,0.62f,
            -1.39f,1.96f, -2.07f,1.91f,
            -2.32f,1.63f, -2.55f,-1.59f
    );
    private static final Shape player_right_hitbox = new PolygonShape(
            1.69f,1.95f, 2.23f,1.66f, 2.5f,-1.67f, 0.79f,-1.68f, 0.27f,0.7f
    );
    SolidFixture right = new SolidFixture(this,player_right_hitbox);
    SolidFixture P = new SolidFixture(this,playerShape);
    private static int score = 0;
    public Player(World world) {
        super(world);
        addImage(player_image);
        P = new SolidFixture(this,playerShape);
        P.setFriction(30);
        setAlwaysOutline(true);
    }

    public void destroyFixture() {
        if (!this.getFixtureList().contains(right)) {
            P.destroy();
            SolidFixture right = new SolidFixture(this, player_right_hitbox);
        }
    }
    public void facing_right_hitbox(){
        SolidFixture P = new SolidFixture(this,player_right_hitbox);
    }
    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Player.score = score;
    }

    public void GetPosition(){
        Vec2 starting_pos = this.getPosition();
    }


}
