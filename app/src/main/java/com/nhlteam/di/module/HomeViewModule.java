package com.nhlteam.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.nhlteam.domain.factory.ViewModelProviderFactory;
import com.nhlteam.domain.services.impl.TeamService;
import com.nhlteam.domain.viewmodel.PlayersViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeViewModule {
    @Provides
    @Named("PlayersViewModel")
    ViewModelProvider.Factory providesHomeViewModelProvider(PlayersViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    PlayersViewModel providesHomeViewModel(TeamService team) {
        return new PlayersViewModel(team);
    }

}
