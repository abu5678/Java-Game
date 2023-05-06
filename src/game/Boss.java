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
    private static final BodyImage ultimate_image = new BodyImage("data/boss/boss_ultimate_left.GIF", 12f);


    private static final Shape enemyShape = new PolygonShape(
            -0.81f, 2.0f, 0.6f, 1.73f,
            0.89f, 0.72f, 0.86f, -0.61f,
            0.67f, -1.52f, -1.85f, -1.58f,
            -1.75f, -0.4f, -1.33f, 1.8f
    );
    private boolean attack;
    private boolean ultimate_attack;
    Timer attack_desicion_timer = new Timer(8400, this);
    Timer attack_warning_timer = new Timer(5600, this);

    Timer attack_over_timer = new Timer(2800, this);

    Timer ultimate_desicion_timer = new Timer(32800, this);
    Timer ultimate_warning_timer = new Timer(5600, this);

    Timer ultimate_over_timer = new Timer(8200, this);

    private boolean facing_right;
    private boolean facing_left;
    SolidFixture boss_idle;
    SolidFixture boss_attack;
    SolidFixture boss_ultimate;
    Player player;

    public Boss(World world, Player player) {
        super(world);
        this.player = player;
        addImage(idle_image);
        world.addStepListener(this);
        startWalking(4);
        boss_idle = new SolidFixture(this, enemyShape);
        boss_idle.setFriction(30);
        setAlwaysOutline(true);
        attack_desicion_timer.setRepeats(false);
        attack_desicion_timer.start();
    }


    @Override
    public void preStep(StepEvent stepEvent) {
       // attack_over_timer.setRepeats(false);
       // attack_over_timer.setInitialDelay(2800);
        //attack_over_timer.start();
       // attack_warning_timer.setRepeats(true);
       // attack_warning_timer.start();
        if (getPosition().x > player.getPosition().x) {
            facing_right = false;
            facing_left = true;
            startWalking(-4);
        }
        if (getPosition().x < player.getPosition().x) {
            facing_right = true;
            facing_left = false;
            startWalking(4);
        }
        if (getPosition().x < player.getPosition().x + 2 &&
                getPosition().x > player.getPosition().x - 2) {
            stopWalking();
        }
    }

    public void change_img() {
       // attack_desicion_timer.stop();
        this.removeAllImages();
        if (attack) {
            attack_desicion_timer.restart();
            AttachedImage am5 = new AttachedImage(this, attack_image, 1, 0, new Vec2(0, 3));
            if (facing_right) {
                this.removeAllImages();
                AttachedImage am6 = new AttachedImage(this, attack_image, 1, 0, new Vec2(0, 3));
                am6.flipHorizontal();
            }
            if (!attack_over_timer.isRunning()) {
                attack_over_timer.setRepeats(false);
                attack_over_timer.start();
            }
            else{
                attack_over_timer.restart();
            }
        }
        // code
        if (attack) {
            attack_desicion_timer.restart();
            AttachedImage am5 = new AttachedImage(this, attack_image, 1, 0, new Vec2(0, 3));
            if (facing_right) {
                this.removeAllImages();
                AttachedImage am6 = new AttachedImage(this, attack_image, 1, 0, new Vec2(0, 3));
                am6.flipHorizontal();
            }
            if (!attack_over_timer.isRunning()) {
                attack_over_timer.setRepeats(false);
                attack_over_timer.start();
            }
            else{
                attack_over_timer.restart();
            }
        }
    }


    @Override
    public void postStep(StepEvent stepEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        int rand = random.nextInt(10);
        if (rand == 1) {
            ultimate_attack = true;
        }
        //if (attack_desicion_timer.getDelay() == 5600) {
        if(!attack) {
                attack = true;
                change_img();
            }

        else if (attack) {
            this.removeAllImages();
            this.addImage(idle_image);
            attack = false;
            attack_over_timer.stop();
        }
            /*
            if (rand != 1) {
                int rand2 = random.nextInt(3);
                if (rand2 == 1) {
                    attack = true;
                    change_img();
                }
            }*/

    }
}