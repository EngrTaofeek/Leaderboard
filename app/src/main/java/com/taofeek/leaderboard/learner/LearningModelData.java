package com.taofeek.leaderboard.learner;

import com.google.gson.annotations.SerializedName;

public class LearningModelData {
    @SerializedName("name")
    private String name;
    @SerializedName("badgeUrl")
    private String badgeUrl;
    @SerializedName("hours")
    private int hours;
    @SerializedName("country")
    private String country;


    public LearningModelData(String name, int hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }
}
