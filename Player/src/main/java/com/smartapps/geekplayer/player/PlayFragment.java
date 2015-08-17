package com.smartapps.geekplayer.player;

import android.app.Fragment;
import android.database.Cursor;
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
        player.setDataSource(path);
        player.prepare();
        player.start();
    }

    private String getAudioList() {
        final Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media.DATA},
                null,
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
        Log.d("MY_APP",getAudioList());
        try {
            playTrack(getAudioList());
        } catch (IOException e) {
            Log.e("MY_APP",e.getMessage());
        }
        return v;
    }
}
