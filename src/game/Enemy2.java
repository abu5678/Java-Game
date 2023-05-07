
package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Enemy2 extends Walker implements StepListener, ActionListener {
    Player player;
    private static final BodyImage move_image = new BodyImage("data/enemy2/enemy2_move.png", 4f);
    private static final BodyImage attack_image = new BodyImage("data/enemy2/enemy2_attack_left.GIF", 8f);
    private static final Shape enemyShape = new PolygonShape(
            -0.81f, 2.0f, 0.6f, 1.73f,
            0.89f, 0.72f, 0.86f, -0.61f,
            0.67f, -1.52f, -1.85f, -1.58f,
            -1.75f, -0.4f, -1.33f, 1.8f
    );

    private static final Shape attack_left_hb = new PolygonShape(
            0.03f, -1.31f, -5.11f, -1.31f, -5.11f, 4.35f, -0.37f, 4.29f

    );
    private static final Shape attack_right_hb = new PolygonShape(
            1.01f, -1.5f, 5.17f, -1.5f, 5.17f, 3.12f, 0.77f, 3.06f

    );
    private boolean facing_right;
    private boolean facing_left;
    private boolean attack;
    private boolean attack_anim;
    SolidFixture enemy2_hitbox;
    SolidFixture enemy2_attack;
    Timer attack_desicion_timer = new Timer(7200, this);//8400
    Timer attack_animation_timer = new Timer(2400, this);


    public Enemy2(World world, Player player) {
        super(world);
        this.player = player;
        addImage(move_image);
        world.addStepListener(this);
        startWalking(4);
        enemy2_hitbox = new SolidFixture(this, enemyShape);
        enemy2_hitbox.setFriction(30);
        setAlwaysOutline(true);
        attack_desicion_timer.start();
    }


    @Override
    public void preStep(StepEvent stepEvent) {
        if (getPosition().x > player.getPosition().x) {
            facing_right = false;
            facing_left = true;
            if (!attack) {
                this.removeAllImages();
                addImage(move_image);
            }
            startWalking(-3);
        }
        if (getPosition().x < player.getPosition().x) {
            facing_right = true;
            facing_left = false;
            if (!attack) {
                this.removeAllImages();
                AttachedImage am2 = new AttachedImage(this, move_image, 1, 0, new Vec2(0, 0));
                am2.flipHorizontal();
            }
            startWalking(3);
        }
    }

    public boolean isAttack() {
        return attack;
    }

    public void change_img() {
        this.removeAllImages();
        if (attack) {
            AttachedImage am7 = new AttachedImage(this, attack_image, 1, 0, new Vec2(-3, 1));
            if (facing_right) {
                this.removeAllImages();
                AttachedImage am8 = new AttachedImage(this, attack_image, 1, 0, new Vec2(0, 1));
                am8.flipHorizontal();
            }
            if (!attack_animation_timer.isRunning()) {
                attack_animation_timer.setRepeats(false);
                attack_animation_timer.start();
                attack_anim = true;
            } else {
                attack_animation_timer.restart();
                attack_anim = true;
            }
        }
    }

    public void change_hb() {
        if (this.getFixtureList().contains(enemy2_hitbox) && attack && facing_left) {
            enemy2_hitbox.destroy();
            enemy2_attack = new SolidFixture(this, attack_left_hb);
            enemy2_attack.setFriction(30);
        }
        if (this.getFixtureList().contains(enemy2_hitbox) && attack && facing_right) {
            enemy2_hitbox.destroy();
            enemy2_attack = new SolidFixture(this, attack_right_hb);
            enemy2_attack.setFriction(30);
        }
    }

    public void resetHitbox() {
        if (this.getFixtureList().contains(enemy2_attack)) {
            enemy2_attack.destroy();
            enemy2_hitbox = new SolidFixture(this, enemyShape);
            enemy2_hitbox.setFriction(30);
        }
    }


    @Override
    public void postStep(StepEvent stepEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (attack_anim) {
            attack_anim = false;
            attack = false;
            this.removeAllImages();
            resetHitbox();
            if (facing_left) {
                this.addImage(move_image);
            }
            if (facing_right) {
                AttachedImage am2 = new AttachedImage(this, move_image, 1, 0, new Vec2(0f, 0));
                am2.flipHorizontal();
            }

        } else {
            attack = true;
            change_hb();
            change_img();
        }
    }
}