package com.example.android.popularmovies.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.example.android.popularmovies.Formats.MovieDB;

public class parseMovieJson {

    public static ArrayList<MovieDB> parseMovieJson(String json) {

        ArrayList<MovieDB> movieresults = new ArrayList<MovieDB>();
        String Title;
        String Posterpath = null;
        String Overview;
        Number vote_average;
        String release_date;
        int movieID;
        boolean hasVideo;

        final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185/";


        try {


            //Create JSON object to traverse
            JSONObject j = new JSONObject(json);

            //
            JSONArray moviearray = j.getJSONArray("results");
            MovieDB movielist =  new MovieDB();

            for (int i = 0; i < moviearray.length(); i++){

                JSONObject results = moviearray.getJSONObject(i);

                //Go down one level in order to access
                movielist.setTitle(results.getString("title"));
                movielist.setPosterpath(IMAGE_BASE_URL + results.getString("poster_path"));
                movielist.setOverview(results.getString("overview"));
                movielist.setVote_average(results.getInt("vote_average"));
                movielist.setRelease_date(results.getString("release_date"));
                movielist.setMovieID(results.getInt("id"));
                movielist.setHasVideo(results.getBoolean("video"));

                //Add the parsed results to the Array
                movieresults.add(movielist);

               movielist = new MovieDB();

            }


        }


        //error catching
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        catch(JSONException ex){
            ex.printStackTrace();
        }

        //MovieDB API results
        return movieresults;
    }
}
