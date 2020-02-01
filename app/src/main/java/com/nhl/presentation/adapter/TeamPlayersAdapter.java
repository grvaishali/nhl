package com.nhl.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nhl.R;
import com.nhl.model.team.roster.Person;
import com.nhl.model.team.roster.Roster;

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
        TextView textViewDescription = holder.textViewDescription;
        CardView view = holder.parentCardView;
        textViewDescription.setText(rosters.get(listPosition).getPerson().getFullName());

    }


    @Override
    public int getItemCount() {
        return rosters.size();
    }

    public static class TeamPlayerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.teamPlayer_name)
        TextView textViewDescription;
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