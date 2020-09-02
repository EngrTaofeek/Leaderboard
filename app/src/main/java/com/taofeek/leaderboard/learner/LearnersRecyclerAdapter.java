package com.taofeek.leaderboard.learner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.taofeek.leaderboard.R;

import java.util.List;

public class LearnersRecyclerAdapter extends RecyclerView.Adapter<LearnersRecyclerAdapter.MyViewHolder> {
    List<LearningModelData> learningLeadersDataList;
    Context context;

    public LearnersRecyclerAdapter(List<LearningModelData> learningLeadersDataList, Context context) {
        this.learningLeadersDataList = learningLeadersDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.learner_list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(learningLeadersDataList.get(position).getName());
        holder.hours.setText(String.valueOf(learningLeadersDataList.get(position).getHours()) + " Learning Hours, ");
        holder.country.setText(learningLeadersDataList.get(position).getCountry());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(learningLeadersDataList.get(position).getBadgeUrl())
                .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked" + learningLeadersDataList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return learningLeadersDataList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView hours;
        TextView country;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.learner_badge_image);
            name =  itemView.findViewById(R.id.learner_name);
            hours = itemView.findViewById(R.id.learning_hours);
            country = itemView.findViewById(R.id.country);
            cardView = itemView.findViewById(R.id.learning_card_view);
        }
    }
}
