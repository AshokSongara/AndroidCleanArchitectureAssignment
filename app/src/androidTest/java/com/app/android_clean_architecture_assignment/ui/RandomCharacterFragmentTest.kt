package com.app.android_clean_architecture_assignment.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.presentation.character.CharacterAdapter
import com.app.android_clean_architecture_assignment.presentation.common.ExpressoIdlingResource
import com.app.android_clean_architecture_assignment.presentation.main.MainActivity
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class RandomCharacterFragmentTest {
    private val items = 4

    @get:Rule
    var activityScenarioRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(
            MainActivity::class.java
        )

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(ExpressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(ExpressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun test_visibility_recyclerview() {
        onView(withId(R.id.rvCharacter)).check(matches(isDisplayed()))
    }

    @Test
    fun test_recyclerview_scroll_to_position() {
        onView(withId(R.id.rvCharacter))
            .perform(
                scrollToPosition<CharacterAdapter.CharacterViewHolder>(
                    items
                )
            )
    }

    @Test
    fun test_isSelectItem_isDetailFragmentVisible() {
        onView(withId(R.id.rvCharacter))
            .perform(
                scrollToPosition<CharacterAdapter.CharacterViewHolder>(
                    items
                )
            )
        onView(withId(R.id.rvCharacter))
            .perform(
                actionOnItemAtPosition<CharacterAdapter.CharacterViewHolder>(
                    items,
                    click()
                )
            )
        onView(withId(R.id.tvCharacter))
            .check(matches(withText("9-Eye")))
    }

    @Test(expected = PerformException::class)
    fun itemWithText_Exist() {
        onView(withId(R.id.rvCharacter))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("9-Eye"))
                )
            )
    }

    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        onView(withId(R.id.rvCharacter))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("not in the list"))
                )
            )
    }

    @Test
    fun testRecyclerviewScrollToBottom() {
        onView(withId(R.id.rvCharacter)).perform(ScrollToBottomAction())
    }


    class ScrollToBottomAction : ViewAction {
        override fun getDescription(): String {
            return "scroll RecyclerView to bottom"
        }

        override fun getConstraints(): Matcher<View> {
            return allOf(isAssignableFrom(RecyclerView::class.java), isDisplayed())
        }

        override fun perform(uiController: UiController?, view: View?) {
            val recyclerView = view as RecyclerView
            val itemCount = recyclerView.adapter?.itemCount
            val position = itemCount?.minus(1) ?: 0
            recyclerView.scrollToPosition(position)
            uiController?.loopMainThreadUntilIdle()
        }
    }
}