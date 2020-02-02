package com.nhl.presentation.navigation.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhl.R;
import com.nhl.domain.model.factory.TeamViewModel;
import com.nhl.model.team.roster.TeamRoster;
import com.nhl.presentation.adapter.TeamPlayersAdapter;
import com.nhl.presentation.start.MainActivity;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    @BindView(R.id.teamPlayers)
    RecyclerView teamPlayerView;

    @Inject
    TeamViewModel teamViewModel;

    @Inject
    @Named("TeamViewModel")
    ViewModelProvider.Factory factory;

    private HomeViewModel homeViewModel;

    @Inject
    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
       // teamViewModel= ViewModelProviders.of(this).get(TeamViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//
//            }
//        });
        //getTeamPlayers(1);
        return view;
    }

    Call<TeamRoster> teamRosterCall;

    public void getTeamPlayers(int position) {
        teamRosterCall = teamViewModel.teamService.getTeamRoster(position);

        teamRosterCall.enqueue(new Callback<TeamRoster>() {
            @Override
            public void onResponse
                    (Call<TeamRoster> call, Response<TeamRoster> response) {
                if (response.isSuccessful()) {
                    TeamRoster teamRoster = response.body();
                    ;
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    teamPlayerView.setLayoutManager(layoutManager);
                    teamPlayerView.setItemAnimator(new DefaultItemAnimator());
                    TeamPlayersAdapter teamPlayersAdapter = new TeamPlayersAdapter(teamRoster.getRoster(),getActivity());
                    teamPlayerView.setAdapter(teamPlayersAdapter);


                } else {
                    Log.e("Team Players Call", response.errorBody().toString(), null);
                }
            }

            @Override
            public void onFailure(Call<TeamRoster> call, Throwable t) {
                Log.e("Team Players Call", t.getMessage(), t);
            }

        });
    }
}