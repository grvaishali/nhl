package com.nhl.presentation.navigation.ui.home;

import androidx.lifecycle.ViewModel;

import com.nhl.domain.services.impl.TeamService;

public class HomeViewModel extends ViewModel {

    public TeamService teamService;


    public HomeViewModel(TeamService teamService) {
        this.teamService = teamService;

    }

}