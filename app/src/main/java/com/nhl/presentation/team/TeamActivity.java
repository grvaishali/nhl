package com.nhl.presentation.team;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.nhl.domain.model.factory.TeamViewModel;
import com.nhl.model.team.Teams;
import com.nhl.presentation.AbstractNHLActivity;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamActivity extends AbstractNHLActivity<TeamViewModel> {

    @Inject
    TeamViewModel teamViewModel;

    @Inject
    @Named("TeamViewModel")
    ViewModelProvider.Factory factory;

    @Override
    public TeamViewModel getViewModel() {
        teamViewModel = ViewModelProviders.of(this, factory).get(TeamViewModel.class);
        return teamViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getViewModel();
       getTeams();
    }


    Call<Teams> teams;

    public void getTeams() {
        teams = teamViewModel.teamService.getTeam();
        teams.enqueue(new Callback<Teams>() {
            @Override
            public void onResponse
                    (Call<Teams> call, Response<Teams> response) {
                if (response.isSuccessful()) {
                    Teams convertedResponse;
                    convertedResponse = (Teams) response.body();

                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<Teams> call, Throwable t) {

            }
        });
    }

    private void bind() {
//        ActivityMainBindingImpl binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        //teamViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);
//        binding.setViewModel(teamViewModel);

    }

}
