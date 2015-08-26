package com.smartapps.geekplayer.player;

import java.util.List;

/**
 * Created by gregory on 8/26/15.
 */
public interface ListablePlaylist extends Playlist {
    List<String > getAllTracks(); //Return all tracks URIs
}
