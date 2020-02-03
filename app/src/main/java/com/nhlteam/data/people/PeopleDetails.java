package com.nhlteam.data.people;

import java.util.List;

import javax.inject.Inject;

public class PeopleDetails {

    /**
     * Dagger Support
     */
    @Inject
    public PeopleDetails() {
    }

    private List<People> people;
    private String copyright;

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
