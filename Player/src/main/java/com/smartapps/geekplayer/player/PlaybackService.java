package com.smartapps.geekplayer.player;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by gregory on 8/17/15.
 */

public class PlaybackService extends Service {
    private MediaPlayer player;
    private IBinder m_binder;
    private Playlist playlist;

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public void play(){

    }

    public void pause(){

    }

    public class PlaybackServiceBinder extends Binder {
        public PlaybackService getService(){
            return PlaybackService.this;
        }
    }

    public PlaybackService(){
        player = new MediaPlayer();
    }
    @Override
    public IBinder onBind(Intent intent){
        return m_binder;
    }
}
