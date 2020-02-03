package com.nhlteam.presentation.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.nhlteam.R;
import com.nhlteam.constant.NHLConstants;
import com.nhlteam.data.team.Team;
import com.nhlteam.util.ConfigUtil;
import com.nhlteam.util.ImageUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter to hold and display the list of teams.
 */
public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {
    private List<Team> teams;
    private Context context;
    private LiveData<Team> currentTeamLiveData;

    @Inject
    public TeamsAdapter() {

    }

    public TeamsAdapter(List<Team> teams, LiveData<Team> currentTeamLiveData, Context context) {
        this.teams = teams;
        this.context = context;
        this.currentTeamLiveData = currentTeamLiveData;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_teams, parent, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder teamsViewHolder, final int listPosition) {
        //Get references
        final Context teamLogoImageContext = teamsViewHolder.teamLogoImageView.getContext();
        TextView nameTextView = teamsViewHolder.nameTextView;
        ImageView teamLogoImage = teamsViewHolder.teamLogoImageView;

        //Set data
        nameTextView.setText(teams.get(listPosition).getName());

        //Attach on click listener
        teamsViewHolder.teamCardView.setOnClickListener(view -> ((MutableLiveData<Team>) currentTeamLiveData).setValue((teams.get(listPosition))));
        try {
            ImageUtil.fetchSvg(teamLogoImageContext, (ConfigUtil.getProperty(NHLConstants.TEAM_LOGO_BASE_URL,teamLogoImageContext) + teams.get(listPosition).getId()) + ConfigUtil.getProperty(NHLConstants.TEAM_LOGO_SUFFIX,teamLogoImageContext), teamLogoImage);
        } catch (Exception e) {
            Log.e("LoadTeamLogo", e.getMessage(), e);
        }

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public static class TeamsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_teams_textView_teamName)
        TextView nameTextView;
        @BindView(R.id.list_teams_imageView_teamLogo)
        ImageView teamLogoImageView;
        @BindView(R.id.team_card_view)
        CardView teamCardView;

        public TeamsViewHolder(View teamView) {
            super(teamView);
            ButterKnife.bind(this, teamView);
        }
    }

}