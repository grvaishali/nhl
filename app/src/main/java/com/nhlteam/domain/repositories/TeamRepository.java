package com.nhlteam.domain.repositories;

import com.nhlteam.model.team.Teams;
import com.nhlteam.model.team.people.PeopleDetails;
import com.nhlteam.model.team.roster.TeamRoster;

import retrofit2.Call;

public interface TeamRepository {

    Call<Teams> getTeam();

    Call<TeamRoster> getTeamRoster(int teamId);

    Call<PeopleDetails> getPeople(int personId);

}
