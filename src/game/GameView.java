package game;

import city.cs.engine.BodyImage;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {

    Player player;

    private final Image background = new ImageIcon("data/level/forest_background.png").getImage();

    private final Image fireball = new ImageIcon("data/level/fireball.PNG").getImage();

    public GameView(World w, int width, int height, Player player) {
        super(w, width, height);
        this.player = player;
    }
    protected void paintForeground(Graphics2D fg){
        fg.setColor(Color.black);
        fg.fillRect(795,45,210,40);
        fg.fillRect(495,45,210,40);
        fg.setColor(Color.white);
        fg.drawString("Score: "+ Player.getScore(),800,100);
        fg.fillRect(800,50,200,30);
        fg.fillRect(500,50,200,30);
        fg.setColor(Color.green);
        fg.fillRect(800,50,player.getHealth(),30);
        fg.setColor(Color.blue);
        fg.fillRect(500,50,player.getEnergy(),30);

        if (player.getFireball_num() > 0){
            fg.drawImage(fireball, 100,-20,500,220,this);
        }
    }
    @Override
    protected void paintBackground(Graphics2D bg) {
        bg.drawImage(background,0,0,1080,700,this);
    }
}