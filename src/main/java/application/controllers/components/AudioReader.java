package application.controllers.components;

/*
import application.resources.LibVlc;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.RuntimeUtil;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
*/

public class AudioReader {
/*
    private MediaPlayerFactory mediaPlayerFactory;

    private EmbeddedMediaPlayer embeddedMediaPlayer;

    public AudioReader() {
        System.out.println("a");

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files (x86)\\VideoLAN\\VLC");

        System.out.println("b");
        try {
            LibVlc instance = Native.load(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        }
        catch (IllegalArgumentException e) {
            System.out.println("c");
            this.mediaPlayerFactory = new MediaPlayerFactory();
            this.embeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
            this.embeddedMediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
                @Override
                public void playing(MediaPlayer mediaPlayer) {
                }

                @Override
                public void paused(MediaPlayer mediaPlayer) {
                }

                @Override
                public void stopped(MediaPlayer mediaPlayer) {
                }

                @Override
                public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
                }
            });
        }


    }

    /*
    Start audio only
     *

    public void start(String file) {
        embeddedMediaPlayer.media().prepare(file);
        embeddedMediaPlayer.media().play("mp4");

        embeddedMediaPlayer.controls().setPosition(0.4f);
    }

    public final void stop() {
        embeddedMediaPlayer.controls().stop();
        embeddedMediaPlayer.release();
        mediaPlayerFactory.release();
    }
*/
}
