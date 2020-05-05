package application.controllers.components;



import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import javafx.scene.layout.Pane;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.RuntimeUtil;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.io.IOException;


public class AudioReader {

    private MediaPlayerFactory mediaPlayerFactory;

    private EmbeddedMediaPlayerComponent embeddedMediaPlayer;

    public AudioReader() {

        //NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files (x86)\\VideoLAN\\VLC");



          // LibVlc instance = Native.load(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);


    }

    /*
    Start audio only
     */

    public void start(String file, Pane pane) throws IOException {
        // pane.getChildren().add(embeddedMediaPlayer);

        System.out.println("A");
        FFmpeg ffmpeg = new FFmpeg("C:\\PROJECTS 2020\\ffmpeg\\ffmpeg.exe");
        System.out.println("B");
        FFprobe ffprobe = new FFprobe("C:\\PROJECTS 2020\\ffprobe\\ffprobe.exe");
        System.out.println("C");
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(file) // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists
                .addOutput("output.mp3") // Filename for the destination
                .setFormat("mp3") // Format is inferred from filename, or can be set
                .setAudioCodec("libmp3lame") // using the aac codec
                .setAudioSampleRate(48_000) // at 48KHz
                .setAudioBitRate(32768) // at 32 kbit/s
                .done();
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        System.out.println("D");
// Run a one-pass encode
        executor.createJob(builder).run();
        System.out.println("E");

        AudioPlayerComponent apc = new AudioPlayerComponent();
        apc.mediaPlayer().media().play("C:\\PROJECTS 2020\\Lecture_Project\\output.mp3");


    }

    public final void stop() {
        embeddedMediaPlayer.release();
        mediaPlayerFactory.release();
    }

}
