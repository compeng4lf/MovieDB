package com.example.android.popularmovies.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.popularmovies.Database.FavoriteMovies;
import com.example.android.popularmovies.R;

import org.w3c.dom.Text;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavHolder> {

    List<FavoriteMovies> favoriteMovies;
    Context context;

    public FavoritesAdapter(Context context){

        this.context = context;
    }


    @NonNull
    @Override
    public FavoritesAdapter.FavHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_favorites, viewGroup, false);
        FavoritesAdapter.FavHolder vh = new FavoritesAdapter.FavHolder(layout);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.FavHolder favHolder, int i) {

        FavoriteMovies fm = favoriteMovies.get(i);
        String title = fm.getMovieTitle();
        String id = fm.getMovieId();

        favHolder.tvMovieTitle.setText(title);
        favHolder.tvMovieId.setText(id);
    }

    @Override
    public int getItemCount() {
        if (favoriteMovies == null){
            return 0;
        }
        return favoriteMovies.size();
    }

    public class FavHolder extends RecyclerView.ViewHolder {
        TextView tvMovieId;
        TextView tvMovieTitle;


        public FavHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = (TextView) itemView.findViewById(R.id.tvMovieTitleFavorites);
            tvMovieId = (TextView) itemView.findViewById(R.id.tvMovieIdFavorites);
        }
    }
    public List<FavoriteMovies> getTasks() {
        return favoriteMovies;
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTasks(List<FavoriteMovies> taskEntries) {
        favoriteMovies = taskEntries;
        notifyDataSetChanged();
    }
}
