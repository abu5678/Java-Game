package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

public class GemsPickup implements SensorListener {

    private Player player;

    public GemsPickup(Player player){

        this.player = player;
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {
        if(sensorEvent.getContactBody() instanceof Gems){
            player.setScore(player.getScore()+100);
            sensorEvent.getContactBody().destroy();
        }

    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}