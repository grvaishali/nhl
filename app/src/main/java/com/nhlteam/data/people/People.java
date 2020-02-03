package com.nhlteam.data.people;

import javax.inject.Inject;

public class People {

    /**
     * Dagger support
     */
    @Inject
    public People() {
    }

    private Integer id;

    private String fullName;

    private String link;

    private String firstName;

    private String lastName;

    private String primaryNumber;

    private String birthDate;

    private Integer currentAge;

    private String birthCity;

    private String birthStateProvince;

    private String birthCountry;

    private String nationality;

    private String height;

    private Integer weight;

    private Boolean active;

    private Boolean alternateCaptain;

    private Boolean captain;

    private Boolean rookie;

    private String shootsCatches;

    private String rosterStatus;

    private CurrentTeam currentTeam;

    private PrimaryPosition primaryPosition;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrimaryNumber() {
        return primaryNumber;
    }

    public void setPrimaryNumber(String primaryNumber) {
        this.primaryNumber = primaryNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(Integer currentAge) {
        this.currentAge = currentAge;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }

    public String getBirthStateProvince() {
        return birthStateProvince;
    }

    public void setBirthStateProvince(String birthStateProvince) {
        this.birthStateProvince = birthStateProvince;
    }

    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getAlternateCaptain() {
        return alternateCaptain;
    }

    public void setAlternateCaptain(Boolean alternateCaptain) {
        this.alternateCaptain = alternateCaptain;
    }

    public Boolean getCaptain() {
        return captain;
    }

    public void setCaptain(Boolean captain) {
        this.captain = captain;
    }

    public Boolean getRookie() {
        return rookie;
    }

    public void setRookie(Boolean rookie) {
        this.rookie = rookie;
    }

    public String getShootsCatches() {
        return shootsCatches;
    }

    public void setShootsCatches(String shootsCatches) {
        this.shootsCatches = shootsCatches;
    }

    public String getRosterStatus() {
        return rosterStatus;
    }

    public void setRosterStatus(String rosterStatus) {
        this.rosterStatus = rosterStatus;
    }

    public CurrentTeam getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(CurrentTeam currentTeam) {
        this.currentTeam = currentTeam;
    }

    public PrimaryPosition getPrimaryPosition() {
        return primaryPosition;
    }

    public void setPrimaryPosition(PrimaryPosition primaryPosition) {
        this.primaryPosition = primaryPosition;
    }
}
