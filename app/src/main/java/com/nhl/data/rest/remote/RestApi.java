package com.nhl.data.rest.remote;

import com.nhl.model.team.Teams;
import com.nhl.model.team.people.People;
import com.nhl.model.team.people.PeopleDetails;
import com.nhl.model.team.roster.Roster;
import com.nhl.model.team.roster.TeamRoster;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApi {

    @GET("teams")
    Call<Teams> getTeams();

    @GET("teams/{teamId}/roster")
    Call<TeamRoster> getTeamRoster(@Path("teamId") int teamId);

    @GET("people/{personId}")
    Call<PeopleDetails> getPeople(@Path("personId")int personId);




}

