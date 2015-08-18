package com.smartapps.geekplayer.player;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

/**
 * Created by gregory on 8/17/15.
 */
public class PlaybackService extends IntentService {
    private MediaPlayer player;

    public  PlaybackService(){
        super("GP_PlaybackService");
        player = new MediaPlayer();
    }
    @Override
    public void onHandleIntent(Intent intent) {

    }
}
