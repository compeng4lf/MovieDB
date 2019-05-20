package com.example.android.popularmovies;


import android.net.Uri;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import static android.content.ContentValues.TAG;

public class NetworkUtils {



    //API KEY USED FOR MOVIEDB
    final static String APIKEY = "" ;

    //BASE URL WE USE FOR GETTING MOVIE INFORMATION
    final static String BASE_URL = "https://api.themoviedb.org/3/movie";

    //PARAMETERS FOR THE URL
    final static String API_PARAM = "api_key";
    final static String LANGUAGE_PARAM = "language";
    final static String PAGE_PARAM = "page";
    final static String language = "en-US";
    final static String page = "1";


    public static URL buildUrl(String sortby) {

        Uri builtUri = null;
        if (sortby.contains("reviews")){
            String MovieId = sortby.replace("reviews", "");
            String Action = "reviews";
            builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(MovieId)
                    .appendPath(Action)
                    .appendQueryParameter(API_PARAM, APIKEY)
                    .appendQueryParameter(LANGUAGE_PARAM, language)
                    .appendQueryParameter(PAGE_PARAM, page)
                    .build();

        }
        else if (sortby.contains("videos")){
            String MovieId = sortby.replace("videos", "");
            String Action = "videos";
            builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(MovieId)
                    .appendPath(Action)
                    .appendQueryParameter(API_PARAM, APIKEY)
                    .appendQueryParameter(LANGUAGE_PARAM, language)
                    // .appendQueryParameter(PAGE_PARAM, page)
                    .build();
        }
        else {

            //Build URL for MovieDB API
            builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(sortby)
                    .appendQueryParameter(API_PARAM, APIKEY)
                    .appendQueryParameter(LANGUAGE_PARAM, language)
                    .appendQueryParameter(PAGE_PARAM, page)
                    .build();

        }

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {

        //HTTP Fetch MovieDB
        String inputLine="";
        HttpURLConnection urlConnection = null;

        try {

            urlConnection = (HttpURLConnection) url.openConnection();
            //int status = urlConnection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            inputLine = in.readLine();

            }
            catch (IOException e){
                Log.d(TAG,e.getMessage());
            }
        finally{
            urlConnection.disconnect();
        }
        return inputLine;
    }
}