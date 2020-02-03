package com.nhlteam.di.module;

import com.nhlteam.domain.repositories.TeamRepository;
import com.nhlteam.domain.services.impl.TeamService;
import com.nhlteam.domain.services.impl.TeamServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class TeamServiceModule {
    @Provides
    TeamService providesTeamServiceImplModel(TeamRepository repository) {
        return new TeamServiceImpl(repository);
    }
}
