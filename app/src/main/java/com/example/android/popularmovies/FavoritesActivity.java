package com.example.android.popularmovies;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.android.popularmovies.Adapters.FavoritesAdapter;
import com.example.android.popularmovies.Database.FavoriteMovies;
import com.example.android.popularmovies.Database.FavoritesDB;



import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    //Logging
    private static final String TAG = FavoritesActivity.class.getSimpleName();

    //Variables for adapter and RecyclerView
    private RecyclerView mRecylcerView;
    private FavoritesAdapter mAdapter;
    private TextView mNoFavorite;

    //Database
    private FavoritesDB mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        mNoFavorite = (TextView) findViewById(R.id.tvNoFaves);

        //Set RecycleView to correct view
        mRecylcerView = findViewById(R.id.rvFavorites);

        mRecylcerView.setLayoutManager(new LinearLayoutManager(this));

        //Initialize the adapter and attach it to the RecycleView
        mAdapter = new FavoritesAdapter(this);
        mRecylcerView.setAdapter(mAdapter);


        mDatabase = FavoritesDB.getInstance(getApplicationContext());
        setupViewModel();

    }

    private void setupViewModel(){

        FavoriteViewModel viewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        //Observe the LiveData in the viewModel
        viewModel.getTasks().observe(this, new Observer<List<FavoriteMovies>>() {
            @Override
            public void onChanged(@Nullable List<FavoriteMovies> favoriteMovies) {

                if (favoriteMovies.size() == 0 ){
                    mNoFavorite.setText("There are no favorites");
                }
                if (favoriteMovies.size() > 0){
                    mNoFavorite.setText("Favorites");
                }
                Log.d(TAG, "Updating list of movies from LiveData in ViewModel");

                mAdapter.setTasks(favoriteMovies);
            }
        });

    }

}
