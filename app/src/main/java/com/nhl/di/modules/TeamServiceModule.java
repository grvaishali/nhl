package com.nhl.di.modules;

import com.nhl.domain.repositories.TeamRepository;
import com.nhl.domain.services.impl.TeamService;
import com.nhl.domain.services.impl.TeamServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class TeamServiceModule {
    @Provides
    TeamService providesTeamServiceImplModel(TeamRepository repository) {
        return new TeamServiceImpl(repository);
    }
}
