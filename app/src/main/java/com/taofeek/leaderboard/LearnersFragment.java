package com.taofeek.leaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LearnersFragment extends Fragment {
    private static Retrofit retrofit;
    private static final String baseURL = "https://gadsapi.herokuapp.com/";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private LearnersInterface learnersInterface;
    //private ProgressBar progressBar;

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_learner, container, false);
        recyclerView = v.findViewById(R.id.learner_recyclerview);
        layoutManager = new LinearLayoutManager(getContext());
        makeNetworkRequest();

        return v;
    }

    public void makeNetworkRequest() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        learnersInterface = retrofit.create(LearnersInterface.class);
        Call<List<LearningModelData>> listCall = learnersInterface.getLearningLeadersData();
        listCall.enqueue(new Callback<List<LearningModelData>>() {
            @Override
            public void onResponse(Call<List<LearningModelData>> call, Response<List<LearningModelData>> response) {
                //progressBar.setVisibility(View.GONE);
                loadRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<LearningModelData>> call, Throwable t) {
                //progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    public void loadRecyclerView(List<LearningModelData> content){
        adapter = new LearnersRecyclerAdapter(content, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

    }
}
