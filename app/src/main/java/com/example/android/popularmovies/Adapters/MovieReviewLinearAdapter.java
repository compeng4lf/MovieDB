package com.example.android.popularmovies.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.android.popularmovies.Formats.MovieReviewsDB;
import com.example.android.popularmovies.R;

import java.util.ArrayList;

public class MovieReviewLinearAdapter extends RecyclerView.Adapter<MovieReviewLinearAdapter.MovieReviewHolder> {

    ArrayList<MovieReviewsDB> movieReviewList;
    Context context;


    public MovieReviewLinearAdapter(ArrayList<MovieReviewsDB> movielist, Context context){

        this.movieReviewList = movielist;
        this.context = context;

    }


    @NonNull
    @Override
    public MovieReviewLinearAdapter.MovieReviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        //Create Views
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_reviews, viewGroup, false);
        MovieReviewLinearAdapter.MovieReviewHolder vh = new MovieReviewLinearAdapter.MovieReviewHolder(layout);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewLinearAdapter.MovieReviewHolder viewHolder, int position) {

        viewHolder.tvAuthor.setText(movieReviewList.get(position).getAuthor());
        viewHolder.tvURL.setText(movieReviewList.get(position).getUrl());
        viewHolder.tvReviews.setText(movieReviewList.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return movieReviewList.size(); //MovieList needs to be an ArrayList
    }


    public class MovieReviewHolder extends RecyclerView.ViewHolder{

        TextView tvReviews;
        TextView tvURL;
        TextView tvAuthor;


        public MovieReviewHolder(View v) {
            super(v);

            tvReviews = (TextView) v.findViewById(R.id.tvContent);
            tvURL = (TextView) v.findViewById(R.id.tvURL);
            tvAuthor = (TextView) v.findViewById(R.id.tvAuthor);

        }

    }
}
