package com.smartapps.geekplayer.player;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by gregory on 8/17/15.
 */

public class PlaybackService extends Service {
    private MediaPlayer player;
    private IBinder m_binder;
    private Playlist playlist;

    @Override
    public void onCreate(){
        m_binder = new PlaybackServiceBinder();
        player = new MediaPlayer();
        PlaybackController controller = new PlaybackController();
        player.setOnPreparedListener(controller);
        player.setOnCompletionListener(controller);
        player.setOnErrorListener(controller);
        playlist = new AllTracksPlaylist(getContentResolver()); //FIXME: Only 4 debug
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }


    class PlaybackController implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

        }

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.start();
        }

        @Override
        public boolean onError(MediaPlayer mp,int what,int extra){
            Log.e("PlaybackService","error in pbs!");
            return false;
        }
    }

    private void startPlayingTrack(String trackURI) throws IOException{
        player.reset();
        player.setDataSource(trackURI);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.prepareAsync();
    }

    public void play(){
        try {
            startPlayingTrack(playlist.getCurrentTrack());
        } catch (IOException e) {
            Log.e("PlaybackService",e.getMessage());
        }
    }

    public void next(){

    }

    public void previous(){

    }

    public void pause(){

    }

    public class PlaybackServiceBinder extends Binder {
        public PlaybackService getService(){
            return PlaybackService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent){
        return m_binder;
    }
}
