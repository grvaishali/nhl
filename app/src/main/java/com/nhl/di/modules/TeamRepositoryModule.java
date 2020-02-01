package com.nhl.di.modules;

import com.nhl.data.rest.remote.RestApi;
import com.nhl.domain.repositories.TeamRepository;
import com.nhl.domain.repositories.impl.TeamRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class TeamRepositoryModule {

    @Provides
    TeamRepository providesTeamRepositoryImplModel(RestApi restApi) {
        return new TeamRepositoryImpl(restApi);
    }
}
