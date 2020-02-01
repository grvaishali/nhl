package com.nhl.domain.repositories.impl;

import com.nhl.data.rest.remote.RestApi;
import com.nhl.domain.repositories.TeamRepository;
import com.nhl.model.team.Team;
import com.nhl.model.team.Teams;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

public class TeamRepositoryImpl implements TeamRepository {

    @Inject
    Retrofit retrofit;


    RestApi api;

    @Inject
    public TeamRepositoryImpl(RestApi api) {
        this.api = api;

    }

    @Override
    public Call<Teams> getTeam() {
        return api.getTeams();
    }
}
