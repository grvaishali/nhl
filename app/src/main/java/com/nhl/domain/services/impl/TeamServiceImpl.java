package com.nhl.domain.services.impl;

import com.nhl.domain.repositories.TeamRepository;
import com.nhl.model.team.Teams;
import com.nhl.model.team.people.People;
import com.nhl.model.team.people.PeopleDetails;
import com.nhl.model.team.roster.TeamRoster;

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
