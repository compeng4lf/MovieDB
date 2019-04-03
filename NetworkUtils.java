package com.example.android.popularmovies;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

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


        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendPath(sortby)
                .appendQueryParameter(API_PARAM, APIKEY)
                .appendQueryParameter(LANGUAGE_PARAM, language)
                .appendQueryParameter(PAGE_PARAM, page)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        String inputLine="";


        try {
            int status = urlConnection.getResponseCode();

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