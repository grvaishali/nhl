package com.nhlteam.data.team;

public class Franchise {
    private int franchiseId;
    private String teamName;
    private String link;

    public int getFranchiseId() {
        return franchiseId;
    }

    public void setFranchiseId(int franchiseId) {
        this.franchiseId = franchiseId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
