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
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener{
        public void onListItemClick(int clickedItemIndex);
    }

    public MovieGridAdapter(ArrayList<MovieDB> movielist, Context context, ListItemClickListener listItemClickListener){

        this.movielist = movielist;
        this.context = context;
        mOnClickListener = listItemClickListener;

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


        //ADD PICASSO TO LOAD IMAGES
        Picasso.get()
                .load(movielist.get(position).getPosterpath())
                .into(viewHolder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return movielist.size(); //MovieList needs to be an ArrayList
    }



    public class MovieviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgPoster;

        public MovieviewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.iv_MoviePoster);
            itemView.setOnClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
