package com.taofeek.leaderboard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearnersInterface {
    @GET("api/hours")
    Call<List<LearningModelData>> getLearningLeadersData();
}
