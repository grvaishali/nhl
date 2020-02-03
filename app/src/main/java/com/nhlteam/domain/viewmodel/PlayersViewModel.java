package com.nhlteam.domain.viewmodel;

import androidx.lifecycle.ViewModel;

import com.nhlteam.domain.services.impl.TeamService;

public class PlayersViewModel extends ViewModel {

    public TeamService teamService;

    public PlayersViewModel(TeamService teamService) {
        this.teamService = teamService;

    }

}