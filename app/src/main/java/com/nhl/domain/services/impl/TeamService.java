package com.nhl.domain.services.impl;

import com.nhl.model.team.Team;
import com.nhl.model.team.Teams;

import java.util.List;

import retrofit2.Call;

public interface TeamService {
    public Call<Teams> getTeam();
}
