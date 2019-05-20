package com.example.android.popularmovies.Formats;

import java.io.Serializable;
import java.util.List;

public class MovieDB implements Serializable {

    private String Title;
    private String Posterpath;
    private String Overview;
    private Number vote_average;
    private String release_date;
    private int movieID;
    private boolean hasVideo;


    public MovieDB() {
    }

    public MovieDB(String Title, String Posterpath, String Overview, Number vote_average, String release_date, int movieID, boolean hasVideo) {
        this.Title = Title;
        this.Posterpath = Posterpath;
        this.Overview = Overview;
        this.vote_average = vote_average;
        this.release_date = release_date;
        this.movieID = movieID;
        this.hasVideo = hasVideo;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getPosterpath() {
        return Posterpath;
    }

    public void setPosterpath(String Posterpath) {
        this.Posterpath = Posterpath;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String Overview) {
        this.Overview = Overview;
    }

    public int getVote_average() {
        return vote_average.intValue();
    }

    public void setVote_average(Number vote_average) {
        this.vote_average = vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getMovieID() {return movieID;}

    public void setMovieID(int movieID) {this.movieID = movieID;}

    public boolean getHasVideo() { return hasVideo; }

    public void setHasVideo(boolean hasVideo) {this.hasVideo = hasVideo;}
}
