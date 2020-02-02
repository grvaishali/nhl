package com.nhlteam.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.nhlteam.domain.model.factory.TeamViewModel;
import com.nhlteam.domain.model.factory.ViewModelProviderFactory;
import com.nhlteam.domain.services.impl.TeamService;

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
