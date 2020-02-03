package com.nhlteam.domain.repositories;

import com.nhlteam.data.team.Teams;
import com.nhlteam.data.people.PeopleDetails;
import com.nhlteam.data.roster.TeamRoster;

import retrofit2.Call;

public interface TeamRepository {

    Call<Teams> getTeam();

    Call<TeamRoster> getTeamRoster(int teamId);

    Call<PeopleDetails> getPeople(int personId);

}
