package com.app.android_clean_architecture_assignment.ui

import android.content.res.Resources
import android.os.SystemClock
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.app.android_clean_architecture_assignment.R
import com.app.android_clean_architecture_assignment.presentation.main.MainActivity
import org.hamcrest.Description
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.internal.matchers.TypeSafeMatcher
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class RandomCharacterFragmentTest {

    /** the Activity of the Target application  */
    private var mActivity: MainActivity? = null

    /** the [RecyclerView]'s resource id  */
    private var resId: Int = R.id.rvCharacter

    /** the [RecyclerView]  */
    private var mRecyclerView: RecyclerView? = null

    /** and it's item count  */
    private var itemCount = 0

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        activityRule.activity
            .supportFragmentManager.beginTransaction()
    }

    @Before
    fun setUpTest() {
        /* obtaining the Activity from the ActivityTestRule */
        this.mActivity = this.activityRule.activity

        /* obtaining handles to the Ui of the Activity */
        this.mRecyclerView =
            this.mActivity!!.findViewById(this.resId)
        this.itemCount = this.mRecyclerView!!.adapter!!.itemCount
    }

    @Test
    fun recyclerViewTestExecution() {

        SystemClock.sleep(5000)

        if (this.mRecyclerView!!.adapter!!.itemCount > 0) {
            for (i in 0 until this.mRecyclerView!!.adapter!!.itemCount) {

                /* clicking the item */
                onView(withId(resId))
                    .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(i, click()))

                /* checking for the text of the first one item */if (i == 0) {
                    onView(
                        RecyclerViewMatcher(resId)
                            .atPositionOnView(i, R.id.tvCharacter)
                    )
                        .check(matches(withText(".GIFfany")))
                }
            }
        }
    }

    class RecyclerViewMatcher(private val recyclerViewId: Int) {

        fun atPosition(position: Int): TypeSafeMatcher<View?> {
            return atPositionOnView(position, -1)
        }

        fun atPositionOnView(position: Int, targetViewId: Int): TypeSafeMatcher<View?> {
            return object : TypeSafeMatcher<View?>() {
                var resources: Resources? = null
                var childView: View? = null
                override fun describeTo(description: Description) {
                    var idDescription = recyclerViewId.toString()
                    if (resources != null) {
                        idDescription = try {
                            resources!!.getResourceName(recyclerViewId)
                        } catch (var4: Resources.NotFoundException) {
                            String.format(
                                "%s (resource name not found)",
                                *arrayOf<Any>(Integer.valueOf(recyclerViewId))
                            )
                        }
                    }
                    description.appendText("with id: $idDescription")
                }

                 override fun matchesSafely(view: View?): Boolean {
                    resources = view!!.resources
                    if (childView == null) {
                        val recyclerView = view.rootView.findViewById(
                            recyclerViewId
                        ) as RecyclerView
                        childView = if (recyclerView.id == recyclerViewId) {
                            recyclerView.findViewHolderForAdapterPosition(position)!!.itemView
                        } else {
                            return false
                        }
                    }
                    return if (targetViewId == -1) {
                        view === childView
                    } else {
                        val targetView: View = childView!!.findViewById(targetViewId)
                        view === targetView
                    }
                }
            }
        }
    }
}