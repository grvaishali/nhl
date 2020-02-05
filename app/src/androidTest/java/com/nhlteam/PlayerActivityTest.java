package com.nhlteam;

import androidx.test.espresso.ViewInteraction;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.nhlteam.presentation.activity.PlayerActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class PlayerActivityTest {
    @Rule
    public ActivityTestRule<PlayerActivity> activityTestRule = new ActivityTestRule<>(PlayerActivity.class);

    @Test
    public void testCountryFlagImageView() {
        ViewInteraction imageView = onView(withId(R.id.activity_player_imageView_flag));
        imageView.check(matches(isDisplayed()));
    }

    @Test
    public void testCountryName() {
        ViewInteraction textView = onView(withId(R.id.activity_player_textView_country));
        textView.check(matches(isDisplayed()));


    }
}
