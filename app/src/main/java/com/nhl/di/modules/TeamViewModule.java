package com.nhl.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.nhl.domain.model.factory.TeamViewModel;
import com.nhl.domain.model.factory.ViewModelProviderFactory;
import com.nhl.domain.services.impl.TeamService;
import com.nhl.domain.services.impl.TeamServiceImpl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TeamViewModule {

    @Provides
    @Named("TeamViewModel")
    ViewModelProvider.Factory providesTeamViewModelProvider(TeamViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    TeamViewModel providesTeamViewModel(TeamService team) {
        return new TeamViewModel(team);
    }
}
