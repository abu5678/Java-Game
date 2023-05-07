package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Boss extends Walker implements StepListener, ActionListener {
    private static final BodyImage idle_image = new BodyImage("data/boss/boss_idle_left.GIF", 8f);
    private static final BodyImage attack_image = new BodyImage("data/boss/boss_attack_left.GIF", 12f);
    private static final BodyImage ultimate_image = new BodyImage("data/boss/boss_ultimate_left2.GIF", 24f);


    private static final Shape enemyShape = new PolygonShape(
            -0.81f, 2.0f, 0.6f, 1.73f,
            0.89f, 0.72f, 0.86f, -0.61f,
            0.67f, -1.52f, -1.85f, -1.58f,
            -1.75f, -0.4f, -1.33f, 1.8f
    );

    private static final Shape attack_left_hb = new PolygonShape(
            0.03f,-1.31f, -5.11f,-1.31f, -5.11f,4.35f, -0.37f,4.29f

    );
    private static final Shape attack_right_hb = new PolygonShape(
            1.01f,-1.5f, 5.17f,-1.5f, 5.17f,3.12f, 0.77f,3.06f

    );

    public boolean isAttack() {
        return attack;
    }

    public boolean isUltimate_attack() {
        return ultimate_attack;
    }

    private static final Shape ultimate_left_hb = new PolygonShape(
            9.41f,-0.54f, -9.95f,-0.7f, -9.9f,3.43f, 8.98f,3.38f

    );
    private static final Shape ultimate_right_hb = new PolygonShape(
            -9.3f,-0.54f, 9.46f,-0.7f, 9.46f,3.43f, -9.3f,3.38f

    );
    private boolean attack;
    private boolean ultimate_attack;
    Timer attack_desicion_timer = new Timer(8400, this);//8400

    Timer attack_animation_timer = new Timer(2800, this);

    Timer ultimate_animation_timer = new Timer(8610, this);

    boolean attack_anim = false;

    private boolean facing_right;
    private boolean facing_left;
    SolidFixture boss_idle;
    SolidFixture boss_attack;
    SolidFixture boss_ultimate;
    Player player;
    int no_of_attacks = 0;
    int health = 350;

    public int getHealth() {
        return health;
    }

    public Boss(World world, Player player) {
        super(world);
        this.player = player;
        addImage(idle_image);
        world.addStepListener(this);
        startWalking(4);
        boss_idle = new SolidFixture(this, enemyShape);
        boss_idle.setFriction(30);
        setAlwaysOutline(true);
        attack_desicion_timer.start();
    }


    @Override
    public void preStep(StepEvent stepEvent) {
        if (getPosition().x > player.getPosition().x) {
            facing_right = false;
            facing_left = true;
            if(!attack && !ultimate_attack) {
                this.removeAllImages();
                addImage(idle_image);
            }
            startWalking(-3);
        }
        if (getPosition().x < player.getPosition().x) {
            facing_right = true;
            facing_left = false;
            if(!attack && !ultimate_attack) {
                this.removeAllImages();
                AttachedImage am2 = new AttachedImage(this, idle_image, 1, 0, new Vec2(0, 0));
                am2.flipHorizontal();
            }
            startWalking(3);

        }
        if (getPosition().x < player.getPosition().x + 2 &&
                getPosition().x > player.getPosition().x - 2) {
            stopWalking();
        }
    }

    public void change_img() {
       // attack_desicion_timer.stop();
       // ultimate_desicion_timer.stop();
        this.removeAllImages();
        if (ultimate_attack) {
            attack = false;
            //attack_desicion_timer.stop();
           // attack_animation_timer.stop();
          //  ultimate_desicion_timer.restart();
            AttachedImage am7 = new AttachedImage(this, ultimate_image, 1, 0, new Vec2(0, 9));
            if (facing_right) {
                this.removeAllImages();
                AttachedImage am8 = new AttachedImage(this, ultimate_image, 1, 0, new Vec2(0, 9));
                am8.flipHorizontal();
            }
            if (!ultimate_animation_timer.isRunning()) {
                ultimate_animation_timer.setRepeats(false);
                ultimate_animation_timer.start();
                attack_anim = true;

            }
            else{
                ultimate_animation_timer.restart();
                attack_anim = true;

            }
        }
        // code
        if (attack) {
            ultimate_attack = false;
           // attack_desicion_timer.restart();
            AttachedImage am5 = new AttachedImage(this, attack_image, 1, 0, new Vec2(0, 3));
            if (facing_right) {
                this.removeAllImages();
                AttachedImage am6 = new AttachedImage(this, attack_image, 1, 0, new Vec2(0, 3));
                am6.flipHorizontal();
            }
            if (!attack_animation_timer.isRunning()) {
                attack_animation_timer.setRepeats(false);
                attack_animation_timer.start();
                attack_anim = true;
            }
            else{
                attack_animation_timer.restart();
                attack_anim = true;
            }
        }
    }

    public void change_hb() {
        if (this.getFixtureList().contains(boss_idle) && attack && facing_left) {
            boss_idle.destroy();
            boss_attack = new SolidFixture(this, attack_left_hb);
            boss_attack.setFriction(30);
        }
        if (this.getFixtureList().contains(boss_idle) && attack && facing_right) {
            boss_idle.destroy();
            boss_attack = new SolidFixture(this, attack_right_hb);
            boss_attack.setFriction(30);
        }
        if (this.getFixtureList().contains(boss_idle) && ultimate_attack && facing_left) {
            boss_idle.destroy();
            boss_ultimate = new SolidFixture(this, ultimate_left_hb);
            boss_ultimate.setFriction(30);
        }
        if (this.getFixtureList().contains(boss_idle) && ultimate_attack && facing_right) {
            boss_idle.destroy();
            boss_ultimate = new SolidFixture(this, ultimate_right_hb);
            boss_ultimate.setFriction(30);
        }
    }
    public void resetHitbox(){
        if (this.getFixtureList().contains(boss_attack)){
            boss_attack.destroy();
            boss_idle = new SolidFixture(this, enemyShape);
            boss_idle.setFriction(30);
        }
        if (this.getFixtureList().contains(boss_ultimate)){
            boss_ultimate.destroy();
            boss_idle = new SolidFixture(this, enemyShape);
            boss_idle.setFriction(30);
        }

    }
    @Override
    public void postStep(StepEvent stepEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (attack_anim){
            attack_anim = false;
            ultimate_attack = false;
            attack = false;
            this.removeAllImages();
            resetHitbox();
            if (facing_left) {
                this.addImage(idle_image);
            }
            if (facing_right) {
                AttachedImage am2 = new AttachedImage(this, idle_image, 1, 0, new Vec2(0f, 0));
                am2.flipHorizontal();
            }

        }
        else {
            no_of_attacks++;
            switch (no_of_attacks) {
                case 1:
                    attack = true;
                    change_hb();
                    change_img();
                    break;
                case 2:
                    attack = true;
                    change_hb();
                    change_img();
                    break;
                case 3:
                    attack = true;
                    change_hb();
                    change_img();
                    break;
                case 4:
                    ultimate_attack = true;
                    no_of_attacks = 0;
                    change_hb();
                    change_img();
                    break;
            }
        }
        //if (attack_desicion_timer.getDelay() == 5600) {7
        /*
        if(!ultimate_attack && no_of_attacks == 3) {
            ultimate_attack = true;
            change_img();
        }
        else if(!attack && !ultimate_over_timer.isRunning()) {
                attack = true;
                change_img();
            }

        else if (attack && !ultimate_over_timer.isRunning()) {
            this.removeAllImages();
            this.addImage(idle_image);
            attack = false;
            no_of_attacks += 1;
            System.out.println(no_of_attacks);
            attack_over_timer.stop();
        }

        else if (ultimate_attack) {
            this.removeAllImages();
            this.addImage(idle_image);
            no_of_attacks = 0;
            ultimate_attack = false;
            ultimate_over_timer.stop();
            attack_desicion_timer.start();
        }
        */

    }
}