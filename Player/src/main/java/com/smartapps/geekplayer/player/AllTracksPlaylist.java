package com.smartapps.geekplayer.player;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;


import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by gregory on 8/26/15.
 */
public class AllTracksPlaylist implements ListablePlaylist {

    List<String> tracks;
    ListIterator<String> iter;

    @Override
    public String getCurrentTrack() {
        return currentTrack;
    }

    void setCurrentTrack(String currentTrack) {
        this.currentTrack = currentTrack;
    }

    String currentTrack = null;


    public AllTracksPlaylist(ContentResolver resolver) {
        final Cursor cursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media.DATA,MediaStore.Audio.Media.ARTIST},
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            int index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
            tracks = new LinkedList<>();
            tracks.add(cursor.getString(index));
            iter = tracks.listIterator();
            setCurrentTrack(iter.next());
            while(cursor.moveToNext()){
                tracks.add(cursor.getString(index));
            }
        }
        else tracks = null;
        cursor.close();
    }

    @Override
    public List<String> getAllTracks() {
        return tracks;
    }

    @Override
    public boolean nextTrack() {
        if(!iter.hasNext()) return false;
        setCurrentTrack(iter.next());
        return true;
    }

    @Override
    public boolean previousTrack() {
        if(!iter.hasPrevious()) return false;
        setCurrentTrack(iter.previous());
        return true;
    }
}
