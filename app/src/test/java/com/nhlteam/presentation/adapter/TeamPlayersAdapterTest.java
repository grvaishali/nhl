package com.nhlteam.presentation.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.nhlteam.data.roster.Person;
import com.nhlteam.data.roster.Roster;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TeamPlayersAdapterTest {

    @Mock
    Context context;

    List<Roster> rosters;
    Roster roster1;
    Roster roster2;
    Person person1;
    Person person2;

    @Mock
    RecyclerView.AdapterDataObserver adapterDataObserver;

    TeamPlayersAdapter teamPlayersAdapter;

    @Before
    public void setup() {
        roster1 = new Roster();
        roster1.setJerseyNumber("1");
        person1 = new Person();
        person1.setFullName("P1");
        roster1.setPerson(person1);

        roster2 = new Roster();
        roster2.setJerseyNumber("2");
        person2 = new Person();
        person2.setFullName("P2");
        roster2.setPerson(person2);

        rosters = new ArrayList<>();
        rosters.add(roster1);
        rosters.add(roster2);

        MockitoAnnotations.initMocks(this);

        teamPlayersAdapter = new TeamPlayersAdapter(rosters, context);
    }

    @Test
    public void testSortByNameAscending() {
        //when
        try {
            teamPlayersAdapter.sortByName(true);
        } catch (Exception e) {

        }
        //then
        Assert.assertEquals(person1, teamPlayersAdapter.getModifiedRosters().get(0).getPerson());
    }

    @Test
    public void testSortByNameDescending() {
        //when
        try {
            teamPlayersAdapter.sortByName(false);
        } catch (Exception e) {

        }
        //then
        Assert.assertEquals(person2, teamPlayersAdapter.getModifiedRosters().get(0).getPerson());
    }

    @Test
    public void testSortByNumberAscending() {
        //when
        try {
            teamPlayersAdapter.sortByNumber(true);
        } catch (Exception e) {

        }
        //then
        Assert.assertEquals(person1, teamPlayersAdapter.getModifiedRosters().get(0).getPerson());
    }

    @Test
    public void testSortByNumberDescending() {
        //when
        try {
            teamPlayersAdapter.sortByNumber(false);
        } catch (Exception e) {

        }
        //then
        Assert.assertEquals(person2, teamPlayersAdapter.getModifiedRosters().get(0).getPerson());
    }
}
