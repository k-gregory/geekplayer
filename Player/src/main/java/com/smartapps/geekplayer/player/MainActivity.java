package com.smartapps.geekplayer.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Created by gregory on 8/15/15.
 */
public class MainActivity extends Activity {

    private class DrawerClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id){
            selectItem(position);
        }
    }

    private String[] menuEntries;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private PlayFragment fragment = new PlayFragment();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuEntries = getResources().getStringArray(R.array.playerMenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.menu_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(this,R.layout.drawer_list_item,menuEntries));
        drawerList.setOnItemClickListener(new DrawerClickListener());
        selectItem(0);
    }

    private void selectItem(int position){
        FragmentManager manager = getFragmentManager();
        manager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        drawerLayout.closeDrawer(drawerList);
    }
}
