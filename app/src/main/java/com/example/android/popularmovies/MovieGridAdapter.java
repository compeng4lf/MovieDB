package com.example.android.popularmovies;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MovieviewHolder> {


    ArrayList<MovieDB> movielist;
    Context context;

    public MovieGridAdapter(ArrayList<MovieDB> movielist, Context context){

        this.movielist = movielist;
        this.context = context;

    }



    @NonNull
    @Override
    public MovieviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_moviegrid, viewGroup, false);
        MovieviewHolder vh = new MovieviewHolder(layout);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieviewHolder viewHolder, int position) {

        viewHolder.tvTitle.setText(movielist.get(position).getTitle()); //Get the movie title out the array


        //ADD PICASSO TO LOAD IMAGES

        Picasso.get()
                .load(movielist.get(position).getPosterpath())
                .into(viewHolder.imgPoster);


    }

    @Override
    public int getItemCount() {
        return movielist.size(); //MovieList needs to be an ArrayList
    }

    public static class MovieviewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        ImageView imgPoster;

        public MovieviewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.iv_MoviePoster);
            tvTitle = itemView.findViewById(R.id.tv_MovieName);
        }
    }
}
