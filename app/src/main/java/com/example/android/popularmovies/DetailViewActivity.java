package com.example.android.popularmovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.Adapters.MovieReviewLinearAdapter;
import com.example.android.popularmovies.Adapters.MovieTrailerAdapter;
import com.example.android.popularmovies.Database.FavoriteMovies;
import com.example.android.popularmovies.Database.FavoritesDB;
import com.example.android.popularmovies.Formats.MovieDB;
import com.example.android.popularmovies.Formats.MovieReviewsDB;
import com.example.android.popularmovies.Formats.MovieTrailerDB;
import com.example.android.popularmovies.Parsers.parseReviewJson;
import com.example.android.popularmovies.Parsers.parseTrailerJson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailViewActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvAverage;
    private TextView tvReleaseDate;
    private ImageView ivPoster;
    private TextView tvTrailerLabel;
    private TextView tvReviewsLabel;
    private Button btnFavorite;
    private Button btnUnFavorite;
    private String mFormatDate;
    private String movieId;
    private boolean hasVideo;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<MovieReviewsDB> MovieReviews;
    private FavoritesDB mFavoriteDatabase;
    int isFavorite;
    private MovieDB mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);


        tvTrailerLabel = (TextView)findViewById(R.id.tvTrailer_Label);
        tvReviewsLabel = (TextView) findViewById(R.id.tvReviewsLabel);
        tvTitle = (TextView) findViewById(R.id.tv_movietitle);
        tvOverview = (TextView) findViewById(R.id.tv_overview);
        tvAverage = (TextView) findViewById(R.id.tv_voteavg);
        tvReleaseDate = (TextView) findViewById(R.id.tv_releasedate);
        ivPoster = (ImageView) findViewById(R.id.iv_Poster);
        btnFavorite = (Button) findViewById(R.id.btnFavorite);
        btnUnFavorite = (Button) findViewById(R.id.btnUnFavorite);
        mFavoriteDatabase = FavoritesDB.getInstance(getApplicationContext());

        SetButtonListenerAddFavorite();
        SetButtonListenerDeleteFavorite();

        Intent intentThatStartedThisIntent = getIntent();
        if (intentThatStartedThisIntent.hasExtra("MovieDetails")) {

            mdb = (MovieDB) intentThatStartedThisIntent.getSerializableExtra("MovieDetails");
            tvTitle.setText(mdb.getTitle());
            tvOverview.setText(mdb.getOverview());
            tvAverage.setText("Voter Rating: " + (Integer.toString(mdb.getVote_average())) + "/10");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date date = simpleDateFormat.parse(mdb.getRelease_date());
                mFormatDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tvReleaseDate.setText("Release Date: " + mFormatDate);

            //ADD PICASSO TO LOAD IMAGES
            Picasso.get()
                    .load(mdb.getPosterpath())
                    .into(ivPoster);

            movieId = String.valueOf(mdb.getMovieID());
            hasVideo = mdb.getHasVideo();

            //checkIsInFavorite();
            //Log.d("CheckFavorite", "OnCreate");

            FetchReviews fr = new FetchReviews();
            fr.execute(movieId);

            FetchTrailers ft = new FetchTrailers();
            ft.execute(movieId);

            ToggleButtons();

        } else
            Toast.makeText(this, "An error occurred go back and try again.", Toast.LENGTH_LONG).show();

    }
