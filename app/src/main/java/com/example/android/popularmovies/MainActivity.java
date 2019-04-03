package com.example.android.popularmovies;

import android.os.AsyncTask;
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

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ProgressBar mLoadingIndicator;
    private MenuItem mPopular;
    private MenuItem mToprated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        FetchGridLayout fgl = new FetchGridLayout();
        fgl.execute();


    }



    public class FetchGridLayout extends AsyncTask<Void, Void, ArrayList<MovieDB>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<MovieDB> doInBackground(Void... voids) {


            URL MovieGridURL = NetworkUtils.buildUrl();

            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(MovieGridURL);

                ArrayList<MovieDB> simpleJsonMovieData = parseMovieJson
                        .parseMovieJson(jsonMovieResponse);

                return simpleJsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<MovieDB> movieData) {
            if (movieData != null) {
                mLoadingIndicator.setVisibility(View.INVISIBLE);
                recyclerView = (RecyclerView) findViewById(R.id.rv_MovieGrid);

                recyclerView.setHasFixedSize(true);

                mAdapter = new MovieGridAdapter(movieData, getApplicationContext());
                recyclerView.setAdapter(mAdapter);

                }
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
                item.setChecked(true);
                mToprated.setChecked(false);
                FetchGridLayout fgl = new FetchGridLayout();
                fgl.execute();
                return true;

            case R.id.action_toprated:
                Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_LONG).show();
                item.setChecked(true);
                mPopular.setChecked(false);



                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    }






