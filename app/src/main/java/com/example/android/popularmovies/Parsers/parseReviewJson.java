package com.example.android.popularmovies.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.example.android.popularmovies.Formats.MovieDB;
import com.example.android.popularmovies.Formats.MovieReviewsDB;

public class parseReviewJson {

    public static ArrayList<MovieReviewsDB> parseReviewJson(String json) {

        ArrayList<MovieReviewsDB> movieReviewsResults = new ArrayList<MovieReviewsDB>();
        String mAuthor;
        String mContent;;
        String mURL;

        try {

            //Create JSON object to traverse
            JSONObject j = new JSONObject(json);

            JSONArray reviewArray = j.getJSONArray("results");
            MovieReviewsDB reviewsList =  new MovieReviewsDB();

            for (int i = 0; i < reviewArray.length(); i++){

                JSONObject results = reviewArray.getJSONObject(i);

                //Go down one level in order to access
                reviewsList.setAuthor(results.getString("author"));
                reviewsList.setContent(results.getString("content"));
                reviewsList.setUrl(results.getString("url"));

                //Add the parsed results to the Array
                movieReviewsResults.add(reviewsList);

                reviewsList = new MovieReviewsDB();

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
        return movieReviewsResults;
    }
}
