package com.taofeek.leaderboard;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

interface SubmitInterface {
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> postProjectData(
            @Field("entry.1877115667") String fist_name,
            @Field("entry.2006916086") String last_name,
            @Field("entry.1824927963") String email,
            @Field("entry.284483984") String link
    );
}
