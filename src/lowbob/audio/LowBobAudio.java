package lowbob.audio;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Created by opuee on 31.05.17.
 */
public class LowBobAudio implements Runnable {

    private Clip clip;

    public LowBobAudio(String path) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(path));
            // Get a sound clip resource.
            this.clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            this.clip.open(audioIn);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        this.run();
    }

    @Override
    public void run() {
        if (this.clip.isRunning())
            this.clip.stop();

        this.clip.start();
    }
}
