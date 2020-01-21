package com.openclassrooms.entrevoisins.neighbour_list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListNeighbourInstrumentedTest {

    NeighbourApiService apiService = new DummyNeighbourApiService();

    private int neighbourInList = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.size();
    private int favouritesNeighbours = apiService.getFavoritesNeighbours().size();

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Test
    public void neighbourProfilDetailIsLaunch_WhenClickedOnAvatarInList() {
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.item_list_avatar),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_neighbours),
                                        0),
                                0),
                        isDisplayed()));
        appCompatImageView.perform(click());
        onView(withId(R.id.neighbourProfilDetail)).check(matches(isDisplayed()));
    }

    @Test
    public void neighbourProfilDetailIsLaunch_WhenClickedOnNameInList() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.item_list_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_neighbours),
                                        0),
                                1),
                        isDisplayed()));
        appCompatTextView.perform(click());
        onView(withId(R.id.neighbourProfilDetail)).check(matches(isDisplayed()));
    }

    @Test
    public void checkThatNameOnPictureIsNotEmpty() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.item_list_name),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_neighbours),
                                        0),
                                1),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.neighbourNameInProfilePicture),
                        childAtPosition(
                                allOf(withId(R.id.neighbourProfilDetail),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        textView.check(matches(not(withText(""))));
    }

    @Test
    public void checkIfRemoveNeighbourIsWorking() {
        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(allOf(withId(R.id.container),
                                childAtPosition(
                                        withId(R.id.main_content),
                                        1))),
                        isDisplayed()));
        recyclerView.check(RecyclerViewItemCountAssertion.withItemCount(neighbourInList));

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.item_list_delete_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_neighbours),
                                        0),
                                2),
                        isDisplayed()));
        appCompatTextView.perform(click());
        recyclerView.check(RecyclerViewItemCountAssertion.withItemCount(neighbourInList - 1));
    }

    @Test
    public void checkIfFavouriteNeighbourIsWorking() {
        ViewInteraction tabView = onView(
                allOf(withContentDescription("Favorites"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.container),
                        childAtPosition(
                                allOf(withId(R.id.main_content),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.list_neighbours),
                        withParent(allOf(withId(R.id.container),
                                childAtPosition(
                                        withId(R.id.main_content),
                                        1))),
                        isDisplayed()));
        recyclerView.check(RecyclerViewItemCountAssertion.withItemCount(favouritesNeighbours));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
