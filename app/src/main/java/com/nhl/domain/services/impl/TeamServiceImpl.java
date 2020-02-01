package com.nhl.domain.services.impl;

import com.nhl.domain.repositories.TeamRepository;
import com.nhl.model.team.Teams;

import javax.inject.Inject;

import retrofit2.Call;

public class TeamServiceImpl implements TeamService {
    TeamRepository teamRepository;

    @Inject
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;

    }

    @Override
    public Call<Teams> getTeam() {
        return teamRepository.getTeam();
    }
}
