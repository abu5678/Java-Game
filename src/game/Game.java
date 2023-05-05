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

    /** Initialise a new Game. */
    public Game() {
        currentLevel = new Level1(this);
        //GameLevel world = new GameLevel(this);
        Player player = currentLevel.getPlayer();

        view = new GameView(currentLevel, 1080, 700, player);
        currentLevel.addStepListener(new Tracker(view, currentLevel.getPlayer()));

        pc = new PlayerController(currentLevel.getPlayer());
        view.addKeyListener(pc);

        GiveFocus gf = new GiveFocus(view);
        view.addMouseListener(gf);

        //4. create a Java window (frame) and add the game
        //   view to it
        final JFrame frame = new JFrame("City Game");
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
    public void goToNextLevel() {
        if (currentLevel instanceof Level1) {
            currentLevel.stop();
            currentLevel = new Level3(this);
            Image level_3_background = new ImageIcon("data/level/level3_bg.jpg").getImage();
            view.setBackground(level_3_background);
            try {
                level3_music = new SoundClip("data/level/level3_music.wav");
                level3_music.loop();
                level3_music.setVolume(0.2);
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
