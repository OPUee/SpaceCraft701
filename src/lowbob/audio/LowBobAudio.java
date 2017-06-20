package lowbob.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

/**
 * Created by opuee on 31.05.17.
 */
public class LowBobAudio {

    MediaPlayer mediaPlayer;

    public LowBobAudio(String path)  {
        Media hit = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                // set duration to zero for replay
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.stop();
                // it's pretty ugly... but it takes me
                // three days for playing sounds efficient
                // thanks java
                // -> if someones knows a better way, let me know!!!
            }
        });
    }

    public void play() {
        if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING)
        {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.stop();
        }
        mediaPlayer.play();
    }
}
