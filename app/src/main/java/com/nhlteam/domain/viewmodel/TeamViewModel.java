package com.nhlteam.domain.viewmodel;

import androidx.lifecycle.ViewModel;

import com.nhlteam.domain.services.impl.TeamService;

import javax.inject.Inject;

public class TeamViewModel extends ViewModel {

    public TeamService teamService;

    @Inject
    public TeamViewModel(TeamService teamService) {
        this.teamService = teamService;
    }
}