/*
    @Override
    protected void onResume() {
        super.onResume();
        checkIsInFavorite();
        Log.d("CheckFavorite", "OnResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //checkIsInFavorite();
        Log.d("CheckFavorite", "OnRestart");
    }




    private void checkIsInFavorite(){

        movieId = String.valueOf(mdb.getMovieID());

        //Check to see if movie is inside the DB already and show appropriate button
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                //Query Database to see if movie is in favorites already
                isFavorite = mFavoriteDatabase.favoriteMovieDao().checkFavorite(movieId);
            }
        });

        if (isFavorite == 0){
            btnFavorite.setVisibility(View.VISIBLE);
            btnUnFavorite.setVisibility(View.INVISIBLE);
        }
        else {
            btnFavorite.setVisibility(View.INVISIBLE);
            btnUnFavorite.setVisibility(View.VISIBLE);
        }
        Log.d("CheckFavorite", mdb.getTitle() + " Favorite Value " + isFavorite);
    }
*/
    private void SetButtonListenerAddFavorite() {
        //Button appears on error when there is no network connection in order to refresh once connectivity is back.
        btnFavorite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){

                //Toast.makeText(getApplicationContext(), "Favorite Button was clicked.", Toast.LENGTH_LONG).show();
                String movieTitle = tvTitle.getText().toString();
                String moId = movieId;
                final FavoriteMovies favoriteMovies = new FavoriteMovies(movieTitle, moId);

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        //Insert Movie into favorites
                        mFavoriteDatabase.favoriteMovieDao().insertAll(favoriteMovies);
                    }
                });

            }
        });
    }


    private void SetButtonListenerDeleteFavorite() {

        btnUnFavorite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){

                //Toast.makeText(getApplicationContext(), "Favorite Button was clicked.", Toast.LENGTH_LONG).show();
                String movieTitle = tvTitle.getText().toString();
                String moId = movieId;

                final FavoriteMovies favoriteMovies = new FavoriteMovies(movieTitle, moId);

                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        //Delete Movie from favorites
                        Log.d("Delete:", "Deleting Movie.");
                        mFavoriteDatabase.favoriteMovieDao().deleteFavorite(favoriteMovies);
                    }
                });
            }
        });
    }


    private void ToggleButtons(){

        LiveData<FavoriteMovies> movie = mFavoriteDatabase.favoriteMovieDao().checkFavorite(movieId);
        movie.observe(this, new Observer<FavoriteMovies>() {
            @Override
            public void onChanged(@Nullable FavoriteMovies favoriteMovies) {
                if (favoriteMovies == null){
                    btnFavorite.setVisibility(View.VISIBLE);
                    btnUnFavorite.setVisibility(View.INVISIBLE);
                }
                else {
                    btnFavorite.setVisibility(View.INVISIBLE);
                    btnUnFavorite.setVisibility(View.VISIBLE);
                }

            }
        });


    }

    public class FetchReviews extends AsyncTask<String, Void, ArrayList<MovieReviewsDB>> {

        @Override
        protected ArrayList<MovieReviewsDB> doInBackground(String... strings) {

            ArrayList<MovieReviewsDB> MovieReviews;

            try {
                //Build URL to get reviews
                String s = strings[0];
                URL movieReviewsURL = NetworkUtils.buildUrl(s + "reviews");
                //Read in response from request
                String reviewResponse = NetworkUtils.getResponseFromHttpUrl(movieReviewsURL);
                //Create Array to hold all reviews
                MovieReviews = parseReviewJson
                        .parseReviewJson(reviewResponse);

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return MovieReviews;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieReviewsDB> movieReviews) {
            super.onPostExecute(movieReviews);
            if (movieReviews == null || movieReviews.size() == 0) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        tvReviewsLabel.setText("Reviews:  There are no reviews for this movie.");

                        if (mRecyclerView != null) {
                            mRecyclerView.removeAllViewsInLayout();
                        }
                    }
                });

            } else {
                mRecyclerView = (RecyclerView) findViewById(R.id.rv_reviews);
                mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new MovieReviewLinearAdapter(movieReviews, getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }
        }
    }

    public class FetchTrailers extends AsyncTask<String, Void, ArrayList<MovieTrailerDB>> implements MovieTrailerAdapter.ListItemClickListener{
        ArrayList<MovieTrailerDB> MovieTrailers;

        @Override
        protected ArrayList<MovieTrailerDB> doInBackground(String... strings) {
            try {
                String s = strings[0];
                //Build URL to get trailers
                URL movieTrailersURL = NetworkUtils.buildUrl(s + "videos");

                //TEST URL to test Trailer RecyclerView
                //URL movieTrailersURL = NetworkUtils.buildUrl("292videos");

                //Read in response from request
                String TrailerResponse = NetworkUtils.getResponseFromHttpUrl(movieTrailersURL);
                //Create Array to hold all reviews
                 MovieTrailers = parseTrailerJson
                        .parseReviewJson(TrailerResponse);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return MovieTrailers;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieTrailerDB> movieTrailer) {
            super.onPostExecute(movieTrailer);
            if (movieTrailer == null || movieTrailer.size() == 0) {

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        tvTrailerLabel.setText("Trailers:  There are no trailers for this movie.");
                        if (mRecyclerView != null) {
                            mRecyclerView.removeAllViewsInLayout();
                        }

                        invalidateOptionsMenu();
                    }
                });

            } else {

                mRecyclerView = (RecyclerView) findViewById(R.id.rv_trailer);
                mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new MovieTrailerAdapter(movieTrailer, getApplicationContext(), this);
                mRecyclerView.setAdapter(mAdapter);
            }
        }

        @Override
        public void onListItemClick(int clickedItemIndex) {

            MovieTrailerDB intentdataholder = new MovieTrailerDB();
            intentdataholder = MovieTrailers.get(clickedItemIndex);
            String mSite = intentdataholder.getSite();
            String videoId = intentdataholder.getKey();
            if (mSite.equals("YouTube")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
                intent.putExtra("VIDEO_ID", videoId);
                startActivity(intent);
            }

        }

    }

}