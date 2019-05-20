package com.example.android.popularmovies.Parsers;
import com.example.android.popularmovies.Formats.MovieTrailerDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class parseTrailerJson {

    public static ArrayList<MovieTrailerDB> parseReviewJson(String json) {

        ArrayList<MovieTrailerDB> movieTrailerResults = new ArrayList<MovieTrailerDB>();
        String mKey;
        String mName;;
        String mSite;
        String mType;

        try {

            //Create JSON object to traverse
            JSONObject j = new JSONObject(json);

            JSONArray reviewArray = j.getJSONArray("results");
            MovieTrailerDB TrailersList =  new MovieTrailerDB();

            for (int i = 0; i < reviewArray.length(); i++){

                JSONObject results = reviewArray.getJSONObject(i);

                //Go down one level in order to access
                TrailersList.setKey(results.getString("key"));
                TrailersList.setName(results.getString("name"));
                TrailersList.setSite(results.getString("site"));
                TrailersList.setVideoType(results.getString("type"));

                //Add the parsed results to the Array
                movieTrailerResults.add(TrailersList);

                TrailersList = new MovieTrailerDB();

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
        return movieTrailerResults;
    }
}
