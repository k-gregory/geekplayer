package com.smartapps.geekplayer.player;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_player,container,false);
    }
    @Override
    public void onStart(){
        super.onStart();
        Intent intent = new Intent(getActivity(),PlaybackService.class);
    }
    @Override public void onStop() {

    }
}
