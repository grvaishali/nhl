package com.nhl.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.nhl.R;
import com.nhl.constants.NHLConstants;
import com.nhl.model.team.Team;
import com.nhl.presentation.ImageUtil;
import com.nhl.presentation.navigation.ui.home.HomeFragment;
import com.nhl.presentation.start.MainActivity;
import com.nhl.presentation.team.TeamActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamsViewHolder> {
    private List<Team> teams;
    private Context context;



    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_teams, parent, false);
        TeamsViewHolder teamsViewHolder = new TeamsViewHolder(view);
        return teamsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, final int listPosition) {
        final Context imageContext = holder.imageViewIcon.getContext();
        TextView textViewDescription = holder.textViewDescription;
        ImageView imageViewIcon = holder.imageViewIcon;
        CardView view = holder.parentCardView;
        textViewDescription.setText(teams.get(listPosition).getName());


        holder.parentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(NHLConstants.TEAM_ID, teams.get(listPosition).getId());
                context.startActivity(intent);

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

    public TeamAdapter(List<Team> teams, Context context) {
        this.teams = teams;
        this.context = context;
    }
}