package com.smartapps.geekplayer.player;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by gregory on 8/15/15.
 */
public class MainActivity extends Activity {
    private String[] menuEntries;
    private DrawerLayout drawerLayout;
    private ListView drawerList;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuEntries = getResources().getStringArray(R.array.playerMenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.menu_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(this,R.layout.drawer_list_item,menuEntries));
    }
}
