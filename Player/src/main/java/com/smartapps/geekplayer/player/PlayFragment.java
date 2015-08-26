package com.smartapps.geekplayer.player;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by gregory on 8/16/15.
 */

public class PlayFragment extends Fragment {

    private PlaybackService playbackService;
    private boolean pbServiceBound = false;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PlaybackService.PlaybackServiceBinder binder =
                    (PlaybackService.PlaybackServiceBinder) iBinder;
            playbackService = binder.getService();
            playbackService.play();
            Log.e("service","connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_player,container,false);
    }
    @Override
    public void onStart(){
        super.onStart();
        Intent intent = new Intent(getActivity(),PlaybackService.class);
        getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }
    @Override public void onStop() {
        super.onStop();
    }
}
