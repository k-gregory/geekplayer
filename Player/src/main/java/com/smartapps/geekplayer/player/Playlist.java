package com.smartapps.geekplayer.player;

/**
 * Created by gregory on 8/26/15.
 */
public interface Playlist {
    String getCurrentTrack(); //Returns current track URI
    boolean nextTrack(); //return false if current is the last
    boolean previousTrack(); //return false if current is the first
}
