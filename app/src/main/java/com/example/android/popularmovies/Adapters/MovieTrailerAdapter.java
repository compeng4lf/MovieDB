package com.example.android.popularmovies.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.Formats.MovieTrailerDB;
import com.example.android.popularmovies.R;

import java.util.ArrayList;

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.TrailerHolder> {

    ArrayList<MovieTrailerDB> movieTrailerList;
    Context context;
    final private MovieTrailerAdapter.ListItemClickListener mOnClickListener;

    public interface ListItemClickListener{
        public void onListItemClick(int clickedItemIndex);
    }

    public MovieTrailerAdapter(ArrayList<MovieTrailerDB> mTrailerList, Context context, ListItemClickListener listItemClickListener) {

        this.movieTrailerList = mTrailerList;
        this.context = context;
        mOnClickListener = listItemClickListener;
    }


    @NonNull
    @Override
    public MovieTrailerAdapter.TrailerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Create Views
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_trailer, viewGroup, false);
        MovieTrailerAdapter.TrailerHolder vh = new MovieTrailerAdapter.TrailerHolder(layout);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTrailerAdapter.TrailerHolder trailerHolder, int i) {
        int VidNumber = i + 1;
        trailerHolder.tvTrailer.setText("Trailer " + VidNumber);

    }

    @Override
    public int getItemCount() {
        return movieTrailerList.size();
    }

    public class TrailerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTrailer;

        public TrailerHolder(View itemView) {
            super(itemView);
            tvTrailer = (TextView) itemView.findViewById(R.id.tvTrailer);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}