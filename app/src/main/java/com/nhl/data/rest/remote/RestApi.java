package com.nhl.data.rest.remote;

import com.nhl.model.team.Teams;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("teams")
    Call<Teams> getTeams();
}

