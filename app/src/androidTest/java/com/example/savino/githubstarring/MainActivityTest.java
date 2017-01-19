package com.example.savino.githubstarring;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.savino.githubstarring.activities.MainActivity;
import com.example.savino.githubstarring.api.ApiManager;
import com.example.savino.githubstarring.di.component.DaggerListingRepoPresenterComponent;
import com.example.savino.githubstarring.di.component.ListingRepoPresenterComponent;
import com.example.savino.githubstarring.di.module.ApiModule;
import com.example.savino.githubstarring.model.Stargazers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private ApiManager mApiManager;

    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setup() {
        mApiManager = mock(ApiManager.class);

        ListingRepoPresenterComponent component = DaggerListingRepoPresenterComponent.builder()
                .apiModule(new ApiModule() {
                    @Override
                    public ApiManager provideManager() {
                        return mApiManager;
                    }
                })
                .build();

        ((MyApplication) InstrumentationRegistry.getTargetContext().getApplicationContext())
                .setListingRepoPresenterComponent(component);
    }

    @Test
    public void visibilityView() {
        mRule.launchActivity(null);

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.container)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.error), withText("Both field must be not empty!"))).check(matches(not(isDisplayed())));
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
    }

    @Test
    public void stargazersResult() {
        when(mApiManager.listRepository(anyString(), anyString()))
                .thenReturn(Observable.just(givenMockStargazers()));

        mRule.launchActivity(null);

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.owner))
                .check(matches(isDisplayed()))
                .perform(clearText(), typeText("owner"));

        onView(withId(R.id.repo))
                .check(matches(isDisplayed()))
                .perform(clearText(), typeText("repo"));

        onView(withText("Ok")).perform(click());

        onView(withText("aaa1")).check(matches(isDisplayed()));
        onView(withText("id_1")).check(matches(isDisplayed()));
    }

    @Test
    public void stargazersEmptyResult() {
        when(mApiManager.listRepository(anyString(), anyString()))
                .thenReturn(Observable.just(new ArrayList<Stargazers>(0)));

        mRule.launchActivity(null);

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.owner))
                .check(matches(isDisplayed()))
                .perform(clearText(), typeText("owner"));

        onView(withId(R.id.repo))
                .check(matches(isDisplayed()))
                .perform(clearText(), typeText("repo"));

        onView(withText("Ok")).perform(click());

        onView(withText("Empty result")).check(matches(isDisplayed()));
    }

    @Test
    public void stargazersError() {
        when(mApiManager.listRepository(anyString(), anyString()))
                .thenReturn(Observable.<ArrayList<Stargazers>>error(new IllegalStateException("IllegalStateException")));

        mRule.launchActivity(null);

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.owner))
                .check(matches(isDisplayed()))
                .perform(clearText(), typeText("owner"));

        onView(withId(R.id.repo))
                .check(matches(isDisplayed()))
                .perform(clearText(), typeText("repo"));

        onView(withText("Ok")).perform(click());

        onView(withText("IllegalStateException")).check(matches(isDisplayed()));
    }

    private ArrayList<Stargazers> givenMockStargazers() {
        ArrayList<Stargazers> stargazerses = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Stargazers s = new Stargazers("id_" + i, "aaa" + i, "http://lorempixel.com/200/200/");
            stargazerses.add(s);
        }

        return stargazerses;
    }
}
