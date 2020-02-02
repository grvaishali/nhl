package com.nhl.domain.repositories;

import com.nhl.model.team.Teams;
import com.nhl.model.team.people.People;
import com.nhl.model.team.people.PeopleDetails;
import com.nhl.model.team.roster.TeamRoster;

import retrofit2.Call;

public interface TeamRepository {

    Call<Teams> getTeam();

    Call<TeamRoster> getTeamRoster(int teamId);

    Call<PeopleDetails> getPeople(int personId);

}
