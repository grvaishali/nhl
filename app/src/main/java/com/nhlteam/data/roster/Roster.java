package com.nhlteam.data.roster;

import javax.inject.Inject;

public class Roster {

    @Inject
    public Roster() {
    }

    private Person person;

    private String jerseyNumber;

    private Position position;


    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
