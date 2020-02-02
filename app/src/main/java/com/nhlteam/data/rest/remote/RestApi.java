package com.nhlteam.data.rest.remote;

import com.nhlteam.model.team.Teams;
import com.nhlteam.model.team.people.PeopleDetails;
import com.nhlteam.model.team.roster.TeamRoster;

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

