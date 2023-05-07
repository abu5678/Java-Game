package game;

import city.cs.engine.BodyImage;
import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {

    Player player;

    public void setBackground(Image background) {
        this.background = background;
    }

    private Image background = new ImageIcon("data/level/level1_bg.jpg").getImage();

    private final Image fireball = new ImageIcon("data/level/fireball.PNG").getImage();
    int level_count;
    Boss boss;
    public GameView(GameLevel w, int width, int height, Player player,int level_count, Boss boss) {
        super(w, width, height);
        this.player = player;
        this.boss = boss;
        this.level_count = level_count;
    }

    public void setLevel_count(int level_count) {
        this.level_count = level_count;
    }

    public int getLevel_count() {
        return level_count;
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

        if(level_count == 3){
            fg.setColor(Color.black);
            fg.fillRect(200,45,350,40);
            fg.setColor(Color.green);
            fg.fillRect(200,50,boss.getHealth(),30);
        }


    }
    @Override
    protected void paintBackground(Graphics2D bg) {

        bg.drawImage(background,0,0,1080,700,this);
    }
}