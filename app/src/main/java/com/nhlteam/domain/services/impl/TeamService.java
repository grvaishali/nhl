package com.nhlteam.domain.services.impl;

import com.nhlteam.data.team.Teams;
import com.nhlteam.data.people.PeopleDetails;
import com.nhlteam.data.roster.TeamRoster;

import retrofit2.Call;

public interface TeamService {
    Call<Teams> getTeam();
    Call<TeamRoster> getTeamRoster(int teamId);
    Call<PeopleDetails> getPeople(int personId);
}
