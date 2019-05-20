package com.example.android.popularmovies.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.widget.LinearLayout;

import java.util.List;

@Dao
public interface FavoriteMovieDao {

    @Query("SELECT * FROM favorites")
    LiveData<List<FavoriteMovies>> getAll();

    @Query("SELECT * FROM favorites where movieId = :id")
    LiveData<FavoriteMovies> checkFavorite (String id);

    @Insert
    void insertAll(FavoriteMovies favoriteMovies);

    @Delete
    void deleteFavorite(FavoriteMovies favoriteMovies);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateFavorite(FavoriteMovies favoriteMovies);

}
