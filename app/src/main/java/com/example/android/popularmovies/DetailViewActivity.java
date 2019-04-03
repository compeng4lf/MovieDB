package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class DetailViewActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvAverage;
    private TextView tvReleaseDate;
    private ImageView ivPoster;
    private String mfixDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);

        tvTitle = (TextView)findViewById(R.id.tv_movietitle);
        tvOverview = (TextView)findViewById(R.id.tv_overview);
        tvAverage = (TextView)findViewById(R.id.tv_voteavg);
        tvReleaseDate = (TextView)findViewById(R.id.tv_releasedate);
        ivPoster = (ImageView)findViewById(R.id.iv_Poster);

        Intent intentThatStartedThisIntent = getIntent();
        if (intentThatStartedThisIntent.hasExtra("MovieDetails")){

            MovieDB mdb = (MovieDB) intentThatStartedThisIntent.getSerializableExtra("MovieDetails");
            tvTitle.setText(mdb.getTitle());
            tvOverview.setText(mdb.getOverview());
            tvAverage.setText("Voter Rating: " + (Integer.toString(mdb.getVote_average())) + "/10");
            mfixDate = mdb.getRelease_date().substring(0,4);
            tvReleaseDate.setText("Release Date: " + mfixDate);

            //ADD PICASSO TO LOAD IMAGES
            Picasso.get()
                    .load(mdb.getPosterpath())
                    .into(ivPoster);
        }
        else
            Toast.makeText(this, "An error occurred go back and try again.", Toast.LENGTH_LONG).show();








    }
}
