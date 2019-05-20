package com.example.android.popularmovies.Formats;

public class MovieTrailerDB {

    String key;
    String name;
    String site;
    String videoType;


    public MovieTrailerDB() {
    }

    public MovieTrailerDB(String key, String name, String site, String videoType) {
        this.key = key;
        this.name = name;
        this.site = site;
        this.videoType = videoType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
}
