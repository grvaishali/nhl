package com.nhlteam.model.team;

import java.util.List;

import javax.inject.Inject;

public class Teams {

    @Inject
    public Teams() {
    }

    List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
