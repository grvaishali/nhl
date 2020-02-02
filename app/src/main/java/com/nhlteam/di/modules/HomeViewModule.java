package com.nhlteam.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.nhlteam.domain.model.factory.ViewModelProviderFactory;
import com.nhlteam.domain.services.impl.TeamService;
import com.nhlteam.presentation.navigation.ui.home.HomeViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeViewModule {
    @Provides
    @Named("HomeViewModel")
    ViewModelProvider.Factory providesHomeViewModelProvider(HomeViewModel viewModel) {
        return new ViewModelProviderFactory<>(viewModel);
    }

    @Provides
    HomeViewModel providesHomeViewModel(TeamService team) {
        return new HomeViewModel(team);
    }

}
