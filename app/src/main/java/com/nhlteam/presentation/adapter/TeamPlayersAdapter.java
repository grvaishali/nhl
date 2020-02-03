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
import com.nhlteam.constant.NHLConstants;
import com.nhlteam.data.roster.Roster;
import com.nhlteam.presentation.activity.PlayerActivity;
import com.nhlteam.util.ConfigUtil;
import com.nhlteam.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter to hold and display the list of players.
 */
public class TeamPlayersAdapter extends RecyclerView.Adapter<TeamPlayersAdapter.TeamPlayerViewHolder> {
    private List<Roster> rosters;
    private List<Roster> modifiedRosters;
    private Context context;

    public TeamPlayersAdapter(List<Roster> rosters, Context context) {
        this.rosters = rosters;
        this.modifiedRosters = new ArrayList<>(rosters);
        this.context = context;
    }

    @NonNull
    @Override
    public TeamPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team, parent, false);
        return new TeamPlayersAdapter.TeamPlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamPlayerViewHolder teamPlayerViewHolder, int listPosition) {
        //Get references
        TextView descriptionTextView = teamPlayerViewHolder.nameTextView;
        EditText positionEditText = teamPlayerViewHolder.positionEditText;
        TextView numberTextView = teamPlayerViewHolder.numberTextView;
        ImageView playerImageView = teamPlayerViewHolder.playerImageView;

        //Get current item
        Roster roster = modifiedRosters.get(listPosition);

        //Fill required data
        numberTextView.setText(roster.getJerseyNumber());
        descriptionTextView.setText(roster.getPerson().getFullName());
        positionEditText.setText(roster.getPosition().getName());
        try {
            ImageUtil.fetchJpg(playerImageView.getContext(), (ConfigUtil.getProperty(NHLConstants.PLAYER_IMAGE_BASE_URL, playerImageView.getContext()) + roster.getPerson().getId()) + ConfigUtil.getProperty(NHLConstants.PLAYER_IMAGE_SUFFIX, playerImageView.getContext()), playerImageView);
        } catch (Exception e) {
            Log.e("LoadPersonImage", e.getMessage(), e);
        }

        //Attach on click listener
        teamPlayerViewHolder.playerCardView.setOnClickListener(view -> {
            Intent intent = new Intent(context, PlayerActivity.class);
            context.startActivity(intent);

        });


    }

    @Override
    public int getItemCount() {
        return modifiedRosters.size();
    }

    public static class TeamPlayerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fragment_players_textView_headerName)
        TextView nameTextView;
        @BindView(R.id.team_imageView_player)
        ImageView playerImageView;
        @BindView(R.id.fragment_players_textView_headerPosition)
        EditText positionEditText;
        @BindView(R.id.fragment_players_button_headerNumber)
        TextView numberTextView;
        @BindView(R.id.teamPlayer_card_view)
        CardView playerCardView;


        public TeamPlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    /**
     * Sorts the players list by name
     *
     * @param ascending
     */
    public void sortByName(boolean ascending) {
        this.modifiedRosters.sort((o1, o2) -> {
            if (ascending) {
                return o1.getPerson().getFullName().compareTo(o2.getPerson().getFullName());
            } else {
                return o2.getPerson().getFullName().compareTo(o1.getPerson().getFullName());
            }
        });
        this.rosters = new ArrayList<>(modifiedRosters);
        notifyDataSetChanged();
    }

    /**
     * Sorts the player list by numberTextView
     *
     * @param ascending
     */
    public void sortByNumber(boolean ascending) {
        this.modifiedRosters.sort((o1, o2) -> {
            if (ascending) {
                return o1.getJerseyNumber().compareTo(o2.getJerseyNumber());
            } else {
                return o2.getJerseyNumber().compareTo(o1.getJerseyNumber());
            }
        });
        this.rosters = new ArrayList<>(modifiedRosters);
        notifyDataSetChanged();
    }

    /**
     * Filters the players list by positionEditText
     *
     * @param filterTerm
     */
    public void filterPlayers(String filterTerm) {
        modifiedRosters = rosters.stream().filter(roster -> roster.getPosition().getName().toLowerCase().contains(filterTerm.toLowerCase())).collect(Collectors.toList());
        notifyDataSetChanged();
    }
}