package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ProgressBar mLoadingIndicator;
    private MenuItem mPopular;
    private MenuItem mToprated;
    private String mSortPath = "popular";
    private String jsonMovieResponse;
    private ArrayList<MovieDB> simpleJsonMovieData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        FetchGridLayout fgl = new FetchGridLayout();
        fgl.execute();
    }



    public class FetchGridLayout extends AsyncTask<Void, Void, ArrayList<MovieDB>> implements MovieGridAdapter.ListItemClickListener{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<MovieDB> doInBackground(Void... voids) {


            URL MovieGridURL = NetworkUtils.buildUrl(mSortPath);

            try {
                jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(MovieGridURL);

                simpleJsonMovieData = parseMovieJson
                        .parseMovieJson(jsonMovieResponse);

                return simpleJsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<MovieDB> movieData) {

            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (movieData.size() != 0) {
                recyclerView = (RecyclerView) findViewById(R.id.rv_MovieGrid);
                recyclerView.setHasFixedSize(true);
                mAdapter = new MovieGridAdapter(movieData, getApplicationContext(), this);
                recyclerView.setAdapter(mAdapter);


                }
            else
                showErrormessage();
            }

        @Override
        public void onListItemClick(int clickedItemIndex) {

            MovieDB intentdataholder = new MovieDB();
            intentdataholder = simpleJsonMovieData.get(clickedItemIndex);
            Context context = MainActivity.this;
            Class destinationActivity = DetailViewActivity.class;
            Intent intent = new Intent(context, destinationActivity);
            intent.putExtra("MovieDetails", intentdataholder);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        mToprated = (MenuItem) menu.findItem(R.id.action_toprated);
        mPopular = (MenuItem) menu.findItem(R.id.action_popular);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemThatWasClickedId = item.getItemId();

        switch (itemThatWasClickedId){
            case R.id.action_popular:
                mSortPath = "popular";
                item.setChecked(true);
                mToprated.setChecked(false);
                //Toast.makeText(this, "Sorting by Most Popular Movies", Toast.LENGTH_SHORT).show();
                FetchGridLayout PopularLayout = new FetchGridLayout();
                PopularLayout.execute();
                return true;

            case R.id.action_toprated:
                mSortPath = "top_rated";
                //Toast.makeText(this, "Sorting by Top Rated Movies", Toast.LENGTH_SHORT).show();
                item.setChecked(true);
                mPopular.setChecked(false);
                FetchGridLayout TopRatedLayout = new FetchGridLayout();
                TopRatedLayout.execute();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showErrormessage(){

        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show();

    }



}






