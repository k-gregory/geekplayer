package com.smartapps.geekplayer.player;

import android.app.Fragment;
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

    private MediaPlayer player;

    public void playTrack(String path) throws IOException{
        player.reset();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDataSource(path);
        player.prepare();
        player.setLooping(false);
        player.start();
    }

    private String getAudioList() {
        final Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media.DATA,MediaStore.Audio.Media.ARTIST},
                null, //MediaStore.Audio.Media.ARTIST+" = 'Blood Sister'", //FIXME This shall newer be committed
                null,
                null);
        cursor.moveToFirst();
        String res = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
        cursor.close();
        return res;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_player,container,false);
        player = new MediaPlayer();
        try{
        playTrack(getAudioList());
        } catch (IOException e) {
            Log.e("PLAY","Can't play,lol");
        }
        return v;
    }
}
