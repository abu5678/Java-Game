package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Boss extends Walker implements StepListener{
    private float left,right;
    private static final BodyImage image = new BodyImage("data/boss/boss_idle_left.GIF", 8f);

    private static final Shape enemyShape = new PolygonShape(
            -0.81f,2.0f, 0.6f,1.73f,
            0.89f,0.72f, 0.86f,-0.61f,
            0.67f,-1.52f, -1.85f,-1.58f,
            -1.75f,-0.4f, -1.33f,1.8f
    );
    Player player;
    public Boss(World world,Player player) {
        super(world);
        this.player = player;
        addImage(image);
        world.addStepListener(this);
        startWalking(4);
        SolidFixture E = new SolidFixture(this,enemyShape);
        E.setFriction(30);
        setAlwaysOutline(true);
    }


    @Override
    public void preStep(StepEvent stepEvent){
        if (getPosition().x > player.getPosition().x){
            startWalking(-4);
        }
        if(getPosition().x < player.getPosition().x){
            startWalking(4);
        }
    }


    @Override
    public void postStep(StepEvent stepEvent) {

    }
}