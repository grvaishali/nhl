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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.nhlteam.R;
import com.nhlteam.model.team.Team;
import com.nhlteam.presentation.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamsViewHolder> {
    private List<Team> teams;
    private Context context;
    public LiveData<Team> currentTeamLiveData;
    FragmentTransaction transaction;
    ArrayList<String> nameList;

    @Inject
    public TeamAdapter() {

    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_teams, parent, false);
        TeamsViewHolder teamsViewHolder = new TeamsViewHolder(view);
        nameList = new ArrayList<>();
        return teamsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, final int listPosition) {
        final Context imageContext = holder.imageViewIcon.getContext();
        TextView textViewDescription = holder.textViewDescription;
        ImageView imageViewIcon = holder.imageViewIcon;
        CardView view = holder.parentCardView;

        textViewDescription.setText(teams.get(listPosition).getName());
        nameList.add(teams.get(listPosition).getName());

        holder.parentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MutableLiveData<Team>) currentTeamLiveData).setValue((teams.get(listPosition)));
            }
        });
        try {
            ImageUtil.fetchSvg(imageContext, ("https://www-league.nhlstatic.com/images/logos/teams-current-primary-light/" + teams.get(listPosition).getId()) + ".svg", imageViewIcon);
        } catch (Exception e) {
            Log.e("LoadBrandImage", e.getMessage(), e);
        }

    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public static class TeamsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.team_name)
        TextView textViewDescription;
        @BindView(R.id.imageView)
        ImageView imageViewIcon;
        @BindView(R.id.team_card_view)
        CardView parentCardView;

        public TeamsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public TeamAdapter(List<Team> teams, LiveData<Team> currentTeamLiveData, Context context) {
        this.teams = teams;
        this.context = context;
        this.currentTeamLiveData = currentTeamLiveData;
    }

    public MutableLiveData<Team> getCurrentTeamLiveData() {
        return (MutableLiveData<Team>) currentTeamLiveData;
    }

    public ArrayList getNameList(){
        return nameList;
    }
}