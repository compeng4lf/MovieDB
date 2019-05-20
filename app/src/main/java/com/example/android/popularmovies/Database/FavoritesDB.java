package com.example.android.popularmovies.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;


@Database(entities = {FavoriteMovies.class},version = 1, exportSchema = false)
public abstract class FavoritesDB extends RoomDatabase {

    private static FavoritesDB sInstance;
    private static final Object LOCK = new Object();
    private  static final String DATABASE_NAME = "faves";

    public static FavoritesDB getInstance(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                Log.d("FavoritesDB", "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(), FavoritesDB.class, FavoritesDB.DATABASE_NAME).build();
            }
        }
        Log.d("FavoritesDB", "Getting the database instance.");
        return sInstance;

    }

    public abstract FavoriteMovieDao favoriteMovieDao();

}
