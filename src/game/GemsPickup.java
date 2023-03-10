package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class GemsPickup implements CollisionListener{

    private Player player;

    public GemsPickup(Player player){
        this.player = player;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Gems) {
            player.setScore(player.getScore()+100);
            e.getOtherBody().destroy();
        }
    }
}