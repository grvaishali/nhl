package com.nhlteam.data.team;

import java.util.List;

import javax.inject.Inject;

public class Teams {

    /**
     * Dagger Support
     */
    @Inject
    public Teams() {
    }

    private List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
