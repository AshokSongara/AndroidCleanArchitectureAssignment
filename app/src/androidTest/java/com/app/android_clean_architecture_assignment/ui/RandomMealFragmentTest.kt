package com.app.android_clean_architecture_assignment.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.presentation.main.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RandomMealFragmentTest {

    @Test
    fun findItemWithTextInRecyclerView() {
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.rvMeal))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Beef"))
                )
            )
    }

}