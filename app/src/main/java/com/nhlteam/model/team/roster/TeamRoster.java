package com.nhlteam.model.team.roster;

import java.util.List;

import javax.inject.Inject;

public class TeamRoster {
    @Inject
    public TeamRoster() {
    }
    String copyright;
    String link;
    List<Roster> roster;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Roster> getRoster() {
        return roster;
    }

    public void setRoster(List<Roster> roster) {
        this.roster = roster;
    }
}
