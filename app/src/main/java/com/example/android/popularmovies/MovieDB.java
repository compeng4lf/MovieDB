package com.example.android.popularmovies;

import java.io.Serializable;
import java.util.List;

public class MovieDB implements Serializable {

    private String Title;
    private String Posterpath;
    private String Overview;
    private Number vote_average;
    private String release_date;


    public MovieDB() {
    }

    public MovieDB(String Title, String Posterpath, String Overview, Number vote_average, String release_date) {
        this.Title = Title;
        this.Posterpath = Posterpath;
        this.Overview = Overview;
        this.vote_average = vote_average;
        this.release_date = release_date;

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

}
