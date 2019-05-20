package com.example.android.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.popularmovies.Database.FavoriteMovies;
import com.example.android.popularmovies.Database.FavoritesDB;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private LiveData<List<FavoriteMovies>> tasks;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        FavoritesDB database = FavoritesDB.getInstance(this.getApplication());
        tasks = database.favoriteMovieDao().getAll();
        }

        public LiveData<List<FavoriteMovies>> getTasks(){
            return tasks;

        }
}

