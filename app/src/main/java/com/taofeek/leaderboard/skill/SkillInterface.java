package com.taofeek.leaderboard.skill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillInterface {
    @GET("api/skilliq")
    Call<List<SkillModelData>> getSkillIQData();
}
