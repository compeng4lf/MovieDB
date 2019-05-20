package com.example.android.popularmovies.Database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favorites")
public class FavoriteMovies {


    @PrimaryKey @NonNull
    @ColumnInfo(name = "movieTitle")
    private String movieTitle;

    @ColumnInfo(name = "movieId")
    private String movieId;

    public FavoriteMovies(String movieTitle, String movieId){
        this.movieTitle = movieTitle;
        this.movieId = movieId;
    }


        public String getMovieTitle() {
            return movieTitle;
        }

        public void setMovieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
        }

        public String getMovieId() {
            return movieId;
        }

        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }

}
