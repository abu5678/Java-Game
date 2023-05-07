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
    private Enemy2 enemy2;

    private Portal portal;

    private Boss boss;

    public Boss getBoss() {
        return boss;
    }

    public GameLevel(Game game) {
        player = new Player(this,game);
        player.setPosition(new Vec2(-12, -10));

        ItemPickup items = new ItemPickup(player);
        player.addCollisionListener(items);

        Collision enemy_collision = new Collision(player);
        player.addCollisionListener(enemy_collision);


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