package com.nhlteam.di.module;

import com.nhlteam.rest.NHLRestService;
import com.nhlteam.domain.repositories.TeamRepository;
import com.nhlteam.domain.repositories.impl.TeamRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class TeamRepositoryModule {

    @Provides
    TeamRepository providesTeamRepositoryImplModel(NHLRestService NHLRestService) {
        return new TeamRepositoryImpl(NHLRestService);
    }
}
