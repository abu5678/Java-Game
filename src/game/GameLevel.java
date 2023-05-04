package game;
import city.cs.engine.BodyImage;
import city.cs.engine.World;
import game.Enemy;
import game.Player;
import game.Portal;
import game.PortalCollision;
import org.jbox2d.common.Vec2;
public abstract class GameLevel extends World {
    private Player player;

    private Enemy enemy;

    private Portal portal;

    public GameLevel(Game game) {
        player = new Player(this);
        player.setPosition(new Vec2(-12, -10));

        ItemPickup items = new ItemPickup(player);
        player.addCollisionListener(items);

        Collision enemy_collision = new Collision(player);
        player.addCollisionListener(enemy_collision);

        Portal portal1 = new Portal(this);
        portal1.setPosition(new Vec2(-23,-11));
        player.addCollisionListener(new PortalCollision(this, game));

    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Player getPlayer() {
            return player;
        }
    public abstract boolean isComplete();{
}
}