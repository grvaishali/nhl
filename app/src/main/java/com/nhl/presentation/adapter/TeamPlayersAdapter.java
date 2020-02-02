package com.nhl.presentation.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nhl.R;
import com.nhl.model.team.roster.Roster;
import com.nhl.presentation.ImageUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamPlayersAdapter extends RecyclerView.Adapter<TeamPlayersAdapter.TeamPlayerViewHolder> {
    private List<Roster> rosters;
    private Context context;


    @NonNull
    @Override
    public TeamPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teamplayers, parent, false);
        TeamPlayersAdapter.TeamPlayerViewHolder teamPalyerViewHolder = new TeamPlayersAdapter.TeamPlayerViewHolder(view);
        return teamPalyerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamPlayerViewHolder holder, int listPosition) {
        int rowPos=holder.getAdapterPosition();
        TextView textViewDescription = holder.textViewDescription;
        TextView position=holder.position;
        TextView number= holder.number;
        ImageView image=holder.image;
        CardView view = holder.parentCardView;

        if (rowPos == 0) {
            // Header Cells. Main Headings appear here
            holder.image.setBackgroundResource(R.drawable.table_header_cell_bg);
            holder.textViewDescription.setBackgroundResource(R.drawable.table_header_cell_bg);
            holder.position.setBackgroundResource(R.drawable.table_header_cell_bg);
            holder.number.setBackgroundResource(R.drawable.table_header_cell_bg);

            holder.position.setText("Position");
            holder.textViewDescription.setText("Name");
            holder.number.setText("#");

            holder.textViewDescription.setTextColor(ContextCompat.getColor(holder.textViewDescription.getContext(),R.color.colorPrimaryDark));
            holder.position.setTextColor(ContextCompat.getColor(holder.position.getContext(),R.color.colorPrimaryDark));
            holder.number.setTextColor(ContextCompat.getColor(holder.number.getContext(),R.color.colorPrimaryDark));

        }
        else{
            Roster roster =  rosters.get(listPosition);
            number.setText(roster.getJerseyNumber());
            textViewDescription.setText(roster.getPerson().getFullName());
            position.setText(roster.getPosition().getName());
            try {
                ImageUtil.fetchJpg(image.getContext(), ("https://nhl.bamcontent.com/images/headshots/current/168x168/"+roster.getPerson().getId())+".jpg", image);
            } catch (Exception e) {
                Log.e("LoadPersonImage", e.getMessage(), e);
            }

        }



    }


    @Override
    public int getItemCount() {
        return rosters.size();
    }

    public static class TeamPlayerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.teamPlayer_name)
        TextView textViewDescription;
        @BindView(R.id.teamPlayer_image)
        ImageView image;
        @BindView(R.id.teamPlayer_position)
        TextView position;
        @BindView(R.id.teamPlayer_number)
        TextView number;
        @BindView(R.id.teamPlayer_card_view)
        CardView parentCardView;


        public TeamPlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public TeamPlayersAdapter(List<Roster> rosters, Context context) {
        this.rosters = rosters;
        this.context = context;
    }
}