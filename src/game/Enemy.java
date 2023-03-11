package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Enemy extends Walker implements StepListener{
    private float left,right;
    private static final BodyImage image = new BodyImage("enemy/enemy_run_right.GIF", 4f);

    private static final Shape enemyShape = new PolygonShape(
            -0.81f,2.0f, 0.6f,1.73f,
            0.89f,0.72f, 0.86f,-0.61f,
            0.67f,-1.52f, -1.85f,-1.58f,
            -1.75f,-0.4f, -1.33f,1.8f
    );
    public Enemy(World world) {
        super(world);
        addImage(image);
        world.addStepListener(this);
        startWalking(3);
        SolidFixture E = new SolidFixture(this,enemyShape);
        E.setFriction(30);
    }

    public void setPosition(Vec2 Pos){
       // Vec2 startPos = this.getPosition();
        super.setPosition((Pos));
        left = Pos.x-3f;
        right = Pos.x+3f;
    }

@Override
    public void preStep(StepEvent stepEvent){
        if (getPosition().x >right){
            startWalking(-3);
            BodyImage image = new BodyImage("enemy/enemy_run_left.gif", 4);
            this.removeAllImages();
            this.addImage(image);

        }
        if(getPosition().x<left){
            startWalking(3);
            BodyImage image = new BodyImage("enemy/enemy_run_right.gif", 4);
            this.removeAllImages();
            this.addImage(image);
        }
    }


    @Override
    public void postStep(StepEvent stepEvent) {

    }
}