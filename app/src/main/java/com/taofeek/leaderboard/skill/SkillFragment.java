package com.taofeek.leaderboard.skill;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.taofeek.leaderboard.R;
import com.taofeek.leaderboard.learner.LearnersInterface;
import com.taofeek.leaderboard.learner.LearnersRecyclerAdapter;
import com.taofeek.leaderboard.learner.LearningModelData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillFragment extends Fragment {
    private static Retrofit retrofit;
    private static final String baseURL = "https://gadsapi.herokuapp.com/";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SkillInterface skillInterface;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_skill, container, false);
        recyclerView = v.findViewById(R.id.skill_recyclerview);
        progressBar = v.findViewById(R.id.progressBar);
        layoutManager = new LinearLayoutManager(getContext());
        makeNetworkRequest();

        return v;
    }
    public void makeNetworkRequest() {
        progressBar.setVisibility(View.VISIBLE);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        skillInterface = retrofit.create(SkillInterface.class);
        Call<List<SkillModelData>> listCall = skillInterface.getSkillIQData();
        listCall.enqueue(new Callback<List<SkillModelData>>() {
            @Override
            public void onResponse(Call<List<SkillModelData>> call, Response<List<SkillModelData>> response) {
                progressBar.setVisibility(View.GONE);
                loadRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillModelData>> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    public void loadRecyclerView(List<SkillModelData> content){
        adapter = new SkillRecyclerAdapter(content, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

    }
}