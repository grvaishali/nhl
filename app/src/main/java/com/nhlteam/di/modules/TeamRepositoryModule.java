package com.nhlteam.di.modules;

import com.nhlteam.data.rest.remote.RestApi;
import com.nhlteam.domain.repositories.TeamRepository;
import com.nhlteam.domain.repositories.impl.TeamRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class TeamRepositoryModule {

    @Provides
    TeamRepository providesTeamRepositoryImplModel(RestApi restApi) {
        return new TeamRepositoryImpl(restApi);
    }
}
