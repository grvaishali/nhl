package com.nhl.domain.repositories;

import com.nhl.model.team.Team;
import com.nhl.model.team.Teams;

import java.util.List;

import retrofit2.Call;

public interface TeamRepository {

    public Call<Teams> getTeam();

}
