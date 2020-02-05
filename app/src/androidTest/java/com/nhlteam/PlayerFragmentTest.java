package com.nhlteam;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.test.espresso.ViewInteraction;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.nhlteam.data.team.Team;
import com.nhlteam.presentation.fragment.PlayersFragment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class PlayerFragmentTest extends MainActivityTest {

    private PlayersFragment startPlayersFragment() {
        FragmentTransaction transaction = activityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
        LiveData<Team> teamLiveData = new MutableLiveData<>();
        Team team = new Team();
        team.setId(1);
        team.setTeamName("Test team name");
        PlayersFragment playersFragment = new PlayersFragment(teamLiveData);
        transaction.add(playersFragment, "playersFragment");
        transaction.commit();
        return playersFragment;
    }

    @Test
    public void testPlayersFragment() {
        activityTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                PlayersFragment playersFragment = startPlayersFragment();
            }
        });

        ViewInteraction textView = onView(withId(R.id.fragment_players_textView_teamName));
        Assert.assertNotNull(textView);
        ViewInteraction teamRecyclerView = onView(withId(R.id.fragment_players_recyclerView_teamPlayers));
        Assert.assertNotNull(teamRecyclerView);


    }

}
