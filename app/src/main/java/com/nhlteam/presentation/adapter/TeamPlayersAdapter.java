package com.nhlteam.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nhlteam.R;
import com.nhlteam.model.team.roster.Roster;
import com.nhlteam.presentation.ImageUtil;
import com.nhlteam.presentation.team.TeamActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamPlayersAdapter extends RecyclerView.Adapter<TeamPlayersAdapter.TeamPlayerViewHolder> {
    private List<Roster> rosters;
    private List<Roster> modifiedRosters;
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
        int rowPos = holder.getAdapterPosition();
        TextView textViewDescription = holder.textViewDescription;
        EditText position = holder.position;
        TextView number = holder.number;
        ImageView image = holder.image;
        CardView view = holder.parentCardView;
        Roster roster = modifiedRosters.get(listPosition);
        number.setText(roster.getJerseyNumber());
        textViewDescription.setText(roster.getPerson().getFullName());
        position.setText(roster.getPosition().getName());
        try {
            ImageUtil.fetchJpg(image.getContext(), ("https://nhl.bamcontent.com/images/headshots/current/168x168/" + roster.getPerson().getId()) + ".jpg", image);
        } catch (Exception e) {
            Log.e("LoadPersonImage", e.getMessage(), e);
        }


        holder.parentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TeamActivity.class);
                context.startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {
        return modifiedRosters.size();
    }

    public static class TeamPlayerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.teamPlayer_name)
        TextView textViewDescription;
        @BindView(R.id.teamPlayer_image)
        ImageView image;
        @BindView(R.id.teamPlayer_position)
        EditText position;
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
        this.modifiedRosters = new ArrayList<>(rosters);
        this.context = context;
    }

    public void sortByName(boolean ascending) {
        this.modifiedRosters.sort(new Comparator<Roster>() {
            @Override
            public int compare(Roster o1, Roster o2) {
                if (ascending) {
                    return o1.getPerson().getFullName().compareTo(o2.getPerson().getFullName());
                } else {
                    return o2.getPerson().getFullName().compareTo(o1.getPerson().getFullName());
                }
            }
        });
        this.rosters = new ArrayList<>(modifiedRosters);
        notifyDataSetChanged();
    }

    public void sortByNumber(boolean ascending) {
        this.modifiedRosters.sort(new Comparator<Roster>() {
            @Override
            public int compare(Roster o1, Roster o2) {
                if (ascending) {
                    return o1.getJerseyNumber().compareTo(o2.getJerseyNumber());
                } else {
                    return o2.getJerseyNumber().compareTo(o1.getJerseyNumber());
                }
            }
        });
        this.rosters = new ArrayList<>(modifiedRosters);
        notifyDataSetChanged();
    }

    public void filterPlayers(String filterTerm) {
        modifiedRosters = rosters.stream().filter(roster -> roster.getPosition().getName().toLowerCase().contains(filterTerm.toLowerCase())).collect(Collectors.toList());
        notifyDataSetChanged();
    }
}