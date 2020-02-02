package com.nhlteam.model.team.roster;

import javax.inject.Inject;

public class Person {

    @Inject
    public Person() {
    }

    private Integer id;

    private String fullName;

    private String link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
