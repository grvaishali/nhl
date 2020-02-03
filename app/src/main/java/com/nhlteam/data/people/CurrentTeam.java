package com.nhlteam.data.people;

import javax.inject.Inject;

public class CurrentTeam {

    @Inject
    public CurrentTeam() {
    }

    private Integer id;

    private String name;

    private String link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
