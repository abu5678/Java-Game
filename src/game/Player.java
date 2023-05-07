package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Player extends Walker {
    private static BodyImage player_image = new BodyImage("data/player/idle_animation_left.GIF", 4f);
    //private static final Shape playerShape1 = new PolygonShape(
      //      -2.49f, -1.68f, -0.76f, -1.66f,
        //    -0.24f, -1.22f, -0.14f, 0.62f,
          //  -1.39f, 1.96f, -2.07f, 1.91f,
            //-2.32f, 1.63f, -2.55f, -1.59f
    //);
    private static final Shape playerShape = new PolygonShape(
            -1.31f,2.0f, -0.26f,0.32f, -0.3f,-1.64f, -2.57f,-1.66f, -2.41f,1.69f, -1.85f,1.99f);

    private static final Shape normal_attack_Left_Shape = new PolygonShape(
            0.78f,-1.61f, -9.21f,-1.86f, -8.31f,0.72f, -0.31f,2.6f
    );
    private static final Shape normal_attack_Right_Shape = new PolygonShape(
            -3.28f,-1.61f, 6.75f,-1.54f, 5.37f,1.62f, -2.33f,2.46f
    );
    private static final Shape special_attack_Left_Shape = new PolygonShape(
            0.81f,-1.57f, -12.34f,-1.72f, -11.21f,2.68f, -1.0f,2.6f
    );
    private static final Shape special_attack_Right_Shape = new PolygonShape(
            -4.37f,-1.61f, 9.8f,-1.72f, 8.64f,2.1f, -0.55f,3.15f
    );
    private static final Shape ultimate_attack_Left_Shape = new PolygonShape(
            7.52f,-1.25f, -17.3f,-1.37f, -17.3f,3.32f, 3.07f,3.32f
    );
    private static final Shape ultimate_attack_Right_Shape = new PolygonShape(
            -11.94f,-1.6f, 12.19f,-1.37f, 12.07f,2.96f, -8.54f,2.85f
    );
    private static int health = 200;
    SolidFixture normal;
    SolidFixture normal_attack_hb;
    SolidFixture special_attack_hb;
    SolidFixture ultimate_attack_hb;
    private static int score = 0;
    private int enemiesKilled = 0;

    public int getEnemiesKilled() {
        return enemiesKilled;
    }

    public void setEnemiesKilled(int enemiesKilled) {
        this.enemiesKilled = enemiesKilled;
    }

    private boolean facing_right = false;

    private boolean normal_attack = false;
    private boolean special_attack = false;
    private boolean ultimate_attack = false;

    private boolean fireball_attack = false;


    public boolean isUltimate_attack() {
        return ultimate_attack;
    }

    public void setUltimate_attack(boolean ultimate_attack) {
        this.ultimate_attack = ultimate_attack;
    }

    public boolean isSpecial_attack() {
        return special_attack;
    }

    public void setSpecial_attack(boolean special_attack) {
        this.special_attack = special_attack;
    }

    public boolean isNormal_attack() {
        return normal_attack;
    }

    public void setNormal_attack(boolean normal_attack) {
        this.normal_attack = normal_attack;
    }

    private static int fireball_num = 0;

    private static int energy = 0;
    private Game game;



    //public static boolean des_fixture = false;
    //SolidFixture right;

    public Player(World world,Game game) {
        super(world);
        addImage(player_image);
        normal = new SolidFixture(this, playerShape);
        normal.setFriction(30);
        this.game = game;
        this.health = 200;
        this.energy = 0;
        this.fireball_num = 0;
        setAlwaysOutline(true);
    }
    public void changeHitbox() {
        if (this.getFixtureList().contains(normal) && normal_attack && facing_right == false) {
            normal.destroy();
            normal_attack_hb = new SolidFixture(this, normal_attack_Left_Shape);
            normal_attack_hb.setFriction(30);
        }
       else if (this.getFixtureList().contains(normal) && normal_attack && facing_right) {
            normal.destroy();
            normal_attack_hb = new SolidFixture(this, normal_attack_Right_Shape);
            normal_attack_hb.setFriction(30);
        }
       else if (this.getFixtureList().contains(normal) && special_attack && !facing_right) {
            normal.destroy();
            special_attack_hb = new SolidFixture(this,special_attack_Left_Shape);
            special_attack_hb.setFriction(30);
        }
       else if (this.getFixtureList().contains(normal) && special_attack && facing_right) {
            normal.destroy();
            special_attack_hb = new SolidFixture(this, special_attack_Right_Shape);
            special_attack_hb.setFriction(30);
        }
       else if (this.getFixtureList().contains(normal) && ultimate_attack && !facing_right) {
            normal.destroy();
            ultimate_attack_hb = new SolidFixture(this, ultimate_attack_Left_Shape);
            ultimate_attack_hb.setFriction(30);
        }
       else if (this.getFixtureList().contains(normal) && ultimate_attack&& facing_right) {
            normal.destroy();
            ultimate_attack_hb = new SolidFixture(this, ultimate_attack_Right_Shape);
            ultimate_attack_hb.setFriction(30);
        }

    }
    public void resetHitbox(){
        if (this.getFixtureList().contains(normal_attack_hb)){
            normal_attack_hb.destroy();
            normal = new SolidFixture(this,playerShape);
            normal.setFriction(30);
        }
      else   if (this.getFixtureList().contains(special_attack_hb)){
            special_attack_hb.destroy();
            normal = new SolidFixture(this,playerShape);
            normal.setFriction(30);
        }
       else if (this.getFixtureList().contains(ultimate_attack_hb)){
            ultimate_attack_hb.destroy();
            normal = new SolidFixture(this,playerShape);
            normal.setFriction(30);
        }
    }

    public static int getFireball_num() {
        return fireball_num;
    }

    public static void setFireball_num(int fireball_num) {
        Player.fireball_num = fireball_num;
    }

    public static int getEnergy() {
        return energy;
    }

    public static void setEnergy(int energy) {
        Player.energy = energy;
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
        return normal;
    }

    public boolean isFireball_attack() {
        return fireball_attack;
    }

    public void setFireball_attack(boolean fireball_attack) {
        this.fireball_attack = fireball_attack;
    }

    public void shoot() {
        if (fireball_num > 0) {
            DynamicBody projectile = new DynamicBody(this.getWorld(), new BoxShape(2f, 0.5f));
            Collision c1 = new Collision(this);
            projectile.addCollisionListener(c1);
            AttachedImage am = new AttachedImage(projectile, new BodyImage("data/level/fireball.PNG", 15), 1, 0, new Vec2(0, -1));
            projectile.setGravityScale(0);
            if (facing_right == false) {
                projectile.setPosition(new Vec2(this.getPosition().x - 5, this.getPosition().y));
                projectile.setLinearVelocity(new Vec2(-30, 0));
                am.flipHorizontal();
                fireball_num = fireball_num - 1;
            } else if (facing_right) {
                projectile.setPosition(new Vec2(this.getPosition().x + 2, this.getPosition().y));
                projectile.setLinearVelocity(new Vec2(30, 0));
                fireball_num = fireball_num - 1;
            }
        }
    }
    public void checkhp(){
        if(health <= 0){
            game.gameOver();
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