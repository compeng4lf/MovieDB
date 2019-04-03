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


    final static String APIKEY = "";


    final static String BASE_URL = "https://api.themoviedb.org/3/movie/popular?";


    final static String SORTBY_PARAM = "sort_by";
    final static String API_PARAM = "api_key";
    final static String INCLUDEVIDEO_PARAM = "include_video";
    final static String LANGUAGE_PARAM = "language";
    final static String INCLUDEADULT_PARAM = "include_adult";
    final static String PAGE_PARAM = "page";

    final static String popularsort = "popularity.desc";
    final static String topcountsort = "vote_count.desc";
    final static String optionaldefaults = "false";
    final static String language = "en-US";
    final static String page = "1";


    public static URL buildUrl() {

        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(API_PARAM, APIKEY)
                .appendQueryParameter(LANGUAGE_PARAM, language)
                //.appendQueryParameter(SORTBY_PARAM, popularsort)
                //.appendQueryParameter(INCLUDEADULT_PARAM, optionaldefaults)
                //.appendQueryParameter(INCLUDEVIDEO_PARAM, optionaldefaults)
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