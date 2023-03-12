package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class ItemPickup implements CollisionListener {

    private Player player;

    public ItemPickup(Player player){

        this.player = player;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if(collisionEvent.getOtherBody() instanceof Gems){
            player.setScore(player.getScore()+100);
            collisionEvent.getOtherBody().destroy();
        }
        else if(collisionEvent.getOtherBody() instanceof Fireball) {
            player.setFireball_num(player.getFireball_num() + 1);
            collisionEvent.getOtherBody().destroy();
        }
        else if (collisionEvent.getOtherBody() instanceof Energy){
            collisionEvent.getOtherBody().destroy();
        }
    }
}