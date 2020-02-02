package com.nhlteam.domain.repositories.impl;

import com.nhlteam.data.rest.remote.RestApi;
import com.nhlteam.domain.repositories.TeamRepository;
import com.nhlteam.model.team.Teams;
import com.nhlteam.model.team.people.PeopleDetails;
import com.nhlteam.model.team.roster.TeamRoster;

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

    @Override
    public Call<TeamRoster> getTeamRoster(int teamId) {
        return api.getTeamRoster(teamId);
    }

    @Override
    public Call<PeopleDetails> getPeople(int personId) {
        return api.getPeople(personId);
    }
}
