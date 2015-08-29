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

class PlaybackController implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    private final String TAG = "PlaybackController";

    private PlaybackService playbackService;

    public PlaybackController(PlaybackService playbackService){
        this.playbackService = playbackService;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Playlist playlist = playbackService.getPlaylist();

        if(playlist.nextTrack())
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(playbackService.getPlaylist().getCurrentTrack());
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                throw new Error("Can't open next song!");
            }

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    @Override
    public boolean onError(MediaPlayer mp,int what,int extra){
        Log.e(TAG,"error in pbs!");
        return false;
    }
}

public class PlaybackService extends Service {
    private final String TAG="PlaybackService";
    
    private MediaPlayer player;
    private IBinder m_binder;

    public Playlist getPlaylist() {
        return playlist;
    }

    private Playlist playlist;

    @Override
    public void onCreate(){
        m_binder = new PlaybackServiceBinder();
        player = new MediaPlayer();
        PlaybackController controller = new PlaybackController(this);
        player.setOnPreparedListener(controller);
        player.setOnCompletionListener(controller);
        player.setOnErrorListener(controller);
        playlist = new AllTracksPlaylist(getContentResolver()); //FIXME: Only 4 debug
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
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
            Log.e(TAG,e.getMessage());
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
