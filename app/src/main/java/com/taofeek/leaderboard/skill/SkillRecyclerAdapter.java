package com.taofeek.leaderboard.skill;


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

public class SkillRecyclerAdapter extends RecyclerView.Adapter<SkillRecyclerAdapter.MyViewHolder> {
    List<SkillModelData> skillIQLeadersDataList;
    Context context;

    public SkillRecyclerAdapter(List<SkillModelData> skillIQLeadersDataList, Context context) {
        this.skillIQLeadersDataList = skillIQLeadersDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.skill_list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(skillIQLeadersDataList.get(position).getName());
        String scorer = String.valueOf(skillIQLeadersDataList.get(position).getScore()) + " skill IQ Score, " ;
        holder.score.setText(scorer);
        holder.country.setText(skillIQLeadersDataList.get(position).getCountry());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(skillIQLeadersDataList.get(position).getBadgeUrl())
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked" + skillIQLeadersDataList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return skillIQLeadersDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView country;
        TextView score;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =  itemView.findViewById(R.id.skill_badge_image);
            name =  itemView.findViewById(R.id.skill_name);
            country =  itemView.findViewById(R.id.skill_country);
            score =  itemView.findViewById(R.id.skill_iq);
            cardView =  itemView.findViewById(R.id.skill_card_view);
        }
    }
}
