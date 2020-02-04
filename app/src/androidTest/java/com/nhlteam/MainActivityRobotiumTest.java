package com.nhlteam;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.nhlteam.presentation.activity.MainActivity;
import com.robotium.solo.Solo;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class MainActivityRobotiumTest {

    private Solo solo;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    public void setUp() {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(),
                activityTestRule.getActivity());
    }

    public void tearDown() {
        solo.finishOpenedActivities();
    }

    @Ignore
    @Test
    public void testDefaultTextView() {
        solo.waitForActivity(MainActivity.class);
        solo.assertCurrentActivity("MainActivity is not displayed", MainActivity.class);
        assertTrue("This is a test in EditText is not displayed",
                solo.searchText("this is a test"));

    }
}
