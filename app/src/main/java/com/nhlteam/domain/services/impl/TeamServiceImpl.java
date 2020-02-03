package com.nhlteam.domain.services.impl;

import com.nhlteam.domain.repositories.TeamRepository;
import com.nhlteam.data.team.Teams;
import com.nhlteam.data.people.PeopleDetails;
import com.nhlteam.data.roster.TeamRoster;

import javax.inject.Inject;

import retrofit2.Call;

public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    @Inject
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;

    }

    @Override
    public Call<Teams> getTeam() {
        return teamRepository.getTeam();
    }

    @Override
    public Call<TeamRoster> getTeamRoster(int teamId) {
        return teamRepository.getTeamRoster(teamId);
    }

    @Override
    public Call<PeopleDetails> getPeople(int personId) {
        return teamRepository.getPeople(personId);
    }
}
