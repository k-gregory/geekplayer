package com.smartapps.geekplayer.player;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


class MusicDatabaseOpenHelper extends SQLiteOpenHelper{
    private static final int databaseVersion = 1;
    private static final String databaseName = "CompositionsDB";

    MusicDatabaseOpenHelper(Context ctx){
        super(ctx,databaseName,null,databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //Todo: Implement schema upgrade
        throw new UnsupportedOperationException("Database upgrading is not supported");
    }
}

public class MusicDatabase {
}