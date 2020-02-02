package com.nhl.model.team.people;

import java.util.List;

import javax.inject.Inject;

public class PeopleDetails {
    @Inject
    public PeopleDetails() {
    }

    List<People> people;
    String copyright;

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
