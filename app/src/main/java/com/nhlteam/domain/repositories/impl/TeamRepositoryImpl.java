package com.nhlteam.domain.repositories.impl;

import com.nhlteam.rest.NHLRestService;
import com.nhlteam.domain.repositories.TeamRepository;
import com.nhlteam.data.team.Teams;
import com.nhlteam.data.people.PeopleDetails;
import com.nhlteam.data.roster.TeamRoster;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Retrofit;

public class TeamRepositoryImpl implements TeamRepository {

    @Inject
    Retrofit retrofit;

    private NHLRestService api;

    @Inject
    public TeamRepositoryImpl(NHLRestService api) {
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
