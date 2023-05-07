package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Your main game entry point
 */
public class Game {
    GameLevel currentLevel;
    GameView view;

    PlayerController pc;
    private SoundClip level1_music;
    private SoundClip level2_music;
    private SoundClip level3_music;
    private JFrame frame;
    private Player player;
    int level_count;
    private endScreen menu;

    /** Initialise a new Game. */
    public Game() {
        currentLevel = new Level1(this);
        int level_count = 1;
        //GameLevel world = new GameLevel(this);
        player = currentLevel.getPlayer();
        try {
            level1_music = new SoundClip("data/level/level1.wav");
            level1_music.loop();
            level1_music.setVolume(0.1);
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
        }

        view = new GameView(currentLevel, 1080, 700, player,level_count,currentLevel.getBoss());
        currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));

        pc = new PlayerController(currentLevel.getPlayer());
        view.addKeyListener(pc);

        GiveFocus gf = new GiveFocus(view);
        view.addMouseListener(gf);

        //4. create a Java window (frame) and add the game
        //   view to it
        frame = new JFrame("City Game");
        frame.add(view);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        //optional: uncomment this to make a debugging view
        // JFrame debugView = new DebugViewer(world, 1080, 700);

        // start our game world simulation!
        currentLevel.start();
    }

    public void gameOver(){
        if (view.getLevel_count()==1){
            level1_music.stop();
        }
        if (view.getLevel_count()==2){
            level2_music.stop();
        }
        if (view.getLevel_count()==3){
            level3_music.stop();
        }
        currentLevel.stop();
        frame.remove(view);
        menu = new endScreen(this);
        menu.getMainPanel().setPreferredSize(new Dimension(1080,700));
        frame.add(menu.getMainPanel());
        frame.repaint();
        frame.pack();
    }
    public void restartGame(){
        currentLevel = new Level1(this);
        currentLevel = new Level1(this);
        view.setLevel_count(1);
        Image level_1_background = new ImageIcon("data/level/level1_bg.jpg").getImage();
        view.setBackground(level_1_background);
        try {
            level1_music = new SoundClip("data/level/level1.wav");
            level1_music.loop();
            level1_music.setVolume(0.1);
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
        }
        //level now refer to the new level
        view.setWorld(currentLevel);
        pc.updatePlayer(currentLevel.getPlayer());
        currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));
        currentLevel.start();
        frame.remove(menu.getMainPanel());
        frame.add(view);
        frame.repaint();
        frame.pack();

    }
    public void goToNextLevel() {
        if (currentLevel instanceof Level1) {
            currentLevel.stop();
            currentLevel = new Level2(this);
            view.setLevel_count(2);
            Image level_2_background = new ImageIcon("data/level/level2_bg.jpg").getImage();
            view.setBackground(level_2_background);
            level1_music.stop();
            try {
                level2_music = new SoundClip("data/level/level2.wav");
                level2_music.loop();
                level2_music.setVolume(0.1);
            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            }
            //level now refer to the new level
            view.setWorld(currentLevel);
            pc.updatePlayer(currentLevel.getPlayer());
            currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));
            currentLevel.start();
        }
        else if (currentLevel instanceof Level2) {
            currentLevel.stop();
            currentLevel = new Level3(this);
            view.setLevel_count(3);
            view = new GameView(currentLevel, 1080, 700, player,level_count,currentLevel.getBoss());
            Image level_3_background = new ImageIcon("data/level/level3_bg.jpg").getImage();
            view.setBackground(level_3_background);
            level2_music.stop();
            try {
                level3_music = new SoundClip("data/level/level3_music.wav");
                level3_music.loop();
                level3_music.setVolume(0.1);
            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {

            }
            //level now refer to the new level
            view.setWorld(currentLevel);
            pc.updatePlayer(currentLevel.getPlayer());
            currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));
            currentLevel.start();
        }
    }

    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }
}
