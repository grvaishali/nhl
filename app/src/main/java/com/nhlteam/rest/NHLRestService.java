package com.nhlteam.rest;

import com.nhlteam.data.team.Teams;
import com.nhlteam.data.people.PeopleDetails;
import com.nhlteam.data.roster.TeamRoster;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NHLRestService {

    @GET("teams")
    Call<Teams> getTeams();

    @GET("teams/{teamId}/roster")
    Call<TeamRoster> getTeamRoster(@Path("teamId") int teamId);

    @GET("people/{personId}")
    Call<PeopleDetails> getPeople(@Path("personId")int personId);




}

